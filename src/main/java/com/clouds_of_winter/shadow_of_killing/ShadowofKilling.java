package com.clouds_of_winter.shadow_of_killing;

import com.clouds_of_winter.shadow_of_killing.init.*;
import com.clouds_of_winter.shadow_of_killing.entity.*;

import com.clouds_of_winter.shadow_of_killing.event.notnecessary.raider.EntityEvents;
import com.clouds_of_winter.shadow_of_killing.handlers.PlayerCooldownEvent;
import com.clouds_of_winter.shadow_of_killing.recipes.brewing.ModBrewingRecipe;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// "  /data merge entity @e[type=shadow_of_killing:wither_zombie,limit=1,sort=nearest] {PersistenceRequired:1}  "
@Mod(ShadowofKilling.MOD_ID)
public class ShadowofKilling
{
    public static final String MOD_ID = "shadow_of_killing";//1.bigspecialmob 2.shadow_of_plundering_and_killing

    public ShadowofKilling()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);


        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);

        ModPotions.POTIONS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);

        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        ModOreFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModOrePlacements.PLACED_FEATURE_REGISTRY.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(EntityEvents.class);

        MinecraftForge.EVENT_BUS.register(this);

    }


    @SubscribeEvent
    public void onPlayerCooldown(PlayerCooldownEvent event) {
        // 在客户端应用相同的冷却效果
        Player player = event.getEntity();
        if (player.level.isClientSide) {
            player.getCooldowns().addCooldown(event.getItem(), event.getCooldown());
        }
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.ELITE_WITHER_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.ELITE_WITHER_ZOMBIE_ARCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntities.WITHER_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.WITHER_ZOMBIE_ARCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.WITHER_ZOMBIE_GUARD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntities.ELITE_SKELETON_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.ELITE_SKELETON_ZOMBIE_ARCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntities.SKELETON_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.SKELETON_ZOMBIE_ARCHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.SKELETON_ZOMBIE_GUARD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntities.DARK_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.WITHER_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.BLACK_SWORD_RAIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntities.RAIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.FIRE_RESISTANCE, Items.FIRE_CHARGE, ModPotions.COLD_RESISTANT.get()));
            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.LONG_FIRE_RESISTANCE, Items.FIRE_CHARGE, ModPotions.LONG_COLD_RESISTANT.get()));

            BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(ModPotions.COLD_RESISTANT.get(), Items.REDSTONE, ModPotions.LONG_COLD_RESISTANT.get()));
        });

    }



    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void allattribute(EntityAttributeCreationEvent event) {
            event.put(ModEntities.ELITE_WITHER_ZOMBIE.get(), EliteWitherZombie.createAttributes().build());
            event.put(ModEntities.ELITE_WITHER_ZOMBIE_ARCHER.get(), EliteWitherZombieArcher.createAttributes().build());


            event.put(ModEntities.WITHER_ZOMBIE.get(), WitherZombie.createAttributes().build());
            event.put(ModEntities.WITHER_ZOMBIE_ARCHER.get(), WitherZombieArcher.createAttributes().build());
            event.put(ModEntities.WITHER_ZOMBIE_GUARD.get(), WitherZombieArcher.createAttributes().build());

            event.put(ModEntities.ELITE_SKELETON_ZOMBIE.get(), EliteSkeletonZombie.createAttributes().build());
            event.put(ModEntities.ELITE_SKELETON_ZOMBIE_ARCHER.get(), EliteSkeletonZombieArcher.createAttributes().build());

            event.put(ModEntities.SKELETON_ZOMBIE.get(), SkeletonZombie.createAttributes().build());
            event.put(ModEntities.SKELETON_ZOMBIE_ARCHER.get(), SkeletonZombieArcher.createAttributes().build());
            event.put(ModEntities.SKELETON_ZOMBIE_GUARD.get(), SkeletonZombieArcher.createAttributes().build());

            event.put(ModEntities.DARK_ZOMBIE.get(), DarkZombie.createAttributes().build());
            event.put(ModEntities.WITHER_ENDERMAN.get(), WitherEnderman.createAttributes().build());
            event.put(ModEntities.BLACK_SWORD_RAIDER.get(), BlackSwordRaider.createAttributes().build());
            event.put(ModEntities.RAIDER.get(), Raider.createAttributes().build());
        }

    }
}