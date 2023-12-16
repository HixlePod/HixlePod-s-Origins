package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;

public class Toupster02 extends Origin {

    public static String NAME = "Toupster02";

    public static void setStats(Player player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(OriginsUtil.randomDouble(0.5, 3.0));
        player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(OriginsUtil.randomDouble(0.0, 1.0));
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(OriginsUtil.randomDouble(0.075, 0.15));
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(OriginsUtil.randomDouble(10.0, 40.0));
        player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(OriginsUtil.randomDouble(3.0, 7.0));
        
        player.getAttribute(ForgeMod.ENTITY_REACH.get()).setBaseValue(OriginsUtil.randomDouble(4.0, 5.5));
        player.getAttribute(ForgeMod.BLOCK_REACH.get()).setBaseValue(OriginsUtil.randomDouble(4.0, 5.5));
    }
}
