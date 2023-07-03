package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.google.common.collect.Maps;
import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CybertronHorseModel;
import com.hixlepod.hixlepodsorigins.common.Entities.CybertronVariants;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronHorse;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityScraplet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.client.renderer.entity.layers.HorseMarkingLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Variant;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public final class CybertronHorseRenderer extends CybertronAbstractHorseRenderer<EntityCybertronHorse, CybertronHorseModel<EntityCybertronHorse>> {

    private static final Map<CybertronVariants, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(CybertronVariants.class), (killmyself) -> {
        killmyself.put(CybertronVariants.AQUAMARINE, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_aquamarine.png"));
        killmyself.put(CybertronVariants.AMETHYST, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_amethyst.png"));
        killmyself.put(CybertronVariants.EMERALD, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_emerald.png"));
        killmyself.put(CybertronVariants.JASPER, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_jasper.png"));
        killmyself.put(CybertronVariants.QUARTZ, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_quartz.png"));
        killmyself.put(CybertronVariants.LAPIS, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_lapis.png"));
        killmyself.put(CybertronVariants.RUBY, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_ruby.png"));
        killmyself.put(CybertronVariants.TOPAZ, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_topaz.png"));
        killmyself.put(CybertronVariants.DIAMOND, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_diamond.png"));


    });

    public CybertronHorseRenderer(EntityRendererProvider.Context p_174167_) {
        super(p_174167_, new CybertronHorseModel<>(p_174167_.bakeLayer(ModelLayers.HORSE)), 1.1F);
        this.addLayer(new CybertronHorseArmourLayer(this, p_174167_.getModelSet()));
    }

    public ResourceLocation getTextureLocation(EntityCybertronHorse p_114872_) {
        return LOCATION_BY_VARIANT.get(p_114872_.getVariant());
    }

    @Override
    protected void scale(EntityCybertronHorse p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(1.5F, 1.5F, 1.5F);
    }
}
