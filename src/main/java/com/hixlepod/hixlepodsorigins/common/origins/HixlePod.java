package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.items.HolyLyre.HolyLyreGui;
import com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys.GodOfFurrys;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class HixlePod {

    public static String NAME = "HixlePod";

    public static boolean Overclocked = false;
    public static float DEFENCE = 65.0F;

    public static double ENERGON_AMOUNT = 6000.0;

    public static void setStats(Player player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.5);
        DEFENCE = 65.0F;
        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);
    }

    public static void Ability1(Player player) {
        Overclocked = true;

        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.5);
        DEFENCE = 55.0F;
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(HixlePod.NAME))) {
            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1") / OriginsManager.ticks) <= 100.0) {
                Overclocked = false;

                player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0);
                DEFENCE = 65.0F;
            }

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
