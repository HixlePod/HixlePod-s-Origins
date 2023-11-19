package com.hixlepod.hixlepodsorigins.core.utils;

import com.hixlepod.hixlepodsorigins.common.origins.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class OriginPlayerUtils {

    public static boolean isRobotPlayer(Player target) {

        if (target.getName().equals(Component.literal(HixlePod.NAME))
                || target.getName().equals(Component.literal(AmbrosiaElf.NAME))
                || target.getName().equals(Component.literal(Blakpaw2244.NAME))
                || target.getName().equals(Component.literal(Folf_Gaming.NAME))
                || target.getName().equals(Component.literal(Kira_uwu69.NAME))) {
            return true;
        }

        return false;
    }
}
