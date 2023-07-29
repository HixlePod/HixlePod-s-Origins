package com.hixlepod.hixlepodsorigins.core.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;
import net.minecraftforge.common.ForgeMod;

@Deprecated
public class OriginsTeamUtils {

    private static Scoreboard scoreboard = new Scoreboard();
    private static PlayerTeam Origins = new PlayerTeam(scoreboard, "origins");

    public static PlayerTeam getOriginsTeam() {
        if (Origins == null) { CreateOriginsTeam(); }
        return Origins;
    }

    private static void CreateOriginsTeam() {
        Origins.setColor(ChatFormatting.YELLOW);
        Origins.setPlayerPrefix(Component.literal("[Origins] "));
    }
}
