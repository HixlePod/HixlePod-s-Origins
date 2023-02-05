package com.hixlepod.hixlepodsorigins.client.screen;

import com.hixlepod.hixlepodsorigins.common.Pets.PetsManager;
import com.hixlepod.hixlepodsorigins.common.Pets.Utils.SendPetInfoPacket;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability1C2SPacket;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;

public class PetMenuScreen extends Screen {

    public PetMenuScreen(String title) {
        super(Component.literal(title).withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected void init() {
        super.init();

        //Pet summoning
        addRenderableWidget(new Button(this.width / 2 - 110, this.height / 2 - 80, 100, 20, Component.literal("Summon Pet"),
                PetMenuScreen::SummonPet));

        addRenderableWidget(new Button(this.width / 2 + 10, this.height / 2 - 80, 100, 20, Component.literal("Unsummon pet"),
                PetMenuScreen::UnsummonPet));

        //Pet behaviour
        addRenderableWidget(new Button(this.width / 2 - 165, this.height / 2 - 50, 100, 20, Component.literal("Set to Friendly"),
                PetMenuScreen::SetFriendly));

        addRenderableWidget(new Button(this.width / 2 - 55, this.height / 2 - 50, 100, 20, Component.literal("Set to Neutral"),
                PetMenuScreen::SetNeutral));

        addRenderableWidget(new Button(this.width / 2 + 55, this.height / 2 - 50, 100, 20, Component.literal("Set to Aggressive"),
                PetMenuScreen::SetAggressive));
    }

    private static void SummonPet(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(0));
    }

    private static void UnsummonPet(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(1));
    }

    private static void SetFriendly(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(2));
    }

    private static void SetNeutral(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(3));
    }

    private static void SetAggressive(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(4));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
