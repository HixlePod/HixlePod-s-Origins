package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class Folf_Gaming {

    public static String NAME = "Folf_Gaming";

    public static double ENERGON_AMOUNT = 6000.0;

    public static int laser_distance = 50;

    public static void SetEnergon(Player player) {
        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);
    }

    public static void setStats(Player player) {
    }

    public static void Ability1(ServerPlayer player) {

        Vec3 look = player.position().add(0, 1.4, 0);

        for (int i = 0; i < laser_distance; i++) {
            look = look.add(player.getLookAngle().x(), player.getLookAngle().y(), player.getLookAngle().z());

            OriginsUtil.sendParticle((ServerLevel) player.level(), new DustParticleOptions(new Vector3f(255, 0,0), 1f), new Vec3(look.x(), look.y(), look.z()), new Vec3(0, 0, 0), 0, 1);

            for (Entity entity : player.level().getServer().getLevel(player.level().dimension()).getAllEntities()) {
                if (entity.position().distanceTo(new Vec3(look.x(), look.y(), look.z())) < 1.5) {
                    if (!entity.equals(player)) {
                        //entity.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(1, player));
                        entity.hurt(player.damageSources().playerAttack(player), OriginsUtil.damageScale(1, player));
                    }
                }
            }
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Folf_Gaming.NAME))) {

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") >= 0) {
                int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 1);
            }

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") <= 1) {
                player.hurt(player.damageSources().starve(), 2.5f);
            }

            player.getFoodData().setFoodLevel(20);

            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10 * 20, 1, true, false, false));

        }
    }
}
