package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.server.ServerLifecycleHooks;

public class Blakpaw2244 {

    public static String NAME = "Blakpaw";

    public static double ENERGON_AMOUNT = 6000.0;

    public static void setStats(Player player) {

        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.085);

        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);

    }

    public static void Ability1(ServerPlayer player) {
        player.getLevel().explode(player, player.position().x(), player.position().y(), player.position().z(), 6.0F, false, Explosion.BlockInteraction.NONE);
    }

    public static void Ability2(ServerPlayer player) {
        player.getAbilities().setFlyingSpeed(0.025F);
        player.getAbilities().mayfly = !player.getAbilities().mayfly;
        player.getAbilities().flying = !player.getAbilities().flying;
        player.onUpdateAbilities();
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {

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
