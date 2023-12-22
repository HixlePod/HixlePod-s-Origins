package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.core.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TrumpetItem extends Item {

    public TrumpetItem(Properties p_41383_) {
        super(p_41383_);
    }

    public static void scare(Level world, LivingEntity user) {
        if (!world.isClientSide) {
            List<LivingEntity> spooked = world.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().inflate(10.0D));
            for (LivingEntity entity : spooked) {
                if (entity == user) continue;

                double deltaX = entity.getX() - user.getX() + world.random.nextDouble() - world.random.nextDouble();
                double deltaZ = entity.getZ() - user.getZ() + world.random.nextDouble() - world.random.nextDouble();
                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

                entity.hurtMarked = true;
                entity.addDeltaMovement(new Vec3(0.5 * deltaX / distance, 5.0D / (10.0D + distance), 0.5 * deltaZ / distance));

                if (entity instanceof Creeper) {
                } else {

                    if (entity instanceof Monster) {
                        Monster attackEntity = (Monster) entity;
                        attackEntity.setTarget(user);
                    }

                    if (entity instanceof NeutralMob) {
                        NeutralMob attackEntity = (NeutralMob) entity;
                        attackEntity.setTarget(user);
                    }
                }

            }
        }
    }

    public UseAnim getUseAnimation(ItemStack p_42931_) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 55;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return null;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), (int) (1.5 * 20));

        player.playSound(SoundInit.DOOT_DOOT.get(), 0.2f, 0.9F + player.level().random.nextFloat() * 0.2F);
        TrumpetItem.scare(player.level(), player);

        return InteractionResultHolder.success(player.getMainHandItem());
    }
}
