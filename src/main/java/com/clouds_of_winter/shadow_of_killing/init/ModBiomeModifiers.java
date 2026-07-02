package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ShadowofKilling.MOD_ID);

    public static final RegistryObject<Codec<ModSpawnEvent.OverworldSpawnsWithExclusions>> OVERWORLD_SPAWNS_WITH_EXCLUSIONS =
            BIOME_MODIFIER_SERIALIZERS.register("overworld_spawns_with_exclusions",
                    () -> RecordCodecBuilder.create(builder -> builder.group(
                            MobSpawnSettings.SpawnerData.CODEC.listOf().fieldOf("spawners").forGetter(ModSpawnEvent.OverworldSpawnsWithExclusions::spawners)
                    ).apply(builder, ModSpawnEvent.OverworldSpawnsWithExclusions::new))
            );

    public static final RegistryObject<Codec<ModSpawnEvent.ExcludeNetherEndOre>> EXCLUDE_NETHER_END_ORE = BIOME_MODIFIER_SERIALIZERS.register("exclude_nether_end_ore", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(ModSpawnEvent.ExcludeNetherEndOre::features),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(ModSpawnEvent.ExcludeNetherEndOre::step)
            ).apply(builder, ModSpawnEvent.ExcludeNetherEndOre::new))
    );
}
