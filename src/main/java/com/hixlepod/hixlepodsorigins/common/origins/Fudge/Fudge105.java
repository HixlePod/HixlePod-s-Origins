package com.hixlepod.hixlepodsorigins.common.origins.Fudge;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

public class Fudge105 {
    //fudge105
    public static String NAME = "fudge105";

    public static boolean isInvisible = false;

    public static void setStats(Player player) {
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).setBaseValue(3.0);

        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_FireDamageCooldown", 3 * OriginsManager.ticks);
    }

    public static void Ability1(ServerPlayer player) {
        //Fudge105.isInvisible = true;

        for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
            NetworkManager.sendToPlayer(new FudgeInvisibilityONS2CPacket(), serverPlayer);
        }
    }

    public static void Ability2(ServerPlayer player) {
        for (int i = 0; i < 6; i++) {
            Entity entity = EntityType.DROWNED.create(player.getLevel());

            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));

            entity.moveTo(player.position());
            entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_DropItems", true);

            player.getLevel().addFreshEntity(entity);
        }
    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(Fudge105.NAME))) {

            if (!player.isInWaterOrRain() && player.getServer().overworld().isDay() && isUnderBlock(player)) {
                if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                    if (player.isOnFire()) {
                        if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_FireDamageCooldown") != 0) {
                            player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_FireDamageCooldown",
                                    player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_FireDamageCooldown") - 1);
                        } else {
                            player.setRemainingFireTicks(20 * 3);
                            player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_FireDamageCooldown", 3 * OriginsManager.ticks);
                        }
                    } else {
                        player.setRemainingFireTicks(20 * 3);
                    }
                }
            }

            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10 * 20, 1, true, false, false));

            if ((player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1") / OriginsManager.ticks) <= 50.0) {
                //Fudge105.isInvisible = false;
                for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
                    NetworkManager.sendToPlayer(new FudgeInvisibilityOFFS2CPacket(), serverPlayer);
                }
            }

            if (player.isInWaterOrRain()) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3 * 20, 1, true, false, false));
            }
        }
    }

    public static boolean isUnderBlock(Player player) {
        int player_y = (int) player.position().y();

        for (int y = player_y; y < player.getServer().overworld().getMaxBuildHeight(); y++) {
            Level level = player.getLevel();
            if (level.getBlockState(new BlockPos(player.position().x, y, player.position().z)).getMaterial().isSolid()) {
                return false;
            }
        }
        return true;
    }
}
