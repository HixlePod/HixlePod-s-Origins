package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss.EntityBetaScraplet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BetaScrapletRenderer<T extends EntityBetaScraplet> extends MobRenderer<T, SpiderModel<T>> {

    private static final ResourceLocation SCRAPLET_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/hostiles/scraplet.png");
    private static final float SCALE = 0.7F;

    public BetaScrapletRenderer(EntityRendererProvider.Context context) {
        super(context, new SpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 0.8F);
    }

    @Override
    protected void scale(EntityBetaScraplet p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(0.25F, 0.25F, 0.25F);
    }

    public ResourceLocation getTextureLocation(EntityBetaScraplet p_113972_) {
        return SCRAPLET_LOCATION;
    }
}
