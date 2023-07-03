package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.RuneModel;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityRune;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Blaze;

public class RuneRenderer extends MobRenderer<EntityRune, RuneModel<EntityRune>> {
    private static final ResourceLocation texture = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/rune.png");

    public RuneRenderer(EntityRendererProvider.Context context) {
        super(context, new RuneModel<>(context.bakeLayer(RuneModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityRune entity) {
        return texture;
    }

    protected int getBlockLightLevel(Blaze p_113910_, BlockPos p_113911_) {
        return 15;
    }
}
