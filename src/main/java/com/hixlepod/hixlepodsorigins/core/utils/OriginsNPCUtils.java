package com.hixlepod.hixlepodsorigins.core.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class OriginsNPCUtils {

    public static void SendMessageToAll(MinecraftServer server, Component component) {
        for (Player player : server.getPlayerList().getPlayers()) {
            SendMessageToPlayer(player, component);
        }
    }

    public static void SendMessageToPlayer(Player player, Component component) {
        player.sendSystemMessage(component);
    }
}
