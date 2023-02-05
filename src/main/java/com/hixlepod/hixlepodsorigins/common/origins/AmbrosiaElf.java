package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class AmbrosiaElf {

    //xAnxietyElfx
    public static String NAME = "xAnxietyElfx";

    public static double ENERGON_AMOUNT = 6000.0;

    public static void setStats(Player player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0);
        player.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(1.0);

        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);
    }

    public static void Ability1(ServerPlayer player) {

        player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.PORTAL_TRIGGER, SoundSource.PLAYERS, 1, 2);

        for (Entity entity : player.getLevel().getAllEntities()) {
            if (entity instanceof LivingEntity) {

                if (entity.position().distanceTo(player.position()) < 8) {
                    if (!entity.equals(player) && !(entity.getTeam() == player.getTeam())) {

                        switch (OriginsUtil.randomInt(1, 7)) {
                            case 1:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON, 15 * 20, 6, true, false));
                                break;

                            case 2:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10 * 20, 6, true, false));
                                break;

                            case 3:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15 * 20, 6, true, false));
                                break;

                            case 4:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 15 * 20, 6, true, false));
                                break;

                            case 5:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * 20, 6, true, false));
                                break;

                            case 6:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 25 * 20, 6, true, false));
                                break;

                            case 7:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 25 * 20, 6, true, false));
                                break;
                        }
                    } else if (!entity.equals(player) && (entity.getTeam() == player.getTeam())) {
                        switch (OriginsUtil.randomInt(1, 6)) {
                            case 1:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 3, true, false));
                                break;

                            case 2:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 15 * 20, 1, true, false));
                                break;

                            case 3:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 15 * 20, 1, true, false));
                                break;

                            case 4:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60 * 20, 1, true, false));
                                break;

                            case 5:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 1, true, false));
                                break;

                            case 6:
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 15 * 20, 1, true, false));
                                break;
                        }
                    }
                }
            }
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") >= 0) {
                int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 1);
            }

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") <= 1) {
                player.hurt(DamageSource.STARVE, 2.5f);
            }
            player.getFoodData().setFoodLevel(20);
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10 * 20, 1, true, false, false));

        }
    }
}
