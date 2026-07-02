package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ShadowofKilling.MOD_ID);
    public static final RegistryObject<Block> ORANGE_CRYSTAL_BLOCK = BLOCKS.register("orange_crystal_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).strength(50.0F, 1200.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final RegistryObject<Block> ORANGE_CRYSTAL_ORE = BLOCKS.register("orange_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(38.5F, 1200.0F).requiresCorrectToolForDrops(), UniformInt.of(10, 24)));
    public static final RegistryObject<Block> DEEPSLATE_ORANGE_CRYSTAL_ORE = BLOCKS.register("deepslate_orange_crystal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(ORANGE_CRYSTAL_ORE.get()).color(MaterialColor.DEEPSLATE).strength(40.5F, 1200.0F).sound(SoundType.DEEPSLATE), UniformInt.of(10, 24)));
    public static final RegistryObject<Block> DARK_IRON_BLOCK = BLOCKS.register("dark_iron_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL,MaterialColor.TERRACOTTA_BLUE).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final RegistryObject<Block> DARK_IRON_ORE = BLOCKS.register("dark_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_DARK_IRON_ORE = BLOCKS.register("deepslate_dark_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DARK_IRON_ORE.get()).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> RAW_DARK_IRON_BLOCK = BLOCKS.register("raw_dark_iron_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).strength(5.0F, 6.0F).requiresCorrectToolForDrops()));
}
