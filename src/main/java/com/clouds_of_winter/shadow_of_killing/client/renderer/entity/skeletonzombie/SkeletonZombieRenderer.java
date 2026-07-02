package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.skeletonzombie;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletonZombieRenderer extends SkeletonRenderer {
    private static final ResourceLocation SKELETON_ZOMBIE_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/skeleton_zombie/skeleton_zombie.png");

    public SkeletonZombieRenderer(EntityRendererProvider.Context p_174380_) {
        super(p_174380_);
        this.addLayer(new SkeletonZombieClothingLayer<>(this, p_174380_.getModelSet()));
    }

    public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
        return SKELETON_ZOMBIE_LOCATION;
    }
}
