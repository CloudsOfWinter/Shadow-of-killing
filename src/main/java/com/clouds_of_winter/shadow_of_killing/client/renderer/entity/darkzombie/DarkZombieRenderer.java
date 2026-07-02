package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.darkzombie;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DarkZombieRenderer extends ZombieRenderer {
    private static final ResourceLocation DARK_ZOMBIE_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/dark_zombie/dark_zombie.png");

    public DarkZombieRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_);
        this.addLayer(new DarkZombieEyesLayer<>(this));
    }

    public ResourceLocation getTextureLocation(Zombie p_113771_) {
        return DARK_ZOMBIE_LOCATION;
    }
}
