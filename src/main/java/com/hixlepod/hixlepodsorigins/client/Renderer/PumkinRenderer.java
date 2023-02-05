package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.EchoModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.PumkinModel;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityEcho;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityPumkin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PumkinRenderer<Type extends EntityPumkin> extends MobRenderer<Type, PumkinModel<Type>> {

    private static final ResourceLocation texture = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/pumkin.png");

    public PumkinRenderer(EntityRendererProvider.Context context) {
        super(context, new PumkinModel<>(context.bakeLayer(PumkinModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return texture;
    }
}
