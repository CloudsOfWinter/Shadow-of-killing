package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> ORES_ORANGE_CRYSTAL = createForgeBlockTag("ores/orange_crystal");
        public static final TagKey<Block> ORES_DARK_IRON = createForgeBlockTag("ores/dark_iron");
        public static final TagKey<Block> STORAGE_BLOCKS_ORANGE_CRYSTAL = createForgeBlockTag("storage_blocks/orange_crystal");
        public static final TagKey<Block> STORAGE_BLOCKS_DARK_IRON = createForgeBlockTag("storage_blocks/dark_iron");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_DARK_IRON = createForgeBlockTag("storage_blocks/raw_dark_iron");
        public static final TagKey<Block> ORANGE_CRYSTAL_ORES = createBlockTag("orange_crystal_ores");
        public static final TagKey<Block> DARK_IRON_ORES = createBlockTag("dark_iron_ores");

        private static TagKey<Block> createForgeBlockTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Block> createBlockTag(String name) {
            return BlockTags.create(new ResourceLocation(ShadowofKilling.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> GEMS_ORANGE_CRYSTAL = createForgeItemTag("gems/orange_crystal");
        public static final TagKey<Item> INGOTS_DARK_IRON = createForgeItemTag("ingots/dark_iron");
        public static final TagKey<Item> NUGGETS_DARK_IRON = createForgeItemTag("nuggets/dark_iron");
        public static final TagKey<Item> ORES_ORANGE_CRYSTAL = createForgeItemTag("ores/orange_crystal");
        public static final TagKey<Item> ORES_DARK_IRON = createForgeItemTag("ores/dark_iron");
        public static final TagKey<Item> RAW_MATERIALS_DARK_IRON = createForgeItemTag("raw_materials/dark_iron");
        public static final TagKey<Item> STORAGE_BLOCKS_ORANGE_CRYSTAL = createForgeItemTag("storage_blocks/orange_crystal");
        public static final TagKey<Item> STORAGE_BLOCKS_DARK_IRON = createForgeItemTag("storage_blocks/dark_iron");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_DARK_IRON = createForgeItemTag("storage_blocks/raw_dark_iron");
        public static final TagKey<Item> ORANGE_CRYSTAL_ORES = createItemTag("orange_crystal_ores");
        public static final TagKey<Item> DARK_IRON_ORES = createItemTag("dark_iron_ores");

        private static TagKey<Item> createForgeItemTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        private static TagKey<Item> createItemTag(String name) {
            return ItemTags.create(new ResourceLocation(ShadowofKilling.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_LORD_CASTLE = createBiomeTag("has_structure/lord_castle");

        private static TagKey<Biome> createForgeBiomeTag(String name) {
            return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge", name));
        }

        private static TagKey<Biome> createBiomeTag(String name) {
            return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(ShadowofKilling.MOD_ID, name));
        }

    }
}
