package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.core.init.DamageSources;
import com.hixlepod.hixlepodsorigins.core.init.DamageTypes;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class Maxwell extends Origin {

    //CorkyPorky3379
    //Nerd
    public static String NAME = "Nycticorax_";

    public static void Ability1(ServerPlayer player) {
        for (Entity entity : player.level().getServer().getLevel(player.level().dimension()).getAllEntities()) {
            if (entity instanceof LivingEntity) {
                if (entity.position().distanceTo(player.position()) < 12) {
                    if (player.getTeam() != entity.getTeam()) {

                        Vec3 difference = player.position().subtract(entity.position());
                        Vec3 normalizedDifference = difference.normalize();
                        entity.setDeltaMovement(normalizedDifference.multiply(-7, -2, -7));

                        //entity.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(4, player));
                        entity.hurt(player.damageSources().playerAttack(player), OriginsUtil.damageScale(4, player));

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
                player.hurt(new DamageSources(player.level().registryAccess()).extinguish(), 0.1f);
            }
        }
    }
}
