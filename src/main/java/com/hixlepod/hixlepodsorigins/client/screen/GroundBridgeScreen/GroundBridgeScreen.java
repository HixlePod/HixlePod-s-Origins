package com.hixlepod.hixlepodsorigins.client.screen.GroundBridgeScreen;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.event.ContainerScreenEvent;

public class GroundBridgeScreen extends AbstractContainerScreen<GroundBridgeMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(HixlePodsOrigins.MODID, "textures/gui/ground_bridge_gui.png");

    public GroundBridgeScreen(GroundBridgeMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int MouseX, int MouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
        renderFuelAmount(poseStack, x, y);
    }

    private void renderFuelAmount(PoseStack poseStack, int x, int y) {
        if (menu.data.get(0) > 1) {
            blit(poseStack, x + 54, y + 14, 176, 0,8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(PoseStack poseStack, int MouseX, int MouseY, float Delta) {
        renderBackground(poseStack);
        super.render(poseStack, MouseX, MouseY, Delta);
        renderTooltip(poseStack, MouseX, MouseY);
    }
}
