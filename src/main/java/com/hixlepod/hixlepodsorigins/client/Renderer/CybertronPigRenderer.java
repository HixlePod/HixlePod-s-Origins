package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.CybertronPigModel;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronPig;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CybertronPigRenderer extends MobRenderer<EntityCybertronPig, CybertronPigModel<EntityCybertronPig>> {
    private static final ResourceLocation CYBER_PIG_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/cyber_pig.png");

    public CybertronPigRenderer(EntityRendererProvider.Context p_174340_) {
        super(p_174340_, new CybertronPigModel<>(p_174340_.bakeLayer(ModelLayers.PIG)), 0.7F);

    }

    public ResourceLocation getTextureLocation(EntityCybertronPig p_115697_) {
        return CYBER_PIG_LOCATION;
    }
}
