package com.hixlepod.hixlepodsorigins.common.origins.Fudge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FudgeInvisibilityOFFS2CPacket {

    public FudgeInvisibilityOFFS2CPacket() {

    }

    public FudgeInvisibilityOFFS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Client
            ServerPlayer player = context.getSender();

            Fudge105.isInvisible = false;


        });
        return true;
    }
}
