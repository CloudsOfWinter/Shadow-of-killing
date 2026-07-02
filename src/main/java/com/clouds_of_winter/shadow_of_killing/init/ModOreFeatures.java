package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModOreFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ShadowofKilling.MOD_ID);


    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ORE_ORANGE_CRYSTAL = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORANGE_CRYSTAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORANGE_CRYSTAL_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORE_DARK_IRON_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.DARK_IRON_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_DARK_IRON_ORE.get().defaultBlockState())));


    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_ORANGE_CRYSTAL = CONFIGURED_FEATURES.register("ore_orange_crystal",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ORE_ORANGE_CRYSTAL.get(),3)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_DARK_IRON_SMALL  = CONFIGURED_FEATURES.register("ore_dark_iron_small",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_DARK_IRON_TARGET_LIST.get(), 4 , 0.5F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_DARK_IRON_LARGE  = CONFIGURED_FEATURES.register("ore_dark_iron_large",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_DARK_IRON_TARGET_LIST.get(), 12 , 0.7F)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORE_DARK_IRON_BURIED  = CONFIGURED_FEATURES.register("ore_dark_iron_buried",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORE_DARK_IRON_TARGET_LIST.get(), 8, 1.0F)));
}
