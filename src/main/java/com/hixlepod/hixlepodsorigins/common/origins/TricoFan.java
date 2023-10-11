package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.core.init.DamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import virtuoel.pehkui.api.*;

public class TricoFan {

    public static String NAME = "TricoFan";

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(10.0);

        player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(1.1);

        ScaleTypes.HEIGHT.getScaleData(player).setScale(0.4f);
        ScaleTypes.WIDTH.getScaleData(player).setScale(0.4f);
        ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.55f);
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.15);
        player.getAttribute(ForgeMod.ENTITY_REACH.get()).setBaseValue(5.5);
        player.getAttribute(ForgeMod.BLOCK_REACH.get()).setBaseValue(5.5);
    }


    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(TricoFan.NAME))) {

            //player.getLevel().getBlockCollisions(player, );

            //Night vision
            if (!isUnderBlock(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20 * 20, 0, true, false, false));
            }

            if (isInLight(player) && !(player.getInventory().getArmor(3).getItem() == Items.CARVED_PUMPKIN ||
                            player.getInventory().getArmor(3).getItem() == Items.PLAYER_HEAD)) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10 * 20, 0, true, false, false));
            }

            BlockPos blockPos1 = new BlockPos((int) player.position().x(), (int) player.position().y() - 1, (int) player.position().z());

            Block block1 = player.level().getBlockState(blockPos1).getBlock();

            if (block1 == Blocks.SCULK || block1 == Blocks.SCULK_SENSOR) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 5 * 20, 0, true, false));
                player.hurt(new DamageSources(player.level().registryAccess()).sculk_drain(), 1);
            }
        }
    }

    public static boolean isInLight(Player player) {
        float f = player.getLightLevelDependentMagicValue();
        if (f >= 0.5F) {
            return true;
        }

        return false;
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
