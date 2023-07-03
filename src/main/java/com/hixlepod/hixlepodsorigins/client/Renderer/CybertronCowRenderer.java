package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CybertronCowModel;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;

public class CybertronCowRenderer extends MobRenderer<EntityCybertronCow, CybertronCowModel<EntityCybertronCow>> {
    private static final ResourceLocation CYBER_COW_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/cyber_cow.png");

    public CybertronCowRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new CybertronCowModel<>(p_173956_.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    public ResourceLocation getTextureLocation(EntityCybertronCow p_114029_) {
        return CYBER_COW_LOCATION;
    }
}
