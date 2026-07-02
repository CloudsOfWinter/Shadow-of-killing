package com.clouds_of_winter.shadow_of_killing.client;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.init.ModLayer;
import com.clouds_of_winter.shadow_of_killing.client.model.BlackSwordRaiderModel;
import com.clouds_of_winter.shadow_of_killing.client.model.RaiderModel;
import com.clouds_of_winter.shadow_of_killing.client.model.WitherEndermanModel;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.BlackSwordRaiderRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.RaiderRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.darkzombie.DarkZombieRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.eliteskeletonzombie.EliteSkeletonZombieRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.eliteskeletonzombie.EliteSkeletonZombieArcherRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.elitewitherzombie.EliteWitherZombieRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.elitewitherzombie.EliteWitherZombieArcherRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.skeletonzombie.SkeletonZombieRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.skeletonzombie.SkeletonZombieArcherRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherenderman.WitherEndermanRenderer;
import com.clouds_of_winter.shadow_of_killing.init.ModEntities;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherzombie.WitherZombieRenderer;
import com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherzombie.WitherZombieArcherRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.WitherSkullRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ShadowofKilling.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ShadowofKillingClient {
    @SubscribeEvent
    public static void allrenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ELITE_WITHER_ZOMBIE.get(), EliteWitherZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.ELITE_WITHER_ZOMBIE_ARCHER.get(), EliteWitherZombieArcherRenderer::new);


        event.registerEntityRenderer(ModEntities.WITHER_ZOMBIE.get(), WitherZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHER_ZOMBIE_ARCHER.get(), WitherZombieArcherRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHER_ZOMBIE_GUARD.get(), WitherZombieArcherRenderer::new);


        event.registerEntityRenderer(ModEntities.ELITE_SKELETON_ZOMBIE.get(), EliteSkeletonZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.ELITE_SKELETON_ZOMBIE_ARCHER.get(), EliteSkeletonZombieArcherRenderer::new);

        event.registerEntityRenderer(ModEntities.SKELETON_ZOMBIE.get(), SkeletonZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.SKELETON_ZOMBIE_ARCHER.get(), SkeletonZombieArcherRenderer::new);
        event.registerEntityRenderer(ModEntities.SKELETON_ZOMBIE_GUARD.get(), SkeletonZombieArcherRenderer::new);

        event.registerEntityRenderer(ModEntities.DARK_ZOMBIE.get(), DarkZombieRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHER_ENDERMAN.get(), WitherEndermanRenderer::new);
        event.registerEntityRenderer(ModEntities.BLACK_SWORD_RAIDER.get(), BlackSwordRaiderRenderer::new);
        event.registerEntityRenderer(ModEntities.RAIDER.get(), RaiderRenderer::new);


        event.registerEntityRenderer(ModEntities.GHOST_ATTACK.get(), WitherSkullRenderer::new);
    }

    @SubscribeEvent
    public static void alllayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModLayer.WITHER_ENDERMAN,WitherEndermanModel::createBodyLayer);
        event.registerLayerDefinition(ModLayer.BLACK_SWORD_RAIDER, BlackSwordRaiderModel::createBodyLayer);
        event.registerLayerDefinition(ModLayer.BLACK_SWORD_RAIDER_INNER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32));
        event.registerLayerDefinition(ModLayer.BLACK_SWORD_RAIDER_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32));
        event.registerLayerDefinition(ModLayer.RAIDER, RaiderModel::createBodyLayer);
        event.registerLayerDefinition(ModLayer.RAIDER_INNER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32));
        event.registerLayerDefinition(ModLayer.RAIDER_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32));
    }


}