package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid.CrispyChordioid;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.FudgeInvisibilityONS2CPacket;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;

public class KyoWing3809 {

    //KyoWing
    public static String NAME = "KyoWing";

    public static boolean GROW_PLANTS = false;

    public static void setStats(Player player) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.15);
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(40.0);
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.5);
        player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()).setBaseValue(3.0);
    }

    public static void Ability1(ServerPlayer player) {
        GROW_PLANTS = !GROW_PLANTS;
        player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Natures healing set to " + GROW_PLANTS));
    }

    public static void Ability2(ServerPlayer player) {

    }

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
            for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
                if (entity instanceof LivingEntity) {

                    if (entity.position().distanceTo(player.position()) < 30) {
                        if (entity.equals(player) || entity.getTeam() == player.getTeam() || entity.getType() == EntityType.HORSE ||
                            entity.getType() == EntityType.DONKEY || entity.getType() == EntityType.SKELETON_HORSE) {

                            if (!((LivingEntity) entity).hasEffect(MobEffects.REGENERATION)) {
                                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 8 * 20, 1, true, false));
                            }
                            player.getServer().getLevel(player.getLevel().dimension()).sendParticles(ParticleTypes.HAPPY_VILLAGER, entity.position().x() + OriginsUtil.randomDouble(-0.45, 0.45), entity.position().y() + OriginsUtil.randomDouble(0.3, 1.2), entity.position().z() + OriginsUtil.randomDouble(-0.45, 0.45), 0, 0.4d, 1d, 0.4d, 0d);
                        }
                    }
                }
            }

            BlockPos blockPos1 = new BlockPos(player.position().x(), player.position().y() - 1, player.position().z());
            BlockPos blockPos2 = new BlockPos(player.position().x(), player.position().y() - 2, player.position().z());
            BlockPos blockPos3 = new BlockPos(player.position().x(), player.position().y() - 3, player.position().z());

            Block block1 = player.getLevel().getBlockState(blockPos1).getBlock();
            Block block2 = player.getLevel().getBlockState(blockPos2).getBlock();
            Block block3 = player.getLevel().getBlockState(blockPos3).getBlock();

            if (block1 == Blocks.GRASS_BLOCK || block2 == Blocks.GRASS_BLOCK || block3 == Blocks.GRASS_BLOCK) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2 * 20, 0, true, false));
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 0, true, false));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 0, true, false));
            }
        }
    }
}
