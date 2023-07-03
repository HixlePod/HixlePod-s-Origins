package com.hixlepod.hixlepodsorigins.core.networking.packet;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class Ability2C2SPacket {

    public Ability2C2SPacket() {

    }

    public Ability2C2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Server
            ServerPlayer player = context.getSender();

            if (OriginSettings.TRIGGER_ABILITIES_ENABLED) {
                if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2") == 0) {

                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown2", OriginsManager.returnAbilityMaxCooldown2(player));

                    playerAbilities2(player);

                } else {
                }
            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Origin trigger abilities have been disabled by the server administrator."));
            }
        });
        return true;
    }

    public static void playerAbilities2(ServerPlayer player) {
        if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
            CrispyChordioid.Ability2(player);

        } else if (player.getName().equals(Component.literal(Fudge105.NAME))) {
            Fudge105.Ability2(player);

        } else if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
            GodOfFurrys.Ability2(player);

        } else if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
            Blakpaw2244.Ability2(player);

        } else if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
            Flo_Plays_.Ability2(player);

        } else if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
            gh0stlure.Ability2(player);

        } else if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            CatGirlSeeka.Ability2(player);

        } else if (player.getName().equals(Component.literal(Stamce.NAME))) {
            Stamce.Ability2(player);

        }
    }
}
