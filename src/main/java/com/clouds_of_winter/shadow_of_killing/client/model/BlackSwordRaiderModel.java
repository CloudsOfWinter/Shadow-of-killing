package com.clouds_of_winter.shadow_of_killing.client.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackSwordRaiderModel<T extends Monster> extends HumanoidModel<T> {
	public BlackSwordRaiderModel(ModelPart p_170677_) {
		super(p_170677_);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(T p_103798_, float p_103799_, float p_103800_, float p_103801_, float p_103802_, float p_103803_) {
		super.setupAnim(p_103798_, p_103799_, p_103800_, p_103801_, p_103802_, p_103803_);
		if (p_103798_.isAggressive()) {
			float f = Mth.sin(this.attackTime * (float)Math.PI);
			float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = (-(float)Math.PI / 1.5F);
			this.leftArm.xRot = (-(float)Math.PI / 1.5F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			AnimationUtils.bobArms(this.rightArm, this.leftArm, p_103801_);
		}

	}
}