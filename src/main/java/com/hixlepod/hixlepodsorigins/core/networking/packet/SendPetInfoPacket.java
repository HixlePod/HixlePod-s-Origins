package com.hixlepod.hixlepodsorigins.core.networking.packet;

import com.hixlepod.hixlepodsorigins.common.Entities.Pets.PetsManager;
import com.hixlepod.hixlepodsorigins.common.events.ClientModEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendPetInfoPacket {

    private int pet_options ;

    public SendPetInfoPacket(FriendlyByteBuf buf) {
        this.pet_options = buf.readInt();
    }

    public SendPetInfoPacket(int options) {
        this.pet_options = options;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(pet_options);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Server
            ServerPlayer player = context.getSender();

            //Summon
            if (this.pet_options == 0) {
                PetsManager.attemptSummon(player, PetsManager.returnPlayerPet(player));
            }

            //Unsummon
            if (this.pet_options == 1) {
                PetsManager.attemptUnsummon(player, PetsManager.returnPlayerPet(player));
            }

            //Set Friendly
            if (this.pet_options == 2) {
                PetsManager.SetEntityBehaviour(player, this.pet_options);
            }

            //Set Neutral
            if (this.pet_options == 3) {
                PetsManager.SetEntityBehaviour(player, this.pet_options);
            }

            //Set Aggressive
            if (this.pet_options == 4) {
                PetsManager.SetEntityBehaviour(player, this.pet_options);
            }

        });
        return true;
    }
}
