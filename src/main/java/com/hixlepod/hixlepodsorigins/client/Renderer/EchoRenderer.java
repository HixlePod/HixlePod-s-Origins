package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.EchoModel;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityEcho;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EchoRenderer<Type extends EntityEcho> extends MobRenderer<Type, EchoModel<Type>> {

    private static final ResourceLocation texture = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/echo.png");

    public EchoRenderer(EntityRendererProvider.Context context) {
        super(context, new EchoModel<>(context.bakeLayer(EchoModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return texture;
    }
}
