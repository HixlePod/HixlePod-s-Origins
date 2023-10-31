package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.google.common.collect.Maps;
import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.CybertronHorseModel;
import com.hixlepod.hixlepodsorigins.common.Entities.CybertronVariants;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronHorse;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public final class CybertronHorseRenderer extends CybertronAbstractHorseRenderer<EntityCybertronHorse, CybertronHorseModel<EntityCybertronHorse>> {

    private static final Map<CybertronVariants, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(CybertronVariants.class), (horse_types) -> {
        horse_types.put(CybertronVariants.AQUAMARINE, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_aquamarine.png"));
        horse_types.put(CybertronVariants.AMETHYST, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_amethyst.png"));
        horse_types.put(CybertronVariants.EMERALD, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_emerald.png"));
        horse_types.put(CybertronVariants.JASPER, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_jasper.png"));
        horse_types.put(CybertronVariants.QUARTZ, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_quartz.png"));
        horse_types.put(CybertronVariants.LAPIS, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_lapis.png"));
        horse_types.put(CybertronVariants.RUBY, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_ruby.png"));
        horse_types.put(CybertronVariants.TOPAZ, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_topaz.png"));
        horse_types.put(CybertronVariants.DIAMOND, new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/animal/horses/cybertron_horse_diamond.png"));


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
