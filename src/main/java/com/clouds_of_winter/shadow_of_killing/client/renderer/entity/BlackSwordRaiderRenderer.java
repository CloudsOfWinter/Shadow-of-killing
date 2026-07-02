package com.clouds_of_winter.shadow_of_killing.client.renderer.entity;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.init.ModLayer;
import com.clouds_of_winter.shadow_of_killing.client.model.BlackSwordRaiderModel;
import com.clouds_of_winter.shadow_of_killing.entity.BlackSwordRaider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackSwordRaiderRenderer extends HumanoidMobRenderer<BlackSwordRaider, BlackSwordRaiderModel<BlackSwordRaider>> {
    private static final ResourceLocation BLACK_SWORD_RAIDER_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/black_sword_raider.png");


    public BlackSwordRaiderRenderer(EntityRendererProvider.Context p_174380_) {
        this(p_174380_, ModLayer.BLACK_SWORD_RAIDER, ModLayer.BLACK_SWORD_RAIDER_INNER_ARMOR, ModLayer.BLACK_SWORD_RAIDER_OUTER_ARMOR);
    }

    public BlackSwordRaiderRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_, new BlackSwordRaiderModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new BlackSwordRaiderModel(p_174382_.bakeLayer(p_174384_)), new BlackSwordRaiderModel(p_174382_.bakeLayer(p_174385_))));
    }


    @Override
    public ResourceLocation getTextureLocation(BlackSwordRaider p_114482_) {
        return BLACK_SWORD_RAIDER_LOCATION;
    }
}
