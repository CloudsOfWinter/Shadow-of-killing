package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherenderman;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.clouds_of_winter.shadow_of_killing.client.model.WitherEndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherEndermanEyesLayer<T extends LivingEntity> extends EyesLayer<T, WitherEndermanModel<T>> {
    private static final RenderType WITHER_ENDERMAN_EYES = RenderType.eyes(new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/wither_enderman/wither_enderman_eyes.png"));

    public WitherEndermanEyesLayer(RenderLayerParent<T, WitherEndermanModel<T>> p_116964_) {
        super(p_116964_);
    }

    public RenderType renderType() {
        return WITHER_ENDERMAN_EYES;
    }
}
