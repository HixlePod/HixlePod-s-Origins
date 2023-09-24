package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.init.DamageTypes;
import com.hixlepod.hixlepodsorigins.core.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.server.ServerLifecycleHooks;

public class J_Curve {

    public static String NAME = "J_Curve";

    public static void Ability1(ServerPlayer player) {
        for (Entity entity : player.level().getServer().getLevel(player.level().dimension()).getAllEntities()) {
            if (entity.position().distanceTo(player.position()) < 10) {
                if (!entity.equals(player)) {
                    entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_ZA_WARUDO", true);

                    entity.getPersistentData().putDouble(HixlePodsOrigins.MODID + "_ZA_WARUDO_X", entity.position().x());
                    entity.getPersistentData().putDouble(HixlePodsOrigins.MODID + "_ZA_WARUDO_Y", entity.position().y());
                    entity.getPersistentData().putDouble(HixlePodsOrigins.MODID + "_ZA_WARUDO_Z", entity.position().z());

                }
            }
        }

        for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
            serverPlayer.level().playSound(null, player.position().x, player.position().y, player.position().z, SoundInit.ZA_WARUDO_ACTIVATE.get(), SoundSource.PLAYERS, 1, 1);
        }
    }

    public static void tick() {

        ServerPlayer player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(J_Curve.NAME);

        if (player != null) {
            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1") / OriginsManager.ticks) < 100.0) {
                for (Entity entity : player.level().getServer().getLevel(player.level().dimension()).getAllEntities()) {
                    if (entity.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_ZA_WARUDO") == true) {
                        entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_ZA_WARUDO", false);
                    }
                }
            } else {
                for (Entity entity : player.level().getServer().getLevel(player.level().dimension()).getAllEntities()) {
                    if (entity.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_ZA_WARUDO") == true) {
                        double entity_x = entity.getPersistentData().getDouble(HixlePodsOrigins.MODID + "_ZA_WARUDO_X");
                        double entity_y = entity.getPersistentData().getDouble(HixlePodsOrigins.MODID + "_ZA_WARUDO_Y");
                        double entity_z = entity.getPersistentData().getDouble(HixlePodsOrigins.MODID + "_ZA_WARUDO_Z");

                        entity.setPos(new Vec3(entity_x, entity_y, entity_z));
                    }
                }
            }

            if (player.getName().contains(Component.literal(J_Curve.NAME))) {
                if (player.isInWaterOrRain()) {
                    //player.hurt(DamageSource.MAGIC, 0.1f);
                    //player.hurt(player.damageSources().magic(), 0.1f);
                    player.hurt(new DamageSource(DamageTypes.EXTINGUISH.getHolder().get()), 0.1f);
                }
            }
        }
    }
}
