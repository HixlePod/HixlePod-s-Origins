package com.hixlepod.hixlepodsorigins.common.NPCs.Dialogues;

import com.hixlepod.hixlepodsorigins.core.utils.OriginsNPCUtils;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SmudgeDialogue {

    public static void HurtCat(Player player) {
        switch (OriginsUtil.randomInt(1, 2)) {
            case 1 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Smudge" + ChatFormatting.WHITE + ": Death"));
        }
    }

    public static void PlayerDidntBuyAnything(Player player) {
        switch (OriginsUtil.randomInt(1, 3)) {
            case 1 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Smudge" + ChatFormatting.WHITE + ": , Alright fucktard, thanks for wasting my time."));
            case 2 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Smudge" + ChatFormatting.WHITE + ": ??? Why come here if you're just gonna waste my time, idiot."));
            case 3 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Smudge" + ChatFormatting.WHITE + ": Wasting my time when I have other customers. Twat."));
        }
    }
}
