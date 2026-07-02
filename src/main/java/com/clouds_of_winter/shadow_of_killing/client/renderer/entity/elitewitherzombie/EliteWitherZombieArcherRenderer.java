package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.elitewitherzombie;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EliteWitherZombieArcherRenderer extends WitherSkeletonRenderer {
    private static final ResourceLocation ELITE_WITHER_ZOMBIE_SHOOTER_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/elite_wither_zombie/elite_wither_zombie.png");

    public EliteWitherZombieArcherRenderer(EntityRendererProvider.Context p_174447_) {
        super(p_174447_);
        this.addLayer(new EliteWitherZombieClothingLayer<>(this, p_174447_.getModelSet()));
    }

    public ResourceLocation getTextureLocation(AbstractSkeleton p_116458_) {
        return ELITE_WITHER_ZOMBIE_SHOOTER_LOCATION;
    }
}
