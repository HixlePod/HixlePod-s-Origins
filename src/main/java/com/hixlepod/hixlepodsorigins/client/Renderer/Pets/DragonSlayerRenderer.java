package com.hixlepod.hixlepodsorigins.client.Renderer.Pets;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.Pets.DragonSlayerModel;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityDragonSlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DragonSlayerRenderer<Type extends EntityDragonSlayer> extends MobRenderer<Type, DragonSlayerModel<Type>> {

    private static final ResourceLocation DRAGON_SLAYER_TEXTURE = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/dragon_slayer.png");

    public DragonSlayerRenderer(EntityRendererProvider.Context context) {
        super(context, new DragonSlayerModel<>(context.bakeLayer(DragonSlayerModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return DRAGON_SLAYER_TEXTURE;
    }
}
