package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.caelus.api.CaelusApi;

public class gh0stlure extends Origin {


    //gh0stlure
    public static String NAME = "gh0stlure";

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(18.0);
        player.getAttribute(CaelusApi.getInstance().getFlightAttribute()).setBaseValue(1);
    }

    public static void Ability1(ServerPlayer player) {
        player.hurtMarked = true;
        player.setDeltaMovement(player.getDeltaMovement().add(0,0.5 * 5,0));
    }

    public static void Ability2(ServerPlayer player) {
        for (Entity entity : player.level().getServer().getLevel(player.level().dimension()).getAllEntities()) {
            if (entity instanceof LivingEntity) {

                if (entity.position().distanceTo(player.position()) < 30) {
                    if (!entity.equals(player) && !(entity.getTeam() == player.getTeam())) {
                        ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.GLOWING, 20 * 20, 6, true, false));
                    }
                }
            }
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
            if (player.getServer().overworld().isDay() && isUnderBlock(player)) {
                if (player.getInventory().getArmor(3).isEmpty()) {
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10 * 20, 0, true, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10 * 20, 0, true, false, false));
                }
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 10 * 20, 1, true, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1, true, false, false));
            }

            if (player.hasEffect(MobEffects.POISON)) {
                player.removeEffect(MobEffects.POISON);
            }

            if (player.hasEffect(MobEffects.HUNGER)) {
                player.removeEffect(MobEffects.HUNGER);
            }
        }
    }

    public static boolean isUnderBlock(Player player) {
        int player_y = (int) player.position().y();

        for (int y = player_y; y < player.getServer().overworld().getMaxBuildHeight(); y++) {
            Level level = player.level();
            if (level.getBlockState(new BlockPos((int) player.position().x, y, (int) player.position().z)).isSolid()) {
                return false;
            }
        }
        return true;
    }

}
