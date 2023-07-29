package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.common.events.FoodLists;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Arrays;

public class DrinkItem extends Item {

    public DrinkItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player) livingEntity : null;

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (itemStack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        if (player != null) {
            if (!Arrays.asList(FoodLists.ROBOTS).contains(player.getName())) {
                if (Arrays.asList(FoodLists.TRANSFORMERS_DRINKS).contains(player.getMainHandItem().getItem())) {
                    OriginsDamageSource.hurt(player, 3000f, OriginsDamageSource.ENERGON_POISONING);
                }
            }
        }

        livingEntity.gameEvent(GameEvent.DRINK);
        return itemStack;
    }

    public UseAnim getUseAnimation(ItemStack p_42931_) {
        return UseAnim.DRINK;
    }
}
