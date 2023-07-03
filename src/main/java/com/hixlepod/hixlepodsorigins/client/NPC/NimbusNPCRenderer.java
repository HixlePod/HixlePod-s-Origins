package com.hixlepod.hixlepodsorigins.client.NPC;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.NPC.Model.NPCModel;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityNimbusNPC;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NimbusNPCRenderer<Type extends EntityNimbusNPC> extends MobRenderer<Type, NPCModel<Type>> {
    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/npc/nimbus.png");

    public NimbusNPCRenderer(EntityRendererProvider.Context context) {
        super(context, new NPCModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return RESOURCE_LOCATION;
    }

    @Override
    protected void scale(Type p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(0.85F, 0.85F, 0.85F);
    }
}
