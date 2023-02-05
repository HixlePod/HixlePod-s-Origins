package com.hixlepod.hixlepodsorigins.core.networking.packet;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid.CrispyChordioid;
import com.hixlepod.hixlepodsorigins.common.origins.Electrum_Star.Electrum_Star;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys.GodOfFurrys;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class Ability1C2SPacket {

    public Ability1C2SPacket() {

    }

    public Ability1C2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Server
            ServerPlayer player = context.getSender();

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1") == 0) {

                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown1", OriginsManager.returnAbilityMaxCooldown1(player));

                playerAbilities1(player);

            } else {
            }
        });
        return true;
    }

    public static void playerAbilities1(ServerPlayer player) {
        if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
            AmbrosiaElf.Ability1(player);
            AmbrosiaElf.Ability1(player);

        } else if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
            CrispyChordioid.Ability1(player);

        } else if (player.getName().equals(Component.literal(Fudge105.NAME))) {
            Fudge105.Ability1(player);

        } else if (player.getName().equals(Component.literal(AllyIsAngy.NAME))) {
            AllyIsAngy.Ability1(player);

        } else if (player.getName().equals(Component.literal(undramaticc.NAME))) {
            undramaticc.Ability1(player);

        } else if (player.getName().equals(Component.literal(J_Curve.NAME))) {
            J_Curve.Ability1(player);

        } else if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
            GodOfFurrys.Ability1(player);

        } else if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
            Blakpaw2244.Ability1(player);

        } else if (player.getName().equals(Component.literal(HixlePod.NAME))) {
            HixlePod.Ability1(player);

        } else if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
            Electrum_Star.Ability1(player);

        } else if (player.getName().equals(Component.literal(Maxwell.NAME))) {
            Maxwell.Ability1(player);

        } else if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
            Flo_Plays_.Ability1(player);

        } else if (player.getName().equals(Component.literal(Aniriai.NAME))) {
            Aniriai.Ability1(player);

        } else if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
            gh0stlure.Ability1(player);

        } else if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            CatGirlSeeka.Ability1(player);

        } else if (player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
            ArtificalMemes.Ability1(player);

        }
    }
}
