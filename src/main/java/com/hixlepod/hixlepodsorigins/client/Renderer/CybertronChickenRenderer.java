package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.CybertronChickenModel;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronChicken;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Chicken;

public class CybertronChickenRenderer extends MobRenderer<EntityCybertronChicken, CybertronChickenModel<EntityCybertronChicken>> {

    private static final ResourceLocation CYBER_CHICKEN_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/cyber_chicken.png");

    public CybertronChickenRenderer(EntityRendererProvider.Context p_173952_) {
        super(p_173952_, new CybertronChickenModel<>(p_173952_.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    public ResourceLocation getTextureLocation(EntityCybertronChicken p_113998_) {
        return CYBER_CHICKEN_LOCATION;
    }

    protected float getBob(Chicken p_114000_, float p_114001_) {
        float f = Mth.lerp(p_114001_, p_114000_.oFlap, p_114000_.flap);
        float f1 = Mth.lerp(p_114001_, p_114000_.oFlapSpeed, p_114000_.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}
