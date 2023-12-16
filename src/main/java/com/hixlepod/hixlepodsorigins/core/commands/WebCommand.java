package com.hixlepod.hixlepodsorigins.core.commands;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;

public class WebCommand {

    public static int WEBCOMMAND(CommandSourceStack source) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            Style componentStyle = Style.EMPTY;
            componentStyle = componentStyle.applyFormat(ChatFormatting.YELLOW);
            componentStyle = componentStyle.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://origins.boomball.org/"));
            componentStyle = componentStyle.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.literal("Opens 'http://origins.boomball.org/' on your browser!")));

            Component component = Component.literal("[Click to open]").withStyle(componentStyle);

            player.sendSystemMessage(component);

        }
        return 1;
    }

}
