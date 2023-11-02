package com.hixlepod.hixlepodsorigins.client.Model;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LaswerbeakModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID, "laserbeak"), "main");
    private final ModelPart bone;

    public LaswerbeakModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 8).addBox(-4.0F, -3.5F, -6.0F, 8.0F, 1.75F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(14, 24).addBox(-1.0F, -3.25F, -2.0F, 2.0F, 0.25F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-2.0F, -3.75F, -8.0F, 4.0F, 1.75F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 14).addBox(4.5F, 0.0F, -1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2426F, -3.0F, -8.1213F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 3).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7071F, -3.0F, -3.1716F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 18).addBox(-7.0F, -0.125F, 4.0F, 2.0F, 0.5F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.7782F, -2.625F, 5.7071F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -0.125F, -3.0F, 2.0F, 0.5F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7071F, -2.625F, 7.1213F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return bone;
    }
}
