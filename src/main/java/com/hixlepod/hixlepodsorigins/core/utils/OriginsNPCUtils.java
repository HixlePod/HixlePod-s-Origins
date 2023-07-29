package com.hixlepod.hixlepodsorigins.core.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class OriginsNPCUtils {

    public static void SendMessageToAll(MinecraftServer server, String string) {
        for (Player player : server.getPlayerList().getPlayers()) {
            SendMessageToPlayer(player, string);
        }
    }

    public static void SendMessageToPlayer(Player player, String string) {
        player.sendSystemMessage(Component.literal(string));
    }
}
