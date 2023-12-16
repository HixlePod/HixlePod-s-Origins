package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ofcourseidid extends Origin {

    public static String NAME = "ofcourseidid";

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0);
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.15);
    }

    public static void Ability1(ServerPlayer player) {
        Level level = player.level();
        
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide()) {
            ThrownEnderpearl thrownenderpearl = new ThrownEnderpearl(level, player);
            thrownenderpearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(thrownenderpearl);
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(ofcourseidid.NAME))) {
            player.getServer().getLevel(player.level().dimension()).sendParticles(ParticleTypes.PORTAL, player.position().x(), player.position().y() + 1, player.position().z(), 0, 0.4d, 1d, 0.4d, 0d);


            if (player.isInWaterOrRain()) {
                if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_WaterDamageCooldown") != 0) {
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_WaterDamageCooldown",
                            player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_WaterDamageCooldown") - 1);
                } else {
                    player.hurt(player.damageSources().drown(), 2.5f);
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_WaterDamageCooldown", 2 * OriginsManager.ticks);
                }
            }
        }
    }
}
