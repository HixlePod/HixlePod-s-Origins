package com.hixlepod.hixlepodsorigins.core.networking.packet;

import com.hixlepod.hixlepodsorigins.common.origins.Flo_Plays_;
import com.hixlepod.hixlepodsorigins.common.origins.gh0stlure;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.caelus.api.CaelusApi;

import java.util.function.Supplier;

public class ElytraAttemptFlyPacket {

    public ElytraAttemptFlyPacket(FriendlyByteBuf buf) {
    }

    public ElytraAttemptFlyPacket() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Server
            ServerPlayer player = context.getSender();
            if (player.getName().equals(Component.literal(gh0stlure.NAME)) || player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
                if (!player.isOnGround() && !player.isFallFlying() && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION)) {
                    player.getAttribute(CaelusApi.getInstance().getFlightAttribute()).setBaseValue(1);
                }
           }
        });
        return true;
    }
}
