package com.hixlepod.hixlepodsorigins.client.screen;

import com.hixlepod.hixlepodsorigins.common.events.ClientModEvents;
import com.hixlepod.hixlepodsorigins.core.networking.packet.SendPetInfoPacket;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class PetMenuScreen extends Screen {

    public PetMenuScreen(String title) {
        super(Component.literal(title).withStyle(ChatFormatting.GOLD));
    }

    @Override
    protected void init() {
        super.init();

        //Pet summoning
        addRenderableWidget(Button.builder(Component.literal("Summon Pet"), PetMenuScreen::SummonPet)
                //x, y, width, height
                .bounds(this.width / 2 - 110, this.height / 2 - 80, 100, 20)
                .build());

        //addRenderableWidget(new Button(this.width / 2 - 110, this.height / 2 - 80, 100, 20, Component.literal("Summon Pet"), PetMenuScreen::SummonPet));

        addRenderableWidget(Button.builder(Component.literal("Unsummon pet"), PetMenuScreen::UnsummonPet)
                //x, y, width, height
                .bounds(this.width / 2 + 10, this.height / 2 - 80, 100, 20)
                .build());

        //addRenderableWidget(new Button(this.width / 2 + 10, this.height / 2 - 80, 100, 20, Component.literal("Unsummon pet"), PetMenuScreen::UnsummonPet));

        //Pet behaviour
        addRenderableWidget(Button.builder(Component.literal("Set to Friendly"), PetMenuScreen::SetFriendly)
                //x, y, width, height
                .bounds(this.width / 2 - 165, this.height / 2 - 50, 100, 20)
                .build());

        //addRenderableWidget(new Button(this.width / 2 - 165, this.height / 2 - 50, 100, 20, Component.literal("Set to Friendly"), PetMenuScreen::SetFriendly));

        addRenderableWidget(Button.builder(Component.literal("Set to Neutral"), PetMenuScreen::SetNeutral)
                //x, y, width, height
                .bounds(this.width / 2 - 55, this.height / 2 - 50, 100, 20)
                .build());

        //addRenderableWidget(new Button(this.width / 2 - 55, this.height / 2 - 50, 100, 20, Component.literal("Set to Neutral"), PetMenuScreen::SetNeutral));

        addRenderableWidget(Button.builder(Component.literal("Set to Aggressive"), PetMenuScreen::SetAggressive)
                //x, y, width, height
                .bounds(this.width / 2 + 55, this.height / 2 - 50, 100, 20)
                .build());

        //addRenderableWidget(new Button(this.width / 2 + 55, this.height / 2 - 50, 100, 20, Component.literal("Set to Aggressive"), PetMenuScreen::SetAggressive));
    }

    private static void SummonPet(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(0));
        ClientModEvents.ClientForgeEvents.isPetOut = true;
    }

    private static void UnsummonPet(Button button) {
        NetworkManager.sendToServer(new SendPetInfoPacket(1));
        ClientModEvents.ClientForgeEvents.isPetOut = false;
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
