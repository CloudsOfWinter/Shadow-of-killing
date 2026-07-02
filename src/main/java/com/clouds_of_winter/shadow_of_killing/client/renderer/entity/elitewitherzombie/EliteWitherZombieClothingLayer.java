package com.clouds_of_winter.shadow_of_killing.client.renderer.entity.elitewitherzombie;

import com.clouds_of_winter.shadow_of_killing.ShadowofKilling;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EliteWitherZombieClothingLayer<T extends Mob & RangedAttackMob, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation ELITE_WITHER_ZOMBIE_CLOTHES_LOCATION = new ResourceLocation(ShadowofKilling.MOD_ID, "textures/entity/elite_wither_zombie/elite_wither_zombie_overlay.png");
    private final SkeletonModel<T> layerModel;

    public EliteWitherZombieClothingLayer(RenderLayerParent<T, M> p_174544_, EntityModelSet p_174545_) {
        super(p_174544_);
        this.layerModel = new SkeletonModel<>(p_174545_.bakeLayer(ModelLayers.STRAY_OUTER_LAYER));
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isInvisible()) {
            Player clientPlayer = Minecraft.getInstance().player;

            if (clientPlayer != null && clientPlayer.isSpectator()) {
                renderLayerWithAlpha(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTick, 0.15F);
            }
        } else {
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.layerModel, ELITE_WITHER_ZOMBIE_CLOTHES_LOCATION, poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTick, 1.0F, 1.0F, 1.0F
            );
        }
    }
    private void renderLayerWithAlpha(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTick, float alpha) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityTranslucent(ELITE_WITHER_ZOMBIE_CLOTHES_LOCATION));

        poseStack.pushPose();

        this.getParentModel().copyPropertiesTo(this.layerModel);
        this.layerModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
        this.layerModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.layerModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);

        poseStack.popPose();
    }
}
