package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import virtuoel.pehkui.api.ScaleTypes;

public class CrispyChordioid {

    public static String NAME = "Crispioid";
    //Crispioid
    public static void setStats(Player player) {

        player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(2.0);
        player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).setBaseValue(0.035);

        ScaleTypes.HEIGHT.getScaleData(player).setScale(0.85f);
        ScaleTypes.WIDTH.getScaleData(player).setScale(0.9f);
    }

    public static void Ability1(ServerPlayer player) {
        player.hurtMarked = true;
        player.setDeltaMovement(player.getDeltaMovement().add(0,0.5 * 5,0));
        player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_CrispyChordioid_Smackdown", true);
    }

    public static void Ability2(ServerPlayer player) {
        if (player.isOnGround() == false) {
            player.hurtMarked = true;
            player.setDeltaMovement(player.getDeltaMovement().add(0, -0.5 * 5, 0));
            player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_CrispyChordioid_Smackdown", true);
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
            for (Entity e : player.getServer().overworld().getAllEntities()) {
                if (e.position().distanceTo(player.position()) < 7) {
                    if (!e.equals(player) && (e instanceof Cat || e instanceof Ocelot)) {
                        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 5 * 20, 1, true, false));
                    }
                }
            }
        }
    }
}
