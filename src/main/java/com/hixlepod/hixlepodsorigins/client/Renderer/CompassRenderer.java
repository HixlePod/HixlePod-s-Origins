package com.hixlepod.hixlepodsorigins.client.Renderer;


import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CompassModel;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityCompass;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ambient.Bat;

public class CompassRenderer extends MobRenderer<EntityCompass, CompassModel> {

    private static final ResourceLocation texture = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/compass.png");

    public CompassRenderer(EntityRendererProvider.Context context) {
        super(context, new CompassModel(context.bakeLayer(CompassModel.LAYER_LOCATION)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCompass entity) {
        return texture;
    }

    protected void scale(EntityCompass p_113878_, PoseStack p_113879_, float p_113880_) {
        p_113879_.scale(0.35F, 0.35F, 0.35F);
    }

    protected void setupRotations(EntityCompass p_113882_, PoseStack p_113883_, float p_113884_, float p_113885_, float p_113886_) {
        if (p_113882_.isResting()) {
            p_113883_.translate(0.0D, (double)-0.1F, 0.0D);
        } else {
            p_113883_.translate(0.0D, (double)(Mth.cos(p_113884_ * 0.3F) * 0.1F), 0.0D);
        }

        super.setupRotations(p_113882_, p_113883_, p_113884_, p_113885_, p_113886_);
    }
}
