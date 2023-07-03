package com.hixlepod.hixlepodsorigins.client.OnCharacters;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.RaccGoatHornsModel;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.TricoElytraModel;
import com.hixlepod.hixlepodsorigins.common.origins.Aniriai;
import com.hixlepod.hixlepodsorigins.common.origins.HixlePod;
import com.hixlepod.hixlepodsorigins.common.origins.TricoFan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;

public class RaccGoatHornsLayer<T extends Player, M extends PlayerModel<T>> extends RenderLayer<T, M> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID, "textures/players/racc_horns.png"), "main");

    private final RaccGoatHornsModel<T> goatModel;

    public RaccGoatHornsLayer(RenderLayerParent<T, M> p_174493_, EntityModelSet p_174494_) {
        super(p_174493_);
        this.goatModel = new RaccGoatHornsModel<>(p_174494_.bakeLayer(LAYER_LOCATION));
    }

    public void render(PoseStack stack, MultiBufferSource p_116952_, int p_116953_, T entity, float p_116955_, float p_116956_, float p_116957_, float p_116958_, float p_116959_, float p_116960_) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (shouldRender(entity)) {
            ResourceLocation resourcelocation = getTexture();

            stack.pushPose();
            //stack.mulPose(Vector3f.XP.rotationDegrees(entity.getXRot()));
            //stack.translate(0.0D, 0.0D, 0.125D);
            //this.getParentModel().head.copyFrom(this.goatModel.getHorns1());

            //ModelPart modelPart = this.getParentModel().head;

            //modelPart.offsetPos(new Vector3f(0.0f, -24.0f, 0.0f));

            this.goatModel.getHorns1().copyFrom(this.getParentModel().head);
            this.goatModel.getHorns2().copyFrom(this.getParentModel().head);


            //this.goatModel.setupAnim(entity, p_116955_, p_116956_, p_116958_, p_116959_, p_116960_);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(p_116952_, RenderType.armorCutoutNoCull(resourcelocation), false, true);
            this.goatModel.renderToBuffer(stack, vertexconsumer, p_116953_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.popPose();


        }
    }

    public boolean shouldRender(T entity) {
        return entity.getName().equals(Component.literal(Aniriai.NAME));
    }

    public ResourceLocation getTexture() {
        return LAYER_LOCATION.getModel();
    }
}
