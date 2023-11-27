package com.hixlepod.hixlepodsorigins.core.commands;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class GenerateQuestTicket {

    public static int GenerateTicket(CommandSourceStack source, String ticket_difficulty) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            switch (ticket_difficulty) {
                case "EASY":
                    player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Ticket generated! EASY"));
                    break;

                case "MEDIUM":
                    player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Ticket generated! MEDIUM"));
                    break;

                case "HARD":
                    player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Ticket generated! HARD"));
                    break;

                case "ENDGAME":
                    player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Ticket generated! ENDGAME"));
                    break;

            }
        }
        return 1;
    }
}