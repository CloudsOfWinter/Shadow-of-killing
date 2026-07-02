package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModOrePlacements {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE_REGISTRY = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ShadowofKilling.MOD_ID);

    public static final RegistryObject<PlacedFeature> ORE_ORANGE_CRYSTAL = PLACED_FEATURE_REGISTRY.register("ore_orange_crystal", () -> new PlacedFeature(ModOreFeatures.ORE_ORANGE_CRYSTAL.getHolder().get(), commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(-25), VerticalAnchor.absolute(3)))));

    public static final RegistryObject<PlacedFeature> ORE_DARK_IRON = PLACED_FEATURE_REGISTRY.register("ore_dark_iron", () -> new PlacedFeature(ModOreFeatures.ORE_DARK_IRON_SMALL.getHolder().get(), commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)))));
    public static final RegistryObject<PlacedFeature> ORE_DARK_IRON_LARGE = PLACED_FEATURE_REGISTRY.register("ore_dark_iron_large", () -> new PlacedFeature(ModOreFeatures.ORE_DARK_IRON_LARGE.getHolder().get(), rareOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)))));
    public static final RegistryObject<PlacedFeature> ORE_DARK_IRON_BURIED = PLACED_FEATURE_REGISTRY.register("ore_dark_iron_buried", () -> new PlacedFeature(ModOreFeatures.ORE_DARK_IRON_BURIED.getHolder().get(), commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)))));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}