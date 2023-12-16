package com.hixlepod.hixlepodsorigins.common.origins;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;

public class CatGirlSeeka extends Origin {

    public static String NAME = "CatGirlSeeka";

    public static void setStats(Player player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0);

    }

    public static void setAbilityData(Player player) {
        player.getAbilities().setFlyingSpeed(0.03F);
        player.getAbilities().mayfly = true;
        player.getServer().getPlayerList().getPlayerByName(CatGirlSeeka.NAME).onUpdateAbilities();
    }

    public static void Ability1(ServerPlayer player) {
        SmallFireball smallFireball = new SmallFireball(player.level(), player, 0.0, -80.0, 0.0);
        smallFireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 4.5F, 1.0F);
        smallFireball.setPos(smallFireball.getX(), player.getY(0.5D) + 0.5D, smallFireball.getZ());
        player.level().addFreshEntity(smallFireball);
    }

    public static void Ability2(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 20, 2, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30 * 20, 4, true, false, false));
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10 * 20, 1, true, false, false));
            player.clearFire();
        }
    }
}
