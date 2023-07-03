package com.hixlepod.hixlepodsorigins.client.OnCharacters.Model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class RaccGoatHornsModel<T extends Player> extends AgeableListModel<T> {

    private final ModelPart horns1;
    private final ModelPart horns2;


    public RaccGoatHornsModel(ModelPart root) {
        this.horns1 = root.getChild("horns1");
        this.horns2 = root.getChild("horns2");
    }

    public ModelPart getHorns1() {
        return horns1;
    }

    public ModelPart getHorns2() {
        return horns2;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition horns1 = partdefinition.addOrReplaceChild("horns1", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = horns1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.2F, -4.466F, -2.4381F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -1.5272F, 0.0F, 0.0F));

        PartDefinition cube_r2 = horns1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -4.016F, -1.7381F, 1.2F, 1.0F, 1.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -1.2654F, 0.0F, 0.0F));

        PartDefinition cube_r3 = horns1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(9, 5).addBox(-0.2F, -3.716F, -1.4381F, 1.4F, 1.0F, 1.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -1.0908F, 0.0F, 0.0F));

        PartDefinition cube_r4 = horns1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 2).addBox(-0.4F, -3.316F, -1.2F, 1.6F, 1.0F, 1.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -0.9163F, 0.0F, 0.0F));

        PartDefinition cube_r5 = horns1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-0.6F, -2.816F, -1.2F, 1.8F, 1.0F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -0.829F, 0.0F, 0.0F));

        PartDefinition cube_r6 = horns1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 15).addBox(-0.8F, -2.216F, -1.2F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r7 = horns1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(6, 17).addBox(-1.0F, -1.45F, -1.2F, 2.2F, 1.0F, 2.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4165F, -10.784F, -0.5619F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r8 = horns1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(18, 12).addBox(-1.0F, -2.2F, -1.4F, 2.4F, 1.0F, 2.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.6165F, -9.3168F, -1.1003F, -0.48F, 0.0F, 0.0F));

        PartDefinition cube_r9 = horns1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 18).addBox(-1.2F, -1.4F, -1.4F, 2.6F, 1.0F, 2.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.6165F, -9.3168F, -1.1003F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r10 = horns1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(18, 7).addBox(-1.4F, -0.9832F, -1.4F, 2.8F, 1.0F, 2.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.6165F, -9.3168F, -1.1003F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r11 = horns1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 5).addBox(-1.3F, -1.5F, -1.7F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8269F, -8.3543F, -0.999F, -0.2182F, 0.0F, -0.0873F));

        PartDefinition cube_r12 = horns1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(9, 7).addBox(-1.5F, -1.5F, -1.7F, 3.2F, 2.0F, 3.2F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.6547F, -7.3779F, -1.1295F, -0.1309F, 0.0F, -0.1745F));

        PartDefinition cube_r13 = horns1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, -1.5F, -1.8F, 3.4F, 2.0F, 3.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.4989F, -6.3652F, -1.117F, -0.0873F, 0.0F, -0.3491F));

        PartDefinition cube_r14 = horns1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(9, 12).addBox(-1.9F, -2.4F, -1.9F, 3.8F, 2.0F, 3.6F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5477F, -4.7176F, -1.1F, -0.0436F, 0.0F, -0.5236F));

        PartDefinition cube_r15 = horns1.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(13, 2).addBox(-1.8F, -1.5F, -2.0F, 3.8F, 2.0F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5477F, -4.5762F, -1.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r16 = horns1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7816F, -3.9334F, -1.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition horns2 = partdefinition.addOrReplaceChild("horns2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r17 = horns2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.2F, -4.466F, -2.4381F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -1.5272F, 0.0F, 0.0F));

        PartDefinition cube_r18 = horns2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-1.2F, -4.016F, -1.7381F, 1.2F, 1.0F, 1.2F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -1.2654F, 0.0F, 0.0F));

        PartDefinition cube_r19 = horns2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(9, 5).mirror().addBox(-1.2F, -3.716F, -1.4381F, 1.4F, 1.0F, 1.4F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -1.0908F, 0.0F, 0.0F));

        PartDefinition cube_r20 = horns2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(12, 2).mirror().addBox(-1.2F, -3.316F, -1.2F, 1.6F, 1.0F, 1.6F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -0.9163F, 0.0F, 0.0F));

        PartDefinition cube_r21 = horns2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.2F, -2.816F, -1.2F, 1.8F, 1.0F, 1.8F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -0.829F, 0.0F, 0.0F));

        PartDefinition cube_r22 = horns2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-1.2F, -2.216F, -1.2F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -0.7418F, 0.0F, 0.0F));

        PartDefinition cube_r23 = horns2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(6, 17).mirror().addBox(-1.2F, -1.45F, -1.2F, 2.2F, 1.0F, 2.2F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4165F, -10.784F, -0.5619F, -0.6109F, 0.0F, 0.0F));

        PartDefinition cube_r24 = horns2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(14, 17).addBox(-1.4F, -2.2F, -1.4F, 2.4F, 1.0F, 2.4F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.6165F, -9.3168F, -1.1003F, -0.48F, 0.0F, 0.0F));

        PartDefinition cube_r25 = horns2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1.4F, -1.4F, -1.4F, 2.6F, 1.0F, 2.6F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.6165F, -9.3168F, -1.1003F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r26 = horns2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(18, 7).mirror().addBox(-1.4F, -0.9832F, -1.4F, 2.8F, 1.0F, 2.8F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.6165F, -9.3168F, -1.1003F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r27 = horns2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-1.7F, -1.5F, -1.7F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.8269F, -8.3543F, -0.999F, -0.2182F, 0.0F, 0.0873F));

        PartDefinition cube_r28 = horns2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(9, 7).mirror().addBox(-1.7F, -1.5F, -1.7F, 3.2F, 2.0F, 3.2F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.6547F, -7.3779F, -1.1295F, -0.1309F, 0.0F, 0.1745F));

        PartDefinition cube_r29 = horns2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-1.9F, -1.5F, -1.8F, 3.4F, 2.0F, 3.4F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.4989F, -6.3652F, -1.117F, -0.0873F, 0.0F, 0.3491F));

        PartDefinition cube_r30 = horns2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(9, 12).mirror().addBox(-1.9F, -2.4F, -1.9F, 3.8F, 2.0F, 3.6F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5477F, -4.7176F, -1.1F, -0.0436F, 0.0F, 0.5236F));

        PartDefinition cube_r31 = horns2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(13, 2).mirror().addBox(-2.0F, -1.5F, -2.0F, 3.8F, 2.0F, 3.8F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5477F, -4.5762F, -1.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r32 = horns2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7816F, -3.9334F, -1.0F, 0.0F, 0.0F, 0.8727F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        horns1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        horns2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.horns1, this.horns2);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }
}
