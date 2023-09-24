package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.Arrays;

public class KyoWing3809 {

    //KyoWing
    public static String NAME = "KyoWing";

    public static boolean GROW_PLANTS = false;

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.15);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0);
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.5);
        player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(3.0);
        ScaleTypes.JUMP_HEIGHT.getScaleData(player).setScale(1.2f);

        ScaleTypes.HEIGHT.getScaleData(player).setScale(1.25f);
        ScaleTypes.WIDTH.getScaleData(player).setScale(1.25f);

        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_LET_IT_GROWW", 3 * OriginsManager.ticks);
    }

    public static void Ability1(ServerPlayer player) {
        GROW_PLANTS = !GROW_PLANTS;

        if (GROW_PLANTS) {
            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You focus your power, nature begins to grow around you."));
        } else {
            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You rest your power."));
        }
    }

    public static void Ability2(ServerPlayer player) {

    }

    static Block[] GOOD_BLOCKS = {Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK, Blocks.MOSS_CARPET};

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
            for (Entity entity : player.getServer().getLevel(player.level().dimension()).getAllEntities()) {
                if (entity instanceof LivingEntity) {

                    if (entity.position().distanceTo(player.position()) < 30) {
                        if (entity.equals(player) || entity.getTeam() == player.getTeam() || entity instanceof Animal) {

                            if (!((LivingEntity) entity).hasEffect(MobEffects.REGENERATION)) {
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 8 * 20, 1, true, false));
                            }

                            if (OriginsUtil.didChance(10)) {
                                player.getServer().getLevel(player.level().dimension()).sendParticles(ParticleTypes.HAPPY_VILLAGER, entity.position().x(), entity.position().y(), entity.position().z(), 3, 0.45d, 1d, 0.45d, 0.1);
                            }
                        }
                    }
                }
            }

            BlockPos blockPos1 = new BlockPos((int) player.position().x(), (int) player.position().y() - 1, (int) player.position().z());
            BlockPos blockPos2 = new BlockPos((int) player.position().x(), (int) player.position().y() - 2, (int) player.position().z());
            BlockPos blockPos3 = new BlockPos((int) player.position().x(), (int) player.position().y() - 3, (int) player.position().z());

            Block block1 = player.level().getBlockState(blockPos1).getBlock();
            Block block2 = player.level().getBlockState(blockPos2).getBlock();
            Block block3 = player.level().getBlockState(blockPos3).getBlock();

            if (Arrays.asList(GOOD_BLOCKS).contains(block1) || Arrays.asList(GOOD_BLOCKS).contains(block2) || Arrays.asList(GOOD_BLOCKS).contains(block3)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2 * 20, 0, true, false));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 0, true, false));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 0, true, false));
            }

            if (GROW_PLANTS) {
                if (OriginsUtil.didChance(15)) {
                    player.getServer().getLevel(player.level().dimension()).sendParticles(ParticleTypes.ELECTRIC_SPARK, player.position().x(), player.position().y() + player.getEyeHeight() + 0.5, player.position().z(), 3, 0.05d, 0.1d, 0.05d, 0.1);
                }
                if (player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_LET_IT_GROWW") != 0) {
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_LET_IT_GROWW",
                            player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_LET_IT_GROWW") - 1);
                } else {
                    LETTTT_ITTTT_GROWWWWW(player);
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_LET_IT_GROWW", 2 * OriginsManager.ticks);
                }
            }
        }
    }

    public static void LETTTT_ITTTT_GROWWWWW(Player player) {

        for (int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                for (int y = -8; y <= 8; y++) {
                    BlockPos BlockPos = new BlockPos((int) player.position().x() + x, (int) player.position().y() + y, (int) player.position().z() + z);
                    BlockState BlockState = player.level().getBlockState(BlockPos);

                    if (BlockState.getBlock() instanceof BonemealableBlock) {
                        BonemealableBlock BonemealableBlock = (BonemealableBlock) BlockState.getBlock();

                        ServerLevel ServerLevel = player.getServer().getLevel(player.level().dimension());
                        if (OriginsUtil.didChance(10)) {
                            if (BonemealableBlock.isBonemealSuccess(player.level(), player.level().random, BlockPos, BlockState)) {
                                BonemealableBlock.performBonemeal(ServerLevel, player.level().random, BlockPos, BlockState);
                            }
                        }
                    }
                }
            }
        }
    }
}
