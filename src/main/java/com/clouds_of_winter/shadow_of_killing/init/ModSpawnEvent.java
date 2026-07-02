package com.clouds_of_winter.shadow_of_killing.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.*;

import java.util.List;

public class ModSpawnEvent {

    public record OverworldSpawnsWithExclusions(List<MobSpawnSettings.SpawnerData> spawners) implements BiomeModifier {
        private static final List<ResourceKey<Biome>> EXCLUDED_BIOME_KEYS = List.of(
                Biomes.MUSHROOM_FIELDS,
                Biomes.THE_VOID,
                Biomes.DEEP_DARK
        );

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD && shouldSpawnInBiome(biome)) {
                MobSpawnSettingsBuilder spawnSettings = builder.getMobSpawnSettings();
                for (MobSpawnSettings.SpawnerData spawner : this.spawners) {
                    spawnSettings.addSpawn(spawner.type.getCategory(), spawner);
                }
            }
        }

        private boolean shouldSpawnInBiome(Holder<Biome> biome) {
            if (!biome.is(BiomeTags.IS_OVERWORLD)) {
                return false;
            }

            for (ResourceKey<Biome> excludedKey : EXCLUDED_BIOME_KEYS) {
                if (biome.is(excludedKey)) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return ModBiomeModifiers.OVERWORLD_SPAWNS_WITH_EXCLUSIONS.get();
        }
    }

    public record ExcludeNetherEndOre(HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier {

        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD && shouldGenerateInBiome(biome)) {
                BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
                this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
            }
        }

        private boolean shouldGenerateInBiome(Holder<Biome> biome) {
            if (biome.is(BiomeTags.IS_END) || biome.is(BiomeTags.IS_NETHER)) {
                return false;
            }

            return true;
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return ModBiomeModifiers.EXCLUDE_NETHER_END_ORE.get();
        }
    }
}
