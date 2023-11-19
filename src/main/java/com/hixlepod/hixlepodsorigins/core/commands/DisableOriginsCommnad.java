package com.hixlepod.hixlepodsorigins.core.commands;

import com.hixlepod.hixlepodsorigins.common.Entities.Pets.PetsManager;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class DisableOriginsCommnad {

    public static int CHANGE_TRIGGER_ABILITIES(CommandSourceStack source, String status) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            if (status.equals("ON")) {
                OriginSettings.TRIGGER_ABILITIES_ENABLED = true;
                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Origin trigger abilities have been enabled."));

            } else if (status.equals("OFF")) {
                OriginSettings.TRIGGER_ABILITIES_ENABLED = false;
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Origin trigger abilities have been disabled."));

            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: Only 2 acceptable values, ENABLE and DISABLE. You typed " + status));
            }
        }
        return 1;
    }

    public static int CHANGE_PETS_ENABLED(CommandSourceStack source, String status) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            if (status.equals("ON")) {
                OriginSettings.PETS_ENABLED = true;
                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Origin pets have been enabled."));

            } else if (status.equals("OFF")) {
                OriginSettings.PETS_ENABLED = false;
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Origin pets have been disabled."));

                for (Player players : player.getServer().getPlayerList().getPlayers()) {
                    PetsManager.attemptUnsummon(players, PetsManager.returnPlayerPet(players));
                }

            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: Only 2 acceptable values, ENABLE and DISABLE. You typed " + status));
            }
        }
        return 1;
    }

    public static int CHANGE_SITTING_ENABLED(CommandSourceStack source, String status) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            if (status.equals("ON")) {
                OriginSettings.SITTING_ENABLED = true;
                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Origin sitting have been enabled."));

            } else if (status.equals("OFF")) {
                OriginSettings.SITTING_ENABLED = false;
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Origin sitting have been disabled."));

            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: Only 2 acceptable values, ENABLE and DISABLE. You typed " + status));
            }
        }
        return 1;
    }

    public static int CHANGE_GROUND_BRIDGE_ENABLED(CommandSourceStack source, String status) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            if (status.equals("ON")) {
                OriginSettings.GROUND_BRIDGE_ENABLED = true;
                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Ground bridges have been enabled."));

            } else if (status.equals("OFF")) {
                OriginSettings.GROUND_BRIDGE_ENABLED = false;
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Ground bridges have been disabled."));

            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: Only 2 acceptable values, ENABLE and DISABLE. You typed " + status));
            }
        }
        return 1;
    }

    public static int CHANGE_ALL(CommandSourceStack source, String status) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            if (status.equals("ON")) {
                OriginSettings.TRIGGER_ABILITIES_ENABLED = true;
                OriginSettings.PETS_ENABLED = true;
                OriginSettings.SITTING_ENABLED = true;
                OriginSettings.GROUND_BRIDGE_ENABLED = true;

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "All categories have been enabled."));

            } else if (status.equals("OFF")) {
                OriginSettings.TRIGGER_ABILITIES_ENABLED = false;
                OriginSettings.PETS_ENABLED = false;
                OriginSettings.SITTING_ENABLED = false;
                OriginSettings.GROUND_BRIDGE_ENABLED = false;

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "All categories have been disabled."));

            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: Only 2 acceptable values, ENABLE and DISABLE. You typed " + status));
            }
        }
        return 1;
    }

}
