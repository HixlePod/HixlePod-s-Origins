package com.hixlepod.hixlepodsorigins.common.origins;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.caelus.api.CaelusApi;
import virtuoel.pehkui.api.ScaleTypes;

public class Stamce {

    public static String NAME = "Stamce";

    public static boolean JUMP_HIGH = false;

    public static void setStats(Player player) {
        ScaleTypes.HEIGHT.getScaleData(player).setScale(0.45f);
        ScaleTypes.WIDTH.getScaleData(player).setScale(0.45f);

        ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.5f);

        player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(1.0);

        player.getAttribute(ForgeMod.REACH_DISTANCE.get()).setBaseValue(5.5);
        player.getAttribute(ForgeMod.ATTACK_RANGE.get()).setBaseValue(5.5);

        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(18.0);


    }

    public static void Ability1(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 20, 4, true, false, true));
    }

    public static void Ability2(ServerPlayer player) {
        JUMP_HIGH = !JUMP_HIGH;

        if (JUMP_HIGH) {
            ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(3.0f);
            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You now jump higher!"));

        } else {
            ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.5f);
            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You no longer jump higher."));
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Stamce.NAME))) {

            if (player.isInWaterOrRain()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5 * 20, 0, true, false, true));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5 * 20, 0, true, false, true));
            }
        }
    }

}
