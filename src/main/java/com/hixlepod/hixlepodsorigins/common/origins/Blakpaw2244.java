package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import virtuoel.pehkui.api.ScaleTypes;

public class Blakpaw2244 extends Origin {

    //Blakpaw
    public static String NAME = "Blakpaw";

    public static double ENERGON_AMOUNT = 6000.0;

    public static void SetEnergon(Player player) {
        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);
    }

    public static void setAbilityData(Player player) {

        player.getAbilities().setFlyingSpeed(0.025F);
        player.getAbilities().mayfly = true;
        player.getAbilities().flying = true;
        player.getServer().getPlayerList().getPlayerByName(Blakpaw2244.NAME).onUpdateAbilities();

    }

    public static void setStats(Player player) {

        SetOrigin(player);

    }

    public static void Ability1(ServerPlayer player) {
        player.level().explode(player, player.position().x(), player.position().y(), player.position().z(), 6.0F, false, Level.ExplosionInteraction.NONE);
    }

    public static void Ability2(ServerPlayer player) {
        ToggleOrigin(player);
        SetOrigin(player);
    }

    public static void ToggleOrigin(Player player) {

        if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_BlackBigBoi") == true) {
            player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_BlackBigBoi", false);


        } else if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_BlackBigBoi") == false) {
            player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_BlackBigBoi", true);
        }
    }

    public static void SetOrigin(Player player) {

        if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_BlackBigBoi") == true) {

            /*
            player.getAbilities().setFlyingSpeed(0.025F);
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(Blakpaw2244.NAME).onUpdateAbilities();


             */
            ScaleTypes.HEIGHT.getScaleData(player).setScale(0.9f);
            ScaleTypes.WIDTH.getScaleData(player).setScale(0.9f);
            ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.0f);

            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
            player.getAttribute(ForgeMod.ENTITY_REACH.get()).setBaseValue(3.0);
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).setBaseValue(3.0);
            player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(1.0);

        } else if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_BlackBigBoi") == false) {

            /*
            player.getAbilities().setFlyingSpeed(0.025F);
            player.getAbilities().mayfly = true;
            player.getAbilities().flying = true;
            ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(Blakpaw2244.NAME).onUpdateAbilities();
             */

            ScaleTypes.HEIGHT.getScaleData(player).setScale(2f);
            ScaleTypes.WIDTH.getScaleData(player).setScale(2f);
            ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.4f);

            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.09);
            player.getAttribute(ForgeMod.ENTITY_REACH.get()).setBaseValue(7.0);
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).setBaseValue(7.0);
            player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(3.0);
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") >= 0) {
                int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - (player.getAbilities().flying ? 2 : 1));
            }

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") <= 1) {
                player.hurt(player.damageSources().starve(), 2.5f);
            }
            player.getFoodData().setFoodLevel(20);
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10 * 20, 1, true, false, false));

        }
    }

}
