package com.hixlepod.hixlepodsorigins.client.Renderer.Projectile;


import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.Projectile.LaserProjectileModel;
import com.hixlepod.hixlepodsorigins.common.Entities.Projectile.EntityLaserProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LaserProjectileRenderer extends EntityRenderer<EntityLaserProjectile> {

    private static final ResourceLocation LASER_PROJECTILE_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/projectile/laser_projectile.png");
    private final LaserProjectileModel model;

    private boolean Foil = true;

    public LaserProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new LaserProjectileModel(context.bakeLayer(LaserProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(EntityLaserProjectile entity, float p_114486_, float p_114487_, PoseStack poseStack, MultiBufferSource p_114489_, int p_114490_) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_114487_, entity.yRotO, entity.getYRot())));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_114487_, entity.xRotO, entity.getXRot())));
        VertexConsumer $$6 = ItemRenderer.getFoilBufferDirect(p_114489_, this.model.renderType(this.getTextureLocation(entity)), false, Foil);
        this.model.renderToBuffer(poseStack, $$6, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, p_114486_, p_114487_, poseStack, p_114489_, p_114490_);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityLaserProjectile t) {
        return LASER_PROJECTILE_LOCATION;
    }
}
