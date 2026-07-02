package com.clouds_of_winter.shadow_of_killing.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ColdResistant extends MobEffect {
    public ColdResistant() {
        super(MobEffectCategory.BENEFICIAL, 0x88C9FF);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // 每tick重置冰冻计时器
        entity.setTicksFrozen(0);
        entity.setIsInPowderSnow(false);
        super.applyEffectTick(entity,amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 每tick都执行
    }
}
