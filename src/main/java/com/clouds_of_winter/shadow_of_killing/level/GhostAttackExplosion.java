package com.clouds_of_winter.shadow_of_killing.level;

import com.clouds_of_winter.shadow_of_killing.entity.WitherEnderman;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GhostAttackExplosion {
    private final Level level;
    private final double x, y, z;
    private final float radius;
    private final Entity source;
    private final DamageSource damageSource;
    private final List<BlockPos> affectedBlocks = new ArrayList<>();
    private final Map<Player, Vec3> hitPlayers = new HashMap<>();

    public GhostAttackExplosion(Level level, Entity source, double x, double y, double z, float radius) {
        this.level = level;
        this.source = source;
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.damageSource = DamageSource.explosion((LivingEntity) source);
    }

    public void explode() {
        // 1. 收集受影响的实体
        AABB affectedArea = new AABB(
                x - radius, y - radius, z - radius,
                x + radius, y + radius, z + radius
        );

        List<Entity> entities = level.getEntities(source, affectedArea);

        // 2. 对实体应用爆炸效果
        for (Entity entity : entities) {
            // 跳过自定义生物和忽略爆炸的实体
            if (shouldIgnoreEntity(entity)) {
                continue;
            }

            applyExplosionToEntity(entity);
        }

        // 4. 生成爆炸粒子效果
        spawnParticles();
    }

    private boolean shouldIgnoreEntity(Entity entity) {
        // 跳过自定义生物
        if (entity instanceof WitherEnderman) {
            return true;
        }

        // 跳过标记为忽略爆炸的实体
        if (entity.ignoreExplosion()) {
            return true;
        }

        // 可以添加其他跳过条件
        // if (entity instanceof OtherCustomEntity) {
        //     return true;
        // }

        return false;
    }

    private void applyExplosionToEntity(Entity entity) {
        double distanceToExplosion = entity.distanceToSqr(x, y, z);

        if (distanceToExplosion > radius * radius) {
            return;
        }

        double distance = Math.sqrt(distanceToExplosion);
        double exposure = getExposure(entity);

        // 计算伤害
        float damage = calculateDamage(distance, exposure);

        if (damage > 0) {
            entity.hurt(damageSource, damage);

            // 应用基于距离的击退
            applyKnockback(entity, distance, exposure);

            // 应用效果
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 200));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40));
            }
        }
    }

    private float calculateDamage(double distance, double exposure) {
        float damageRatio = 1.0F - (float)(distance / radius);
        float baseDamage = radius * 14.78260869565217F;
        return baseDamage * damageRatio * (float)exposure;
    }

    private void applyKnockback(Entity entity, double distance, double exposure) {
        // 计算基础击退力度（基于距离衰减）
        float knockbackRatio = calculateKnockbackRatio(distance);
        float baseKnockbackForce = knockbackRatio * (float)exposure;

        // 获取环境因子
        float environmentFactor = getEnvironmentFactor(entity);

        // 获取从爆炸中心指向实体的方向向量
        Vec3 direction = new Vec3(
                entity.getX() - x,
                entity.getY() - y,
                entity.getZ() - z
        ).normalize();

        // 计算各轴的击退力度
        Vec3 knockback = calculateAxisKnockback(direction, baseKnockbackForce, distance);

        // 应用环境因子到击退力度
        knockback = knockback.multiply(environmentFactor, environmentFactor, environmentFactor);

        // 应用击退
        entity.setDeltaMovement(
                entity.getDeltaMovement().add(knockback)
        );

    }

    // 环境因素考虑
    private float getEnvironmentFactor(Entity entity) {
        if (entity.isInWater()) {
            return 0.5F; // 水中击退减半
        } else if (entity.isInLava()) {
            return 0.3F; // 熔岩中击退更弱
        } else if (entity.isOnGround()) {
            return 0.9F; // 地面上略微减弱
        }
        return 1.0F; // 空中全效果
    }

    // 计算击退比率（基于距离）
    private float calculateKnockbackRatio(double distance) {
        float normalizedDistance = (float)(distance / radius);

        // 使用二次衰减曲线（中心强，边缘弱）
        return 1.0F - normalizedDistance * normalizedDistance;
    }

    // 计算各轴击退力度
    private Vec3 calculateAxisKnockback(Vec3 direction, float baseForce, double distance) {
        // 水平击退系数
        float horizontalMultiplier = 0.5F;
        // 垂直击退系数
        float verticalMultiplier = 0.5F;
        // 额外向上力度
        float upwardBoost = 0.2F;

        // XZ平面击退（水平方向）
        float horizontalForce = baseForce * horizontalMultiplier;
        float xForce = (float)direction.x * horizontalForce;
        float zForce = (float)direction.z * horizontalForce;

        // Y轴击退（垂直方向）
        float yForce = calculateVerticalKnockback(direction, baseForce, distance, verticalMultiplier, upwardBoost);

        return new Vec3(xForce, yForce, zForce);
    }

    // 计算垂直方向击退
    private float calculateVerticalKnockback(Vec3 direction, float baseForce, double distance, float verticalMultiplier, float upwardBoost) {
        float verticalForce;

        // 基础垂直击退
        float baseVertical = baseForce * verticalMultiplier;

        // 根据实体相对于爆炸中心的位置调整垂直击退
        if (direction.y > 0) {
            // 实体在爆炸上方 - 更强的向下击退
            verticalForce = -baseVertical * (1.0F + (float)direction.y);
        } else {
            // 实体在爆炸下方或同水平 - 向上击退
            verticalForce = baseVertical * (1.0F - (float)direction.y);

            // 添加额外向上力度（确保实体被炸飞）
            float distanceFactor = 1.0F - (float)(distance / radius);
            verticalForce += upwardBoost * distanceFactor;
        }

        return verticalForce;
    }

    private double getExposure(Entity entity) {
        // 简化的暴露度计算
        AABB entityBoundingBox = entity.getBoundingBox();
        int sampleCount = 8;
        int exposedCount = 0;

        for (int i = 0; i < sampleCount; ++i) {
            double sampleX = entityBoundingBox.minX + (entityBoundingBox.maxX - entityBoundingBox.minX) * (i % 2) * 0.5;
            double sampleY = entityBoundingBox.minY + (entityBoundingBox.maxY - entityBoundingBox.minY) * (i / 2 % 2) * 0.5;
            double sampleZ = entityBoundingBox.minZ + (entityBoundingBox.maxZ - entityBoundingBox.minZ) * (i / 4) * 0.5;

            Vec3 samplePos = new Vec3(sampleX, sampleY, sampleZ);
            if (level.clip(new ClipContext(samplePos, new Vec3(x, y, z),
                    ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getType() == HitResult.Type.MISS) {
                exposedCount++;
            }
        }

        return (double) exposedCount / sampleCount;
    }

    private void spawnParticles() {

        if (level instanceof ServerLevel serverLevel) {

            if (!(radius < 2.0F)) {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 5, 0.5, 0.5, 0.5, 0.1);
            } else {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 5, 0.5, 0.5, 0.5, 0.1);
            }
        }




        // 爆炸音效
        level.playSound(null, x, y, z,
                SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 2.0F,
                (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);

        // 爆炸闪光
        level.playSound(null, x, y, z,
                SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F,
                (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.5F);
    }
}