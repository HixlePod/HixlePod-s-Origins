package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CybertronCowModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CybertronHostileCowModel;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronCow;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronHostileCow;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CybertronHostileCowRenderer extends MobRenderer<EntityCybertronHostileCow, CybertronHostileCowModel<EntityCybertronHostileCow>> {
    private static final ResourceLocation CYBER_HOSTILE_COW_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/hostiles/cybertron_hostile_cow.png");

    public CybertronHostileCowRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new CybertronHostileCowModel<>(p_173956_.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public ResourceLocation getTextureLocation(EntityCybertronHostileCow p_114029_) {
        return CYBER_HOSTILE_COW_LOCATION;
    }
}
