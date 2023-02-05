package com.hixlepod.hixlepodsorigins.common.origins;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;

public class Aniriai {

    public static String NAME = "Aniriai";

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(17.0);
        player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(3.0);
    }

    public static void Ability1(ServerPlayer player) {
        for (Entity entity : player.getLevel().getAllEntities()) {
            if (entity instanceof LivingEntity) {

                if (entity.position().distanceTo(player.position()) < 8) {
                    if (!entity.equals(player)) {
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 15 * 20, 6, true, false));

                        player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.GOAT_SCREAMING_AMBIENT, SoundSource.PLAYERS, 1, 1);
                    }
                }
            }
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Aniriai.NAME))) {
            if (player.isSprinting()) {
                player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(1.0);
            } else {
                player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0.5);
            }
        }
    }
}
