package com.hixlepod.hixlepodsorigins.core.utils;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityEcho;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class OriginsUtil {

    public static int randomInt(int minimum, int maximum) {
        return ((int) (Math.random() * ((maximum + 1) - minimum))) + minimum;
    }

    public static double randomDouble(double minimum, double maximum) {
        return ((double) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static float randomFloat(float minimum, float maximum) {
        return ((float) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static double DamageScale(double BaseDamage, Player player) {
        double ExperienceLevel = player.experienceLevel;
        double DayCount = player.getServer().overworld().getDayTime();

        //BaseDamage added twice to calculation so it has a bigger effect on the final damage.
        double TotalDamage = BaseDamage + ((ExperienceLevel / 10) + (DayCount / 300));
        double FinalDamage = BaseDamage + (Math.log(TotalDamage) / Math.log(1.2));
        return FinalDamage;
    }

    /**
     * @DEPRECATED: Old origins damage scale has been replaced, please use the new system.
     * @Method: DamageScale(BaseDamage, Player);
     */
    @Deprecated(since = "0.9.0")
    public static float damageScale(float baseDamage, Player player) {
        return (float) DamageScale(baseDamage, player);
    }

    public static boolean didChance(int Chance) {
        int Roll = randomInt(1, 100);

        if (Roll <= Chance) {
            return true;
        }

        return false;
    }

    public static void sendParticle(ServerLevel level, ParticleOptions particleType, Vec3 position, Vec3 Delta, double Speed, int Count) {
        level.sendParticles(particleType, position.x(), position.y(), position.z(), Count, Delta.x(), Delta.y(), Delta.z(), Speed);
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
                    if (petEntity instanceof EntityEcho) {
                        EntityEcho echo = (EntityEcho) petEntity;
                        pet_status = ChatFormatting.RED + " Echo: " + String.format("%.2f", echo.getHealth() / echo.getMaxHealth()) + "%";
                    }
                }
            }
        }

        if (OriginSettings.TRIGGER_ABILITIES_ENABLED == false) {
            ability1 = ChatFormatting.RED + "Abilities disabled.";
            ability2 = "";
        }

        String energon_level = "";

        if (player.getName().equals(Component.literal(HixlePod.NAME)) || player.getName().equals(Component.literal(AmbrosiaElf.NAME)) || player.getName().equals(Component.literal(Blakpaw2244.NAME))
        || player.getName().equals(Component.literal(Folf_Gaming.NAME)) || player.getName().equals(Component.literal(Kira_uwu69.NAME))) {

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
