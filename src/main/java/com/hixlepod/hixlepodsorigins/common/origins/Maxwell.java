package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.phys.Vec3;

public class Maxwell {

    //CorkyPorky3379
    public static String NAME = "CorkyPorky3379";

    public static void Ability1(ServerPlayer player) {
        for (Entity entity : player.getLevel().getAllEntities()) {
            if (entity instanceof LivingEntity) {
                if (entity.position().distanceTo(player.position()) < 12) {
                    if (player.getTeam() != entity.getTeam()) {

                        Vec3 difference = player.position().subtract(entity.position());
                        Vec3 normalizedDifference = difference.normalize();
                        entity.setDeltaMovement(normalizedDifference.multiply(-7, -2, -7));

                        entity.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(4, player));

                        entity.setRemainingFireTicks(10 * 20);
                    }
                }
            }
        }

        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15 * 20, 2, true, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 2, true, false, true));
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Maxwell.NAME))) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10 * 20, 1, true, false, false));
            player.clearFire();

            if (player.isInWaterOrRain()) {

                OriginsDamageSource.hurt(player, 1f, OriginsDamageSource.EXTINGUISH);
            }
        }
    }
}
