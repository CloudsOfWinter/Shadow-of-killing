
package com.clouds_of_winter.shadow_of_killing.item;

import net.minecraft.world.item.*;

public class BlackSword extends SwordItem {

    /**
    // 定义唯一的 UUID 用于属性修饰符
    public static final UUID ATTACK_RANGE_UUID = UUID.fromString("1a9b8c7d-6e5f-4a3b-8c1d-2e3f4a5b6c7d");
    */


    public BlackSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }
    /**
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player player) {
            // 获取攻击距离属性
            AttributeInstance attackRange = player.getAttribute(ForgeMod.ATTACK_RANGE.get());
            if (attackRange != null) {
                // 移除旧的修饰符（如果存在）
                AttributeModifier oldModifier = attackRange.getModifier(ATTACK_RANGE_UUID);
                if (oldModifier != null) {
                    attackRange.removeModifier(ATTACK_RANGE_UUID);
                }

                // 添加新修饰符（2倍距离）
                AttributeModifier newModifier = new AttributeModifier(
                        ATTACK_RANGE_UUID,
                        "obsidian_attack_range",
                        1.0,  // 基础值 * 2 (MULTIPLY_TOTAL)
                        AttributeModifier.Operation.MULTIPLY_TOTAL
                );
                attackRange.addTransientModifier(newModifier);
            }
        }
        return super.onEntitySwing(stack, entity);
    }

    */
}
