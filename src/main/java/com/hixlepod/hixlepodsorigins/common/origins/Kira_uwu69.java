package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import virtuoel.pehkui.api.ScaleTypes;

public class Kira_uwu69 {

    public static String NAME = "kira_uwu69";

    public static double ENERGON_AMOUNT = 6000.0;

    public static void SetEnergon(Player player) {
        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", 6000 * OriginsManager.ticks);
    }

    public static void setStats(Player player) {

        ScaleTypes.HEIGHT.getScaleData(player).setScale(1.45f);
        ScaleTypes.WIDTH.getScaleData(player).setScale(1.45f);
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Kira_uwu69.NAME))) {

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") >= 0) {
                int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 1);
            }

            if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon") <= 1) {
                player.hurt(DamageSource.STARVE, 2.5f);
            }

            player.getFoodData().setFoodLevel(20);

            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10 * 20, 1, true, false, false));

            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2") / OriginsManager.ticks) <= 90.0) {
                ProtectThoseGodDamnPeople(player);
            }
        }
    }

    public static void Ability1(ServerPlayer player) {
        for (Entity entity : player.getLevel().getAllEntities()) {
            if (entity instanceof LivingEntity) {

                if (entity.position().distanceTo(player.position()) < 16) {
                    if (!entity.equals(player) && !(entity.getTeam() == player.getTeam())) {

                        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(player.getLevel());

                        lightning.moveTo(entity.position());
                        lightning.setDamage(OriginsUtil.damageScale(5, player));
                        lightning.setVisualOnly(true);

                        player.getLevel().addFreshEntity(lightning);
                    }
                }
            }
        }
    }

    public static void Ability2(ServerPlayer player) {
        for (Player entity : player.getServer().getPlayerList().getPlayers()) {
            if (entity.equals(player) || (entity.getTeam() == player.getTeam())) {

                entity.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "A shield has been summoned around you."));
                entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_ProtectThePeople", true);
            }
        }
    }

    public static void ProtectThoseGodDamnPeople(Player player) {
        for (Player entity : player.getServer().getPlayerList().getPlayers()) {
            if (entity.equals(player) || (entity.getTeam() == player.getTeam())) {

                if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2") / OriginsManager.ticks) <= 90.0) {

                    if (entity.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_ProtectThePeople") == true) {
                        player.getServer().getLevel(player.getLevel().dimension()).sendParticles(
                                ParticleTypes.DOLPHIN,
                                entity.position().x() + OriginsUtil.randomDouble(-0.45, 0.45),
                                entity.position().y() + OriginsUtil.randomDouble(0.3, 1.2),
                                entity.position().z() + OriginsUtil.randomDouble(-0.45, 0.45), 0, 0.4d, 1d, 0.4d, 0d);

                        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5 * 20, 2, true, false, true));
                    }
                } else {
                    entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_ProtectThePeople", false);
                }
            }
        }
    }
}
