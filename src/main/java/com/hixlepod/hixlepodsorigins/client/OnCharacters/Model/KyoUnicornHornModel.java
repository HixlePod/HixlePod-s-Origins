package com.hixlepod.hixlepodsorigins.client.OnCharacters.Model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.player.Player;

public class KyoUnicornHornModel<T extends Player> extends AgeableListModel<T> {

    private final ModelPart horn;

    public KyoUnicornHornModel(ModelPart root) {
        this.horn = root.getChild("horn");
    }

    public ModelPart getHorn() {
        return horn;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition horn = partdefinition.addOrReplaceChild("horn", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = horn.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 0).addBox(-0.75F, -1.0F, -0.75F, 1.5F, 2.0F, 1.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5584F, -3.0325F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r2 = horn.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.511F, -3.4654F, 0.2182F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        horn.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.horn);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }
}
