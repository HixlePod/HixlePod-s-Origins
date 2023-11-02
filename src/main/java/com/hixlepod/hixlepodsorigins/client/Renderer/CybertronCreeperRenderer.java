package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.CybertronCreeperModel;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityCybertronCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CybertronCreeperRenderer extends MobRenderer<EntityCybertronCreeper, CybertronCreeperModel<EntityCybertronCreeper>> {

    private static final ResourceLocation CYBER_CREEPER_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/hostiles/cyber_creeper.png");


    public CybertronCreeperRenderer(EntityRendererProvider.Context p_173958_) {
        super(p_173958_, new CybertronCreeperModel<>(p_173958_.bakeLayer(ModelLayers.CREEPER)), 0.5F);
        //this.addLayer(new CreeperPowerLayer(this, p_173958_.getModelSet()));
    }

    protected void scale(EntityCybertronCreeper p_114046_, PoseStack p_114047_, float p_114048_) {
        float f = p_114046_.getSwelling(p_114048_);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        p_114047_.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(EntityCybertronCreeper p_114043_, float p_114044_) {
        float f = p_114043_.getSwelling(p_114044_);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

    public ResourceLocation getTextureLocation(EntityCybertronCreeper p_114041_) {
        return CYBER_CREEPER_LOCATION;
    }
}
