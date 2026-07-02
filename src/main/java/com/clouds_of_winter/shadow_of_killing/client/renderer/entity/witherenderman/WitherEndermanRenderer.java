package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherenderman;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.mojang.blaze3d.vertex.PoseStack;
import com.clouds_of_winter.shadow_of_killing.init.ModLayer;
import com.clouds_of_winter.shadow_of_killing.client.model.WitherEndermanModel;
import com.clouds_of_winter.shadow_of_killing.entity.WitherEnderman;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherEndermanRenderer extends MobRenderer<WitherEnderman, WitherEndermanModel<WitherEnderman>> {
    private static final ResourceLocation WITHER_ENDERMAN_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/wither_enderman/wither_enderman.png");
    private final RandomSource random = RandomSource.create();

    public WitherEndermanRenderer(EntityRendererProvider.Context p_173992_) {
        super(p_173992_, new WitherEndermanModel<>(p_173992_.bakeLayer(ModLayer.WITHER_ENDERMAN)), 0.5F);
        this.addLayer(new WitherEndermanEyesLayer<>(this));
        this.addLayer(new WitherEndermanCarriedBlockLayer(this, p_173992_.getBlockRenderDispatcher()));
    }



    public void render(WitherEnderman p_114339_, float p_114340_, float p_114341_, PoseStack p_114342_, MultiBufferSource p_114343_, int p_114344_) {
        BlockState blockstate = p_114339_.getCarriedBlock();
        WitherEndermanModel<WitherEnderman> witherendermanmodel = this.getModel();
        witherendermanmodel.carrying = blockstate != null;
        witherendermanmodel.creepy = p_114339_.isCreepy();
        super.render(p_114339_, p_114340_, p_114341_, p_114342_, p_114343_, p_114344_);
    }

    public Vec3 getRenderOffset(WitherEnderman p_114336_, float p_114337_) {
        if (p_114336_.isCreepy()) {
            double d0 = 0.02D;
            return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(p_114336_, p_114337_);
        }
    }

    public ResourceLocation getTextureLocation(WitherEnderman p_114482_) {
        return WITHER_ENDERMAN_LOCATION;
    }

}
