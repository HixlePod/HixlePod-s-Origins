package com.hixlepod.hixlepodsorigins.core.commands.NPC;

import com.hixlepod.hixlepodsorigins.common.NPCs.Dialogues.NimbusDialogue;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class NimbusReplyCommand {

    public static int GiveQuests(CommandSourceStack source, String REPLY_ID) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            switch (REPLY_ID) {
                case "QUESTS":
                    NimbusDialogue.Quests(player);
                    break;

                default:
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Do not use these commands."));
                    break;

            }
        }
        return 1;
    }
}
