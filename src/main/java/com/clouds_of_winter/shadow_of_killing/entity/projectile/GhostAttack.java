package com.clouds_of_winter.shadow_of_killing.entity.projectile;

import com.clouds_of_winter.shadow_of_killing.level.GhostAttackExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.*;
import net.minecraft.world.level.Level;

public class GhostAttack extends WitherSkull {

    public GhostAttack(EntityType<? extends GhostAttack> type, Level level) {
        super(type, level);
    }

    public GhostAttack(Level level, LivingEntity owner, double dx, double dy, double dz) {
        super(level, owner, dx, dy, dz);
    }

    protected void onHitEntity(EntityHitResult p_37626_) {
        if (!this.level.isClientSide) {
            Entity entity = p_37626_.getEntity();
            Entity entity1 = this.getOwner();
            boolean flag;
            if (entity1 instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity1;
                flag = entity.hurt(DamageSource.witherSkull(this, livingentity), 4.0F);
                if (flag) {
                    if (entity.isAlive()) {
                        this.doEnchantDamageEffects(livingentity, entity);
                    }
                }
            } else {
                flag = entity.hurt(DamageSource.MAGIC, 3.0F);
            }

            if (flag && entity instanceof LivingEntity) {

                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this.getEffectSource());
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40), this.getEffectSource());

            }

        }
    }

    @Override
    protected void onHit(HitResult result) {
        HitResult.Type hitresult$type = result.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)result);
            this.level.gameEvent(GameEvent.PROJECTILE_LAND, result.getLocation(), GameEvent.Context.of(this, (BlockState)null));
        } else if (hitresult$type == HitResult.Type.BLOCK) {
            BlockHitResult blockhitresult = (BlockHitResult)result;
            this.onHitBlock(blockhitresult);
            BlockPos blockpos = blockhitresult.getBlockPos();
            this.level.gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level.getBlockState(blockpos)));
        }
        if (!this.level.isClientSide) {
            // 使用自定义爆炸
            GhostAttackExplosion explosion = new GhostAttackExplosion(level,this.getOwner(), this.getX(), this.getY(), this.getZ(), 1.0F);
            explosion.explode();
            this.discard();
        }
    }
}