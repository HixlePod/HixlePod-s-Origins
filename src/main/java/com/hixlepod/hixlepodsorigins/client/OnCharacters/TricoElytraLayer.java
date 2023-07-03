package com.hixlepod.hixlepodsorigins.client.OnCharacters;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.GhostElytraModel;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.TricoElytraModel;
import com.hixlepod.hixlepodsorigins.common.origins.TricoFan;
import com.hixlepod.hixlepodsorigins.common.origins.gh0stlure;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;

public class TricoElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    public static final ModelLayerLocation WINGS_LOCATION = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID,"textures/wings/aleato_wings.png"), "main");
    private final TricoElytraModel<T> elytraModel;

    public TricoElytraLayer(RenderLayerParent<T, M> p_174493_, EntityModelSet p_174494_) {
        super(p_174493_);
        this.elytraModel = new TricoElytraModel<>(p_174494_.bakeLayer(ModelLayers.ELYTRA));
    }

    public void render(PoseStack p_116951_, MultiBufferSource p_116952_, int p_116953_, T entity, float p_116955_, float p_116956_, float p_116957_, float p_116958_, float p_116959_, float p_116960_) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (shouldRender(entity)) {
            ResourceLocation resourcelocation;
            if (entity instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer) entity;
                if (abstractclientplayer.isElytraLoaded() && abstractclientplayer.getElytraTextureLocation() != null) {
                    resourcelocation = abstractclientplayer.getElytraTextureLocation();
                } else if (abstractclientplayer.isCapeLoaded() && abstractclientplayer.getCloakTextureLocation() != null && abstractclientplayer.isModelPartShown(PlayerModelPart.CAPE)) {
                    resourcelocation = abstractclientplayer.getCloakTextureLocation();
                } else {
                    resourcelocation = getElytraTexture(itemstack, entity);
                }
            } else {
                resourcelocation = getElytraTexture(itemstack, entity);
            }

            p_116951_.pushPose();
            p_116951_.translate(0.0D, 0.0D, 0.125D);
            this.getParentModel().copyPropertiesTo(this.elytraModel);
            this.elytraModel.setupAnim(entity, p_116955_, p_116956_, p_116958_, p_116959_, p_116960_);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_116952_, RenderType.armorCutoutNoCull(resourcelocation), false, false);
            this.elytraModel.renderToBuffer(p_116951_, vertexconsumer, p_116953_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            p_116951_.popPose();
        }
    }

    public boolean shouldRender(T entity) {
        return entity.getName().equals(Component.literal(TricoFan.NAME));
    }

    public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
        return WINGS_LOCATION.getModel();
    }
}
