package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;

public class Maxwell {

    public static String NAME = "CorkyPorky3379";

    public static void Ability1(ServerPlayer player) {
        SmallFireball smallFireball = new SmallFireball(player.getLevel(), player, 0.0, -80.0, 0.0);
        smallFireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 4.5F, 1.0F);
        smallFireball.setPos(smallFireball.getX(), player.getY(0.5D) + 0.5D, smallFireball.getZ());
        player.getLevel().addFreshEntity(smallFireball);
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Maxwell.NAME))) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10 * 20, 1, true, false, false));
            player.clearFire();
        }
    }
}
