package com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AllyInvisibilityONS2CPacket {

    public AllyInvisibilityONS2CPacket() {

    }

    public AllyInvisibilityONS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Client
            ServerPlayer player = context.getSender();

            AllyIsAngy.isInvisible = true;


        });
        return true;
    }
}
