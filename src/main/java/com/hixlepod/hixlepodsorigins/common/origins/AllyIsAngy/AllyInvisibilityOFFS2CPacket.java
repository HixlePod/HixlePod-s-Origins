package com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy;

import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AllyInvisibilityOFFS2CPacket {

    public AllyInvisibilityOFFS2CPacket() {

    }

    public AllyInvisibilityOFFS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Client
            ServerPlayer player = context.getSender();

            AllyIsAngy.isInvisible = false;


        });
        return true;
    }
}
