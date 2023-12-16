package com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.Origin;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class AllyIsAngy extends Origin {

    public static String NAME = "AllyIsAngy";

    public static boolean isInvisible = false;

    public static void Ability1(ServerPlayer player) {

        for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
            NetworkManager.sendToPlayer(new AllyInvisibilityONS2CPacket(), serverPlayer);
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(AllyIsAngy.NAME))) {

            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1") / OriginsManager.ticks) <= 50) {
                for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
                    NetworkManager.sendToPlayer(new AllyInvisibilityOFFS2CPacket(), serverPlayer);
                }
            }
        }
    }

}
