package com.hixlepod.hixlepodsorigins.core.utils;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.AmbrosiaElf;
import com.hixlepod.hixlepodsorigins.common.origins.Blakpaw2244;
import com.hixlepod.hixlepodsorigins.common.origins.HixlePod;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class OriginsUtil {

    public static int randomInt(int minimum, int maximum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static double randomDouble(double minimum, double maximum) {
        return ((double) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static float damageScale(float baseDamage, Player player) {
        float experience = (float) player.experienceLevel;
        float day = (float) player.getServer().overworld().getDayTime();

        float total = baseDamage + ((experience / 10) + (day / 60));

        if (total >= 17) {
            return 17;
        } else {
            return total;
        }
    }

    public static boolean didChance(int Chance) {
        int Roll = randomInt(1, 100);

        if (Roll <= Chance) {
            return true;
        }

        return false;
    }

    public static void returnAbilityMessage(Player player) {
        int ability1_left = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1");
        int ability2_left = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2");

        String ability1 = ChatFormatting.YELLOW + "Ability 1: " + ability1_left / OriginsManager.ticks;
        String ability2 = ChatFormatting.YELLOW + " Ability 2: " + ability2_left / OriginsManager.ticks;
        String pet_status = "";

        if (ability1_left == 0) {ability1 = ChatFormatting.GREEN + "Ability 1: Ready.";}
        if (ability2_left == 0) {ability2 = ChatFormatting.GREEN + " Ability 2: Ready.";}

        if (OriginsManager.returnAbilityMaxCooldown1(player) == -1) {
            ability1 = "";
        }

        if (OriginsManager.returnAbilityMaxCooldown2(player) == -1) {
            ability2 = "";

            if (player.getName().equals(Component.literal(HixlePod.NAME))) {
                Entity petEntity = returnPlayerPet(player, EntityInit.ECHO.get());

                if (petEntity != null) {
                    pet_status = ChatFormatting.RED + " Echo: " ;
                }
            }
        }

        String energon_level = "";

        if (player.getName().equals(Component.literal(HixlePod.NAME)) || player.getName().equals(Component.literal(AmbrosiaElf.NAME)) || player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
            int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
            double total = ((double) energon / (double) OriginsManager.ticks) / HixlePod.ENERGON_AMOUNT * 100;

            energon_level = ChatFormatting.DARK_GREEN + " Energon: " + String.format("%.2f", total) + "%";
        }

        player.displayClientMessage(Component.nullToEmpty(ability1 + pet_status + ability2 + energon_level), true);
    }

    public static Entity returnPlayerPet(Player player, EntityType livingEntity) {
        for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
            if (entity instanceof LivingEntity) {

                if (entity.getType() == livingEntity) {
                    return entity;
                } else {
                    return null;
                }
            }
        }

        return null;
    }
}
