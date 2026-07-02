package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherzombie;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherZombieArcherRenderer extends WitherSkeletonRenderer {
    private static final ResourceLocation WITHER_ZOMBIE_SHOOTER_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/wither_zombie/wither_zombie.png");

    public WitherZombieArcherRenderer(EntityRendererProvider.Context p_174447_) {
        super(p_174447_);
        this.addLayer(new WitherZombieClothingLayer<>(this, p_174447_.getModelSet()));
    }


    public ResourceLocation getTextureLocation(AbstractSkeleton p_116458_) {
        return WITHER_ZOMBIE_SHOOTER_LOCATION;
    }
}
