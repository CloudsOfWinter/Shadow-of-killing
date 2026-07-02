package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.witherenderman;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.clouds_of_winter.shadow_of_killing.client.model.WitherEndermanModel;
import com.clouds_of_winter.shadow_of_killing.entity.WitherEnderman;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;

public class WitherEndermanCarriedBlockLayer extends RenderLayer<WitherEnderman, WitherEndermanModel<WitherEnderman>> {
    private final BlockRenderDispatcher blockRenderer;

    public WitherEndermanCarriedBlockLayer(RenderLayerParent<WitherEnderman, WitherEndermanModel<WitherEnderman>> p_234814_, BlockRenderDispatcher p_234815_) {
        super(p_234814_);
        this.blockRenderer = p_234815_;
    }

    public void render(PoseStack p_116639_, MultiBufferSource p_116640_, int p_116641_, WitherEnderman p_116642_, float p_116643_, float p_116644_, float p_116645_, float p_116646_, float p_116647_, float p_116648_) {
        BlockState blockstate = p_116642_.getCarriedBlock();
        if (blockstate != null) {
            p_116639_.pushPose();
            p_116639_.translate(0.0D, 0.6875D, -0.75D);
            p_116639_.mulPose(Vector3f.XP.rotationDegrees(20.0F));
            p_116639_.mulPose(Vector3f.YP.rotationDegrees(45.0F));
            p_116639_.translate(0.25D, 0.1875D, 0.25D);
            float f = 0.5F;
            p_116639_.scale(-0.5F, -0.5F, 0.5F);
            p_116639_.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            this.blockRenderer.renderSingleBlock(blockstate, p_116639_, p_116640_, p_116641_, OverlayTexture.NO_OVERLAY);
            p_116639_.popPose();
        }
    }
}
