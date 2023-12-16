package com.hixlepod.hixlepodsorigins.common.NPCs.Dialogues;

import com.hixlepod.hixlepodsorigins.core.utils.OriginsNPCUtils;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

public class NimbusDialogue {

    public static void AnnounceCats(MinecraftServer server) {
        switch (OriginsUtil.randomInt(1, 4)) {
            case 1 ->
                    OriginsNPCUtils.SendMessageToAll(server, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": Hey friends! We've re-opened shop again at spawn, come check it out!"));
            case 2 ->
                    OriginsNPCUtils.SendMessageToAll(server, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": ♪ The paws are back in town! The paws are back in town! ♪ Come check out what we have at spawn!"));
            case 3 ->
                    OriginsNPCUtils.SendMessageToAll(server, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": Me and my pawsome friends are back in the shop, come check it out!"));
            case 4 ->
                    OriginsNPCUtils.SendMessageToAll(server, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": The shop has opened! I have some quests and my friends have items for sale!"));
        }
    }

    public static void HelloThere(Player player) {

        Style componentStyle = Style.EMPTY;
        componentStyle = componentStyle.applyFormat(ChatFormatting.YELLOW);
        componentStyle = componentStyle.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/npc_reply NIMBUS QUESTS"));

        Component component = Component.literal("[ ... ]").withStyle(componentStyle);


        switch (OriginsUtil.randomInt(1, 4)) {
            case 1 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": I have lion blood in my ancestry, that's why I'm fat.").append("\n").append(component));
            case 2 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": I'm being starved everyday, call animal protection services. How am I supposed to eat a half empty bowl?").append("\n").append(component));
            case 3 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": I have about 23 different cat puns in my arsenal and will use exactly 2 of them.").append("\n").append(component));
            case 4 ->
                    OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": I haven't had a bath since 1989.").append("\n").append(component));
        }

    }

    public static void Quests(Player player) {
        Style easyStyle = Style.EMPTY;
        easyStyle = easyStyle.applyFormat(ChatFormatting.YELLOW);
        easyStyle = easyStyle.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/generateticket EASY"));
        easyStyle = easyStyle.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Starts an Easy Quest!")));
        Component easyComponent = Component.literal("[ I want a Easy Quest ]").withStyle(easyStyle);

        Style mediumStyle = Style.EMPTY;
        mediumStyle = mediumStyle.applyFormat(ChatFormatting.YELLOW);
        mediumStyle = mediumStyle.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/generateticket MEDIUM"));
        mediumStyle = mediumStyle.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Starts an Medium Quest!")));
        Component mediumComponent = Component.literal("[ I want a Medium Quest ]").withStyle(mediumStyle);

        Style hardStyle = Style.EMPTY;
        hardStyle = hardStyle.applyFormat(ChatFormatting.YELLOW);
        hardStyle = hardStyle.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/generateticket HARD"));
        hardStyle = hardStyle.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Starts an Hard Quest!")));
        Component hardComponent = Component.literal("[ I want a Hard Quest ]").withStyle(hardStyle);

        Style endgameStyle = Style.EMPTY;
        endgameStyle = endgameStyle.applyFormat(ChatFormatting.YELLOW);
        endgameStyle = endgameStyle.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/generateticket ENDGAME"));
        endgameStyle = endgameStyle.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Starts an EndGame Quest!")));
        Component endgameComponent = Component.literal("[ I want a EndGame Quest ]").withStyle(endgameStyle);

        OriginsNPCUtils.SendMessageToPlayer(player, Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": Anyway... Ya here for some quests right? Which ones today?")
                .append("\n").append(easyComponent)
                .append("\n").append(mediumComponent)
                .append("\n").append(hardComponent)
                .append("\n").append(endgameComponent)
        );



    }

}
