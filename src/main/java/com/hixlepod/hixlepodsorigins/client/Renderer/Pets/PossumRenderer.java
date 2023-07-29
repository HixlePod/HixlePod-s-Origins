package com.hixlepod.hixlepodsorigins.client.Renderer.Pets;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.Pets.EchoModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.Pets.PossumModel;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityEcho;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityPossum;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PossumRenderer<Type extends EntityPossum> extends MobRenderer<Type, PossumModel<Type>> {
    private static final ResourceLocation POSSUM_TEXTURE = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/possum.png");

    public PossumRenderer(EntityRendererProvider.Context context) {
        super(context, new PossumModel<>(context.bakeLayer(PossumModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return POSSUM_TEXTURE;
    }
}
