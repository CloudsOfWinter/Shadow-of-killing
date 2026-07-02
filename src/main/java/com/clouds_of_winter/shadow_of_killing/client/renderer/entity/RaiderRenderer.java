package com.clouds_of_winter.shadow_of_killing.client.renderer.entity;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.init.ModLayer;
import com.clouds_of_winter.shadow_of_killing.client.model.RaiderModel;
import com.clouds_of_winter.shadow_of_killing.entity.Raider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaiderRenderer extends HumanoidMobRenderer<Raider, RaiderModel<Raider>> {
    private static final ResourceLocation RAIDER_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/raider.png");

    public RaiderRenderer(EntityRendererProvider.Context p_174380_) {
        this(p_174380_, ModLayer.RAIDER, ModLayer.RAIDER_INNER_ARMOR, ModLayer.RAIDER_OUTER_ARMOR);
    }

    public RaiderRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_, new RaiderModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new RaiderModel(p_174382_.bakeLayer(p_174384_)), new RaiderModel(p_174382_.bakeLayer(p_174385_))));
    }


    @Override
    public ResourceLocation getTextureLocation(Raider p_114482_) {
        return RAIDER_LOCATION;
    }
}
