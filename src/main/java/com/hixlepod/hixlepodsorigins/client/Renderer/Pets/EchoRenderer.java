package com.hixlepod.hixlepodsorigins.client.Renderer.Pets;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.Pets.EchoModel;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityEcho;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EchoRenderer<Type extends EntityEcho> extends MobRenderer<Type, EchoModel<Type>> {

    //echo_overclocked.png
    private static final ResourceLocation ECHO_TEXTURE = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/echo.png");

    private static final ResourceLocation ECHO_OVERCLOCKED_TEXTURE = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/echo_overclocked.png");

    public EchoRenderer(EntityRendererProvider.Context context) {
        super(context, new EchoModel<>(context.bakeLayer(EchoModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return ECHO_TEXTURE;
    }

    @Override
    protected void scale(EntityEcho p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(0.825F, 0.825F, 0.825F);
    }
}
