package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.darkzombie;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DarkZombieEyesLayer<T extends Zombie> extends EyesLayer<T, ZombieModel<T>> {
    public DarkZombieEyesLayer(RenderLayerParent<T, ZombieModel<T>> p_116981_) {
        super(p_116981_);
    }

    private static final RenderType DARK_ZOMBIE_EYES = RenderType.eyes(new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/dark_zombie/dark_zombie_eyes.png"));

    @Override
    public RenderType renderType() {
        return DARK_ZOMBIE_EYES;
    }
}
