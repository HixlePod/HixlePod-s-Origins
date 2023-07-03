package com.hixlepod.hixlepodsorigins.core.commands;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SetCustomNameCommand {

    public static int SET_CUSTOM_NAME(CommandSourceStack source, String name) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            player.getPersistentData().putString(HixlePodsOrigins.MODID + "_CustomDisplayName", name);
            player.refreshDisplayName();

            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Set your custom name to " + ChatFormatting.GOLD + name + ChatFormatting.GREEN + "!"));
        }
        return 1;
    }
}
