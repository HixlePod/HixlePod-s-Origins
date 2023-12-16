package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import virtuoel.pehkui.api.ScaleTypes;

public class lordcrime extends Origin {

    public static String NAME = "lordcrime";

    public static void SetEnergon(Player player) {
        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);
    }

    public static void setStats(Player player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0);
        player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(1.0);

        player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(1.0);

        ScaleTypes.HEIGHT.getScaleData(player).setScale(0.4f);
        ScaleTypes.WIDTH.getScaleData(player).setScale(0.44f);
        ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.5f);

        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.155);
        player.getAttribute(ForgeMod.ENTITY_REACH.get()).setBaseValue(5.5);
        player.getAttribute(ForgeMod.BLOCK_REACH.get()).setBaseValue(5.5);
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(lordcrime.NAME))) {

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") >= 0) {
                int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 1);
            }

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") <= 1) {
                //player.hurt(DamageSource.STARVE, 2.5f);
                player.hurt(player.damageSources().starve(), 2.5f);
            }
            player.getFoodData().setFoodLevel(20);
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10 * 20, 1, true, false, false));

        }
    }

}
