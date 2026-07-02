package com.clouds_of_winter.shadow_of_killing.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ORANGE_CRYSTAL_APPLE = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(1.2F)
            .alwaysEat()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 24000, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 24000, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 24000, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.JUMP, 24000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 24000, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 24000, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 24000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 24000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 24000, 14), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 24000, 4), 1.0F)
            .effect(new MobEffectInstance(MobEffects.SATURATION, 24000, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 24000, 3), 1.0F)
            .effect(new MobEffectInstance(ModEffects.COLD_RESISTANT.get(), 24000), 1.0F)
            .build();
}
