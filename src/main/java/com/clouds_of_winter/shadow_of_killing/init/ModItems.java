package com.clouds_of_winter.shadow_of_killing.init;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.item.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ShadowofKilling.MOD_ID);

    public static final RegistryObject<Item> ORANGE_CRYSTAL = ITEMS.register("orange_crystal", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_SCRAP = ITEMS.register("orange_crystal_scrap", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_APPLE = ITEMS.register("orange_crystal_apple", () -> new OrangeCrystalApple(new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).food(ModFoods.ORANGE_CRYSTAL_APPLE).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> DARK_IRON_INGOT  = ITEMS.register("dark_iron_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> RAW_DARK_IRON  = ITEMS.register("raw_dark_iron", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_NUGGET = ITEMS.register("dark_iron_nugget", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));


    public static final RegistryObject<Item> ORANGE_CRYSTAL_BLOCK = ITEMS.register("orange_crystal_block", () -> new BlockItem(ModBlocks.ORANGE_CRYSTAL_BLOCK.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_ORE = ITEMS.register("orange_crystal_ore", () -> new BlockItem(ModBlocks.ORANGE_CRYSTAL_ORE.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> DEEPSLATE_ORANGE_CRYSTAL_ORE = ITEMS.register("deepslate_orange_crystal_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_ORANGE_CRYSTAL_ORE.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> DARK_IRON_BLOCK = ITEMS.register("dark_iron_block", () -> new BlockItem(ModBlocks.DARK_IRON_BLOCK.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_ORE = ITEMS.register("dark_iron_ore", () -> new BlockItem(ModBlocks.DARK_IRON_ORE.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DEEPSLATE_DARK_IRON_ORE = ITEMS.register("deepslate_dark_iron_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_DARK_IRON_ORE.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> RAW_DARK_IRON_BLOCK = ITEMS.register("raw_dark_iron_block", () -> new BlockItem(ModBlocks.RAW_DARK_IRON_BLOCK.get(), new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));


    public static final RegistryObject<Item> ORANGE_CRYSTAL_SWORD = ITEMS.register("orange_crystal_sword", () -> new SwordItem(ModTools.ORANGE_CRYSTAL, 3, -2.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_SHOVEL = ITEMS.register("orange_crystal_shovel", () -> new ShovelItem(ModTools.ORANGE_CRYSTAL, 1.5F, -2.6F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_PICKAXE = ITEMS.register("orange_crystal_pickaxe", () -> new PickaxeItem(ModTools.ORANGE_CRYSTAL, 1, -2.6F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_AXE = ITEMS.register("orange_crystal_axe", () -> new AxeItem(ModTools.ORANGE_CRYSTAL, 5.0F, -2.7F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));

    public static final RegistryObject<Item> ORANGE_CRYSTAL_HOE = ITEMS.register("orange_crystal_hoe", () -> new HoeItem(ModTools.ORANGE_CRYSTAL, 1, 1.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> STARDEMON_BATTLEAXE = ITEMS.register("stardemon_battleaxe", () -> new AxeItem(ModTools.BATTLEAXE, 22.0F, -3.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> SOUL_SHATTERING_WARHAMMER = ITEMS.register("soul_shattering_warhammer", () -> new AxeItem(ModTools.WARHAMMER, 16.0F, -2.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_SWORD = ITEMS.register("dark_iron_sword", () -> new SwordItem(ModTools.DARK_IRON, 3, -1.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_SHOVEL = ITEMS.register("dark_iron_shovel", () -> new ShovelItem(ModTools.DARK_IRON, 1.5F, -2.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_PICKAXE = ITEMS.register("dark_iron_pickaxe", () -> new PickaxeItem(ModTools.DARK_IRON, 1, -1.8F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_AXE = ITEMS.register("dark_iron_axe", () -> new AxeItem(ModTools.DARK_IRON, 5.0F, -2.4F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_HOE = ITEMS.register("dark_iron_hoe", () -> new HoeItem(ModTools.DARK_IRON, -4, 1.0F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> BLACK_SWORD = ITEMS.register("black_sword", () -> new SwordItem(ModTools.BLACK_SWORD, 30, -2.1F,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));


    public static final RegistryObject<Item> ORANGE_CRYSTAL_HELMET = ITEMS.register("orange_crystal_helmet", () -> new OrangeCrystalHelmet(ModArmors.ORANGE_CRYSTAL, EquipmentSlot.HEAD,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_CHESTPLATE = ITEMS.register("orange_crystal_chestplate", () -> new OrangeCrystalChestplate(ModArmors.ORANGE_CRYSTAL, EquipmentSlot.CHEST,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_LEGGINGS = ITEMS.register("orange_crystal_leggings", () -> new OrangeCrystalLeggings(ModArmors.ORANGE_CRYSTAL, EquipmentSlot.LEGS,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_BOOTS = ITEMS.register("orange_crystal_boots", () -> new OrangeCrystalBoots(ModArmors.ORANGE_CRYSTAL, EquipmentSlot.FEET,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP).fireResistant()));
    public static final RegistryObject<Item> DARK_IRON_HELMET = ITEMS.register("dark_iron_helmet", () -> new ArmorItem(ModArmors.DARK_IRON, EquipmentSlot.HEAD,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_CHESTPLATE = ITEMS.register("dark_iron_chestplate", () -> new ArmorItem(ModArmors.DARK_IRON, EquipmentSlot.CHEST,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRON_LEGGINGS = ITEMS.register("dark_iron_leggings", () -> new ArmorItem(ModArmors.DARK_IRON, EquipmentSlot.LEGS,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_IRONL_BOOTS = ITEMS.register("dark_iron_boots", () -> new ArmorItem(ModArmors.DARK_IRON, EquipmentSlot.FEET,new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));


    public static final RegistryObject<Item> ELITE_WITHER_ZOMBIE_SPAWN_EGG = ITEMS.register("elite_wither_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ELITE_WITHER_ZOMBIE, 7542554, 6116957, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> ELITE_WITHER_ZOMBIE_ARCHER_SPAWN_EGG = ITEMS.register("elite_wither_zombie_archer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ELITE_WITHER_ZOMBIE_ARCHER, 7542554, 6116957, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> WITHER_ZOMBIE_SPAWN_EGG = ITEMS.register("wither_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WITHER_ZOMBIE, 1601385, 3948865, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> WITHER_ZOMBIE_ARCHER_SPAWN_EGG = ITEMS.register("wither_zombie_archer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WITHER_ZOMBIE_ARCHER, 1601385, 3948865, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> WITHER_ZOMBIE_GUARD_SPAWN_EGG = ITEMS.register("wither_zombie_guard_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WITHER_ZOMBIE_GUARD, 1601385, 3948865, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> ELITE_SKELETON_ZOMBIE_SPAWN_EGG = ITEMS.register("elite_skeleton_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ELITE_SKELETON_ZOMBIE, 8523521, 12698049, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> ELITE_SKELETON_ZOMBIE_ARCHER_SPAWN_EGG = ITEMS.register("elite_skeleton_zombie_archer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ELITE_SKELETON_ZOMBIE_ARCHER, 8523521, 12698049, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> SKELETON_ZOMBIE_SPAWN_EGG = ITEMS.register("skeleton_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SKELETON_ZOMBIE, 1410446, 12698049, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> SKELETON_ZOMBIE_ARCHER_SPAWN_EGG = ITEMS.register("skeleton_zombie_archer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SKELETON_ZOMBIE_ARCHER, 1410446, 12698049, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> SKELETON_ZOMBIE_GUARD_SPAWN_EGG = ITEMS.register("skeleton_zombie_guard_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SKELETON_ZOMBIE_GUARD, 1410446, 12698049, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_ZOMBIE_SPAWN_EGG = ITEMS.register("dark_zombie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DARK_ZOMBIE, 9144715, 5921114, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> WITHER_ENDERMAN_SPAWN_EGG = ITEMS.register("wither_enderman_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.WITHER_ENDERMAN, 264208, 2556087, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> BLACK_SWORD_RAIDER_SPAWN_EGG = ITEMS.register("black_sword_raider_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.BLACK_SWORD_RAIDER, 2171169, 10354688, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));
    public static final RegistryObject<Item> RAIDER_SPAWN_EGG = ITEMS.register("raider_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.RAIDER, 2171169, 8883857, new Item.Properties().tab(ModCreativeModeTab.SHADOW_OF_KILLING_ITEM_GROUP)));


    public static final RegistryObject<Item> SHADOW_OF_KILLING_ITEM_GROUP = ITEMS.register("shadow_of_killing_item_group", () -> new Item(new Item.Properties()));


}
