package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MysteryBundleItem extends Item {


    public MysteryBundleItem(Properties p_41383_) {
        super(p_41383_);
    }

    /*
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);


        if (!(player instanceof ServerPlayer) && level.isClientSide()) {
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, item);
        }
        ServerPlayer serverPlayer = (ServerPlayer) player;

        // Generate items from loot table, give to player.
        boolean openWholeStack = serverPlayer.isCrouching();


        ResourceLocation loc = new ResourceLocation(HixlePodsOrigins.MODID, "loot_tables/mystery_bundle_table.json");

        LootTables ltManager = level.getServer().getLootTables();
        LootTable lt = ltManager.get(loc);
        LootContextParamSet ltps = lt.getParamSet();

        LootContext.Builder builder = new LootContext.Builder((ServerLevel) level);
        List<ItemStack> generated =  lt.getRandomItems(builder.create(ltps));


        // Play item pickup sound...
        serverPlayer.level.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F,
                ((serverPlayer.getRandom().nextFloat() - serverPlayer.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
        item.shrink(openWholeStack ? item.getCount() : 1);

        return InteractionResultHolder.sidedSuccess(item, level.isClientSide());
    }

     */
}
