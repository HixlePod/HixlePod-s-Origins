package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.TenModel;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityTen;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TenRenderer extends MobRenderer<EntityTen, TenModel<EntityTen>> {
    private static final ResourceLocation TEN_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/ten.png");

    public TenRenderer(EntityRendererProvider.Context p_174188_) {
        super(p_174188_, new TenModel<>(p_174188_.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
    }

    public ResourceLocation getTextureLocation(EntityTen p_115012_) {
        return TEN_LOCATION;
    }

    protected void scale(EntityTen p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(2F, 2F, 2F);
    }

    protected void setupRotations(EntityTen p_115014_, PoseStack p_115015_, float p_115016_, float p_115017_, float p_115018_) {
        super.setupRotations(p_115014_, p_115015_, p_115016_, p_115017_, p_115018_);
        if (!((double)p_115014_.walkAnimation.speed() < 0.01)) {
            float $$5 = 13.0F;
            float $$6 = p_115014_.walkAnimation.position(p_115018_) + 6.0F;
            float $$7 = (Math.abs($$6 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            p_115015_.mulPose(Axis.ZP.rotationDegrees(6.5F * $$7));
        }
    }
}
