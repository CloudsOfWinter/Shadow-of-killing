package com.clouds_of_winter.shadow_of_killing.level.notnecessary;

import com.clouds_of_winter.shadow_of_killing.entity.WitherEnderman;
import com.clouds_of_winter.shadow_of_killing.entity.projectile.GhostAttack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class CustomExplosion {
    private final Level level;
    private final double x, y, z;
    private final float radius;
    private final float damage;
    private final int witherDuration;
    private final Entity source;
    private final List<BlockPos> affectedBlocks = new ArrayList<>();
    private final List<LivingEntity> affectedEntities = new ArrayList<>();

    public CustomExplosion(Level level, Entity source, double x, double y, double z,
                           float radius, float damage, int witherDuration) {
        this.level = level;
        this.source = source;
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.damage = damage;
        this.witherDuration = witherDuration;
    }

    public void explode() {
        if (level.isClientSide) return;

        // 1. 视觉效果和音效
        spawnExplosionEffects();

        // 2. 对实体产生影响
        affectEntities();

        // 3. 可选：对特定非地形方块产生影响
        affectSpecialBlocks();
    }

    private void spawnExplosionEffects() {
        // 爆炸粒子效果
        // 主要爆炸粒子效果
        if (level instanceof ServerLevel serverLevel) {
            // 爆炸核心效果 - 使用 EXPLOSION 粒子
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                    x, y, z, 5, 0.5, 0.5, 0.5, 0.1);


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

    private void affectEntities() {
        AABB explosionArea = new AABB(
                x - radius, y - radius, z - radius,
                x + radius, y + radius, z + radius
        );

        List<LivingEntity> entities = level.getEntitiesOfClass(
                LivingEntity.class, explosionArea
        );

        for (LivingEntity entity : entities) {
            if (shouldAffectEntity(entity)) {
                affectEntity(entity);
            }
        }
    }

    private boolean shouldAffectEntity(LivingEntity entity) {
        // 自定义末影人免疫自己的爆炸
        if (entity instanceof WitherEnderman && source instanceof GhostAttack) {
            GhostAttack skull = (GhostAttack) source;
            if (skull.getOwner() == entity) {
                return false;
            }
        }

        // 忽略创造模式玩家
        if (entity instanceof Player player && player.isCreative()) {
            return false;
        }

        return true;
    }

    private void affectEntity(LivingEntity entity) {
        double dx = entity.getX() - x;
        double dy = entity.getY() + entity.getEyeHeight() - y;
        double dz = entity.getZ() - z;

        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (distance <= radius) {
            // 计算伤害衰减
            float damageMultiplier = 1.0F - (float)(distance / radius);
            float actualDamage = damage * damageMultiplier;

            // 造成伤害
            if (actualDamage > 0) {
                entity.hurt(createDamageSource(), actualDamage);
            }

            // 施加凋零效果
            if (witherDuration > 0) {
                entity.addEffect(new MobEffectInstance(
                        MobEffects.WITHER,
                        (int)(witherDuration * damageMultiplier),
                        1 // 凋零效果等级
                ));
            }

            // 计算击退（自定义末影人减少击退）
            if (!(entity instanceof WitherEnderman)) {
                applyKnockback(entity, dx, dy, dz, distance, damageMultiplier);
            } else {
                applyReducedKnockback(entity, dx, dy, dz, distance, damageMultiplier);
            }

            affectedEntities.add(entity);
        }
    }

    private DamageSource createDamageSource() {
        return new DamageSource("custom_wither_explosion")
                .setScalesWithDifficulty()
                .setMagic();
    }

    private void applyKnockback(LivingEntity entity, double dx, double dy, double dz,
                                double distance, float multiplier) {
        double strength = 2.0 * multiplier / distance;

        Vec3 knockbackVec = new Vec3(dx, dy, dz)
                .normalize()
                .scale(strength);

        entity.setDeltaMovement(
                entity.getDeltaMovement().add(knockbackVec)
        );

        // 确保实体知道被击退
        entity.hurtMarked = true;
    }

    private void applyReducedKnockback(LivingEntity entity, double dx, double dy, double dz,
                                       double distance, float multiplier) {
        // 自定义末影人只受到轻微击退
        double strength = 0.3 * multiplier / distance;

        Vec3 knockbackVec = new Vec3(dx, dy, dz)
                .normalize()
                .scale(strength);

        entity.setDeltaMovement(
                entity.getDeltaMovement().add(knockbackVec)
        );

        entity.hurtMarked = true;
    }

    private void affectSpecialBlocks() {
        // 这里可以添加对特定非地形方块的影响
        // 例如：只影响草、花等装饰性方块，不影响地形方块

        int radiusInt = (int) Math.ceil(radius);
        BlockPos center = new BlockPos(x, y, z);

        for (int ix = -radiusInt; ix <= radiusInt; ix++) {
            for (int iy = -radiusInt; iy <= radiusInt; iy++) {
                for (int iz = -radiusInt; iz <= radiusInt; iz++) {
                    BlockPos pos = center.offset(ix, iy, iz);

                    if (shouldAffectBlock(pos)) {
                        affectBlock(pos);
                    }
                }
            }
        }
    }

    private boolean shouldAffectBlock(BlockPos pos) {
        // 直接返回false，不破坏任何方块
        return false;

        // 如果你想要保留距离计算但只是不执行破坏，可以这样写：
    /*
    double distance = Math.sqrt(
        Math.pow(pos.getX() + 0.5 - x, 2) +
        Math.pow(pos.getY() + 0.5 - y, 2) +
        Math.pow(pos.getZ() + 0.5 - z, 2)
    );

    // 即使距离在爆炸范围内，也返回false不破坏方块
    return false;
    */
    }

    private void affectBlock(BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        // 破坏装饰性方块
        if (!state.isAir()) {
            level.destroyBlock(pos, false);
            affectedBlocks.add(pos);

            // 添加方块破坏粒子
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        new BlockParticleOption(ParticleTypes.BLOCK, state),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        10, 0.3, 0.3, 0.3, 0.1
                );
            }
        }
    }

    // 获取受影响实体（用于调试或显示）
    public List<LivingEntity> getAffectedEntities() {
        return Collections.unmodifiableList(affectedEntities);
    }

    // 获取受影响方块（用于调试或显示）
    public List<BlockPos> getAffectedBlocks() {
        return Collections.unmodifiableList(affectedBlocks);
    }
}