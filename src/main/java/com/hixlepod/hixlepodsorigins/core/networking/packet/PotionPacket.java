package com.hixlepod.hixlepodsorigins.core.networking.packet;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.BlockStore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PotionPacket {
    private String potionName;

    public PotionPacket(String potion) {
        this.potionName = potion;
    }

    public static void encode(PotionPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.potionName);

    }

    public static PotionPacket decode(FriendlyByteBuf buf) {
        return new PotionPacket(buf.readUtf());
    }

    public static class Handler {
        public static void handle(final PotionPacket message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                String removed_potion = message.potionName;
                toggle(removed_potion);

            });
            ctx.get().setPacketHandled(true);
        }

        @OnlyIn(Dist.CLIENT)
        private static void toggle(String potion) {
            BlockStore store = HixlePodsOrigins.blockStore;
            store.getStoreByReference(potion).getBlockData().setDrawing(false);
        }
    }
}
