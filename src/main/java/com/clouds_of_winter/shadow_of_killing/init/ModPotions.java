package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ShadowofKilling.MOD_ID);
    public static final RegistryObject<Potion> COLD_RESISTANT = POTIONS.register("cold_resistant",() -> new Potion(new MobEffectInstance(ModEffects.COLD_RESISTANT.get(),9600)));
    public static final RegistryObject<Potion> LONG_COLD_RESISTANT = POTIONS.register("long_cold_resistant",() -> new Potion("cold_resistant", new MobEffectInstance(ModEffects.COLD_RESISTANT.get(),18000)));
}
