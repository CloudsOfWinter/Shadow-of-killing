/**
 package com.winter_general_is_the_best.shadow_of_killing.event;

import com.winter_general_is_the_best.shadow_of_killing.entity.BlackswordRaider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Comparator;
import java.util.List;

public class CombatHandler {
    private static final int SWITCH_TICKS = 8 * 20; // 8秒（以tick计）

    // 当实体设置新目标时
    @SubscribeEvent
    public static void onSetTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof BlackswordRaider attacker)) return;

        attacker.getCapability(ModCapabilities.TARGET_DATA).ifPresent(data -> {
            // 记录首次锁定的目标
            if (event.getNewTarget() != null && data.getFirstTargetId() == null) {
                data.setFirstTargetId(event.getNewTarget().getUUID());
                data.setFirstTargetTime(attacker.level.getGameTime());
                data.resetNoDamageTicks();
            }
            // 目标被清除时重置状态
            else if (event.getNewTarget() == null) {
                data.setFirstTargetId(null);
                data.setNoDamageTicks(0);
            }
        });
    }

    // 每tick检查是否超时
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof BlackswordRaider attacker)) return;
        LivingEntity target = attacker.getTarget();

        attacker.getCapability(ModCapabilities.TARGET_DATA).ifPresent(data -> {
            // 有目标时检查超时
            if (target != null && data.getFirstTargetId() != null) {
                // 检查是否超过8秒未攻击
                if (attacker.level.getGameTime() - data.getFirstTargetTime() > SWITCH_TICKS) {
                    // 切换到新目标
                    findAndSetNewTarget(attacker, target);
                    return;
                }

                // 增加未攻击计时
                data.incrementNoDamageTicks();

                // 超过8秒未造成伤害时切换
                if (data.getNoDamageTicks() >= SWITCH_TICKS) {
                    findAndSetNewTarget(attacker, target);
                }
            }
        });
    }

    // 当造成伤害时重置计时器
    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof BlackswordRaider attacker) {
            attacker.getCapability(ModCapabilities.TARGET_DATA).ifPresent(data -> {
                if (event.getEntity().equals(attacker.getTarget())) {
                    data.resetNoDamageTicks(); // 重置未攻击计时
                }
            });
        }
    }

    // 寻找并切换新目标
    private static void findAndSetNewTarget(BlackswordRaider attacker, LivingEntity currentTarget) {
        List<LivingEntity> candidates = attacker.level.getEntitiesOfClass(
                LivingEntity.class,
                attacker.getBoundingBox().inflate(16),
                e -> e != currentTarget && e.isAlive() && attacker.canAttack(e)
        );

        if (!candidates.isEmpty()) {
            // 选择最近的实体
            LivingEntity newTarget = candidates.stream()
                    .min(Comparator.comparingDouble(attacker::distanceToSqr))
                    .orElse(null);

            if (newTarget != null) {
                attacker.setTarget(newTarget);
            }
        }

        // 重置索敌状态
        attacker.getCapability(ModCapabilities.TARGET_DATA).ifPresent(data -> {
            data.setFirstTargetId(null);
            data.setNoDamageTicks(0);
        });
    }
}
*/