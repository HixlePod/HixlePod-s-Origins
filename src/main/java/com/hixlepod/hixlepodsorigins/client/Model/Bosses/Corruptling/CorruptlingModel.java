package com.hixlepod.hixlepodsorigins.client.Model.Bosses.Corruptling;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.Corruptling.EntityCorruptling;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.swing.text.html.parser.Entity;

public class CorruptlingModel<T extends EntityCorruptling> extends HierarchicalModel<T> {


	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID, "corruptling"), "main");
	private final ModelPart main;
	private final ModelPart head;

	public CorruptlingModel(ModelPart root) {
		this.main = root.getChild("main");
		this.head = main.getChild("body").getChild("upperbody").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -39.5F, 0.0F));

		PartDefinition upperbody = body.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(80, 0).addBox(-8.0F, -24.5F, -4.0F, 16.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition lowerbody = upperbody.addOrReplaceChild("lowerbody", CubeListBuilder.create().texOffs(88, 31).addBox(-7.0F, -7.5F, -3.0F, 14.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftarm = upperbody.addOrReplaceChild("leftarm", CubeListBuilder.create(), PartPose.offset(-9.5F, -2.25F, 0.0F));

		PartDefinition upper_arm = leftarm.addOrReplaceChild("upper_arm", CubeListBuilder.create().texOffs(49, 102).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 22.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.25F, 0.0F));

		PartDefinition lower_arm = upper_arm.addOrReplaceChild("lower_arm", CubeListBuilder.create().texOffs(1, 76).addBox(-1.5F, -0.25F, -2.0F, 3.0F, 19.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.25F, 0.0F));

		PartDefinition rightarm = upperbody.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(9.5F, -2.25F, 0.0F));

		PartDefinition upper_arm2 = rightarm.addOrReplaceChild("upper_arm2", CubeListBuilder.create().texOffs(80, 62).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 22.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.25F, 0.0F));

		PartDefinition lower_arm2 = upper_arm2.addOrReplaceChild("lower_arm2", CubeListBuilder.create().texOffs(32, 53).addBox(-1.5F, -0.25F, -2.0F, 3.0F, 19.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.25F, 0.0F));

		PartDefinition head = upperbody.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 29.5F, 0.0F));

		PartDefinition upperhead = head.addOrReplaceChild("upperhead", CubeListBuilder.create().texOffs(0, 110).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -59.0F, 0.0F));

		PartDefinition righthorns = upperhead.addOrReplaceChild("righthorns", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-0.177F, -5.6374F, -2.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.823F, -5.6374F, 0.0F));

		PartDefinition cube_r1 = righthorns.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -4.5F, -2.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.823F, 3.1374F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition lefthorns = upperhead.addOrReplaceChild("lefthorns", CubeListBuilder.create().texOffs(0, 10).addBox(-1.823F, -5.6374F, -2.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.823F, -5.6374F, 0.0F));

		PartDefinition cube_r2 = lefthorns.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -4.5F, -2.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.823F, 3.1374F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(88, 117).addBox(-5.0F, 0.0F, -9.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(106, 100).addBox(-5.0F, -4.0F, -1.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -55.0F, 4.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create(), PartPose.offset(-4.0F, 23.1667F, 0.0F));

		PartDefinition upper = leftleg.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(30, 6).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.6667F, 0.0F));

		PartDefinition middle = upper.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(12, 7).addBox(-1.5F, 0.3333F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.6667F, 0.0F));

		PartDefinition lower = middle.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(120, 68).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.3333F, 0.0F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create(), PartPose.offset(4.0F, 23.1667F, 0.0F));

		PartDefinition upper2 = rightleg.addOrReplaceChild("upper2", CubeListBuilder.create().texOffs(0, 54).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.6667F, 0.0F));

		PartDefinition middle2 = upper2.addOrReplaceChild("middle2", CubeListBuilder.create().texOffs(5, 26).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition lower2 = middle2.addOrReplaceChild("lower2", CubeListBuilder.create().texOffs(119, 49).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.main.getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(CorruptlingAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((EntityCorruptling) entity).runningAnimationState, CorruptlingAnimations.RUNNING, ageInTicks, 1f);
		this.animate(((EntityCorruptling) entity).screamingAnimationState, CorruptlingAnimations.SCREAM, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		//pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		//pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return main;
	}
}