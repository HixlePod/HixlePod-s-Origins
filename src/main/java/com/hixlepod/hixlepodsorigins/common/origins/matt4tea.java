package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;

public class matt4tea extends Origin {

    public static String NAME = "matt4tea";

    public static boolean EXTREME_FOCUS = false;

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3);
    }

    public static void Ability1(ServerPlayer player) {
        EXTREME_FOCUS = true;

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 15 * 20, 2, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15 * 20, 0, true, false, false));
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(matt4tea.NAME))) {
            if (player.isInWater()) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 5 * 20, 0, true, false, false));

                if (EXTREME_FOCUS) {
                    player.hurtMarked = true;
                    player.setDeltaMovement(player.getDeltaMovement().add(0, 0.2, 0));
                }
            }

            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1") / OriginsManager.ticks) <= 50) {
                EXTREME_FOCUS = false;
            }
        }
    }
}
