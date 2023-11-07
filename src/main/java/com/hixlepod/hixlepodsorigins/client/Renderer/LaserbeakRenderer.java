package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.LaswerbeakModel;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.Laserbeak.EntityLaserbeak;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LaserbeakRenderer<T extends EntityLaserbeak> extends MobRenderer<T, LaswerbeakModel<T>> {

    private static final ResourceLocation LASERBEAK_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/hostiles/laserbeak.png");

    public LaserbeakRenderer(EntityRendererProvider.Context context) {
        super(context, new LaswerbeakModel<>(context.bakeLayer(LaswerbeakModel.LAYER_LOCATION)), 0.8F);
    }

    public ResourceLocation getTextureLocation(EntityLaserbeak p_113972_) {
        return LASERBEAK_LOCATION;
    }

    // It's not x, y, and z. It's Pitch, Yaw and roll. I just can't be asked to figure it out.
    @Override
    protected void setupRotations(T entity, PoseStack poseStack, float x, float y, float z) {
        super.setupRotations(entity, poseStack, x, y, z);
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));
    }
}
