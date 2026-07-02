package com.clouds_of_winter.shadow_of_killing.notnecessary.mixin;

import com.clouds_of_winter.shadow_of_killing.entity.EliteSkeletonZombieArcher;
import com.clouds_of_winter.shadow_of_killing.entity.EliteWitherZombieArcher;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MeleeAttackGoal.class)
public abstract class MeleeAttackGoalMixin {


    @Shadow @Final protected PathfinderMob mob;

    @Shadow protected abstract void resetAttackCooldown();
    @Shadow protected abstract int getTicksUntilNextAttack();
    @Shadow protected abstract double getAttackReachSqr(LivingEntity entity);


    @Inject(method = "checkAndPerformAttack", at = @At("HEAD"))
    private void onCheckAndPerformAttack(LivingEntity target, double distanceSqr, CallbackInfo ci) {

        if (mob instanceof EliteWitherZombieArcher) {
            ItemStack mainHandItem = this.mob.getMainHandItem();
            if (mainHandItem.getItem() instanceof TieredItem) {
                double mobHeight = this.mob.getBbHeight();
                double minY = 0.5 * mobHeight;
                double maxY = 1.4 * mobHeight;
                double yDiff = target.getY() - this.mob.getY();
                if (yDiff >= minY && yDiff <= maxY) {
                    double attackReachSqr = this.getAttackReachSqr(target);
                    double dx = target.getX() - this.mob.getX();
                    double dz = target.getZ() - this.mob.getZ();
                    double horizontalDistanceSqr = dx * dx + dz * dz;
                    if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                        this.resetAttackCooldown();
                        this.mob.swing(InteractionHand.MAIN_HAND);
                        this.mob.doHurtTarget(target);
                    }
                }
            } else {
                double mobHeight = this.mob.getBbHeight();
                double minY = 0.5 * mobHeight;
                double maxY = 1.125 * mobHeight;
                double yDiff = target.getY() - this.mob.getY();
                if (yDiff >= minY && yDiff <= maxY) {
                    double attackReachSqr = this.getAttackReachSqr(target);
                    double dx = target.getX() - this.mob.getX();
                    double dz = target.getZ() - this.mob.getZ();
                    double horizontalDistanceSqr = dx * dx + dz * dz;
                    if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                        this.resetAttackCooldown();
                        this.mob.swing(InteractionHand.MAIN_HAND);
                        this.mob.doHurtTarget(target);
                    }
                }
            }


        }

        if (mob instanceof EliteSkeletonZombieArcher) {
            ItemStack mainHandItem = this.mob.getMainHandItem();
            if (mainHandItem.getItem() instanceof TieredItem) {
                double mobHeight = this.mob.getBbHeight();
                double minY = 0.5 * mobHeight;
                double maxY = 1.4 * mobHeight;
                double yDiff = target.getY() - this.mob.getY();
                if (yDiff >= minY && yDiff <= maxY) {
                    double attackReachSqr = this.getAttackReachSqr(target);
                    double dx = target.getX() - this.mob.getX();
                    double dz = target.getZ() - this.mob.getZ();
                    double horizontalDistanceSqr = dx * dx + dz * dz;
                    if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                        this.resetAttackCooldown();
                        this.mob.swing(InteractionHand.MAIN_HAND);
                        this.mob.doHurtTarget(target);
                    }
                }
            } else {
                double mobHeight = this.mob.getBbHeight();
                double minY = 0.5 * mobHeight;
                double maxY = 1.125628140703518 * mobHeight;
                double yDiff = target.getY() - this.mob.getY();
                if (yDiff >= minY && yDiff <= maxY) {
                    double attackReachSqr = this.getAttackReachSqr(target);
                    double dx = target.getX() - this.mob.getX();
                    double dz = target.getZ() - this.mob.getZ();
                    double horizontalDistanceSqr = dx * dx + dz * dz;
                    if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                        this.resetAttackCooldown();
                        this.mob.swing(InteractionHand.MAIN_HAND);
                        this.mob.doHurtTarget(target);
                    }
                }
            }





        }

    }






















    /**
    @Inject(method = "checkAndPerformAttack", at = @At("HEAD"), cancellable = true)
    private void onCheckAndPerformAttack(LivingEntity target, double distanceSqr, CallbackInfo ci) {



        if (mob instanceof EliteWitherZombieArcher) {
            double mobHeight = this.mob.getBbHeight();
            double minY = 0.5 * mobHeight;
            double maxY = 1.125 * mobHeight;
            double yDiff = target.getY() - this.mob.getY();
            if (yDiff >= minY && yDiff <= maxY) {
                double attackReachSqr = this.getAttackReachSqr(target);
                double dx = target.getX() - this.mob.getX();
                double dz = target.getZ() - this.mob.getZ();
                double horizontalDistanceSqr = dx * dx + dz * dz;
                if (horizontalDistanceSqr <= attackReachSqr && this.getTicksUntilNextAttack() <= 0) {
                    this.resetAttackCooldown();
                    this.mob.swing(InteractionHand.MAIN_HAND);
                    this.mob.doHurtTarget(target);
                }
                ci.cancel();
            }
        }
    }
    */

}
