package com.hixlepod.hixlepodsorigins.client.NPC;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.NPC.Model.NPCModel;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityBooNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityNimbusNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntitySmudgeNPC;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class SmudgeNPCRenderer extends AbstractNPCRenderer<EntitySmudgeNPC, NPCModel<EntitySmudgeNPC>> {
    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/npc/smudge.png");

    public SmudgeNPCRenderer(EntityRendererProvider.Context p_174456_) {
        this(p_174456_, ModelLayers.PLAYER, ModelLayers.PLAYER_INNER_ARMOR, ModelLayers.PLAYER_OUTER_ARMOR);
    }

    public SmudgeNPCRenderer(EntityRendererProvider.Context p_174458_, ModelLayerLocation p_174459_, ModelLayerLocation p_174460_, ModelLayerLocation p_174461_) {
        super(p_174458_, new NPCModel<>(p_174458_.bakeLayer(p_174459_)), new NPCModel<>(p_174458_.bakeLayer(p_174460_)), new NPCModel<>(p_174458_.bakeLayer(p_174461_)));
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySmudgeNPC entity) {
        return RESOURCE_LOCATION;
    }

    @Override
    protected void scale(EntitySmudgeNPC p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(0.85F, 0.85F, 0.85F);
    }
}
