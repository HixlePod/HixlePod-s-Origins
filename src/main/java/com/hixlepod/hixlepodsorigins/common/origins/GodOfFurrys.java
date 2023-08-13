package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class GodOfFurrys {

    public static String NAME = "GodOfFurrys";

    public static int laser_distance = 50;
    public static boolean shield = false;

    public static void setStats(Player player) {
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0);
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.08);
    }

    public static void Ability1(ServerPlayer player) {

        Vec3 look = player.position().add(0, 1.4, 0);

        LaserAttackLoop:
        for (int i = 0; i < laser_distance; i++) {
            look = look.add(player.getLookAngle().x(), player.getLookAngle().y(), player.getLookAngle().z());

            OriginsUtil.sendParticle(player.getLevel(), new DustParticleOptions(new Vector3f(0, 0, 0), 1), new Vec3(look.x(), look.y(), look.z()), new Vec3(0, 0, 0), 1, 2);

            for (Entity entity : player.getLevel().getAllEntities()) {
                if (entity.position().distanceTo(new Vec3(look.x(), look.y(), look.z())) < 1.5) {
                    if (!entity.equals(player)) {
                        entity.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(1, player));
                        break LaserAttackLoop;
                    }
                }
            }
        }
    }

    public static void Ability2(ServerPlayer player) {
        shield = true;
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2") / OriginsManager.ticks) <= 100) {
                shield = false;
            }
        }
    }
}
