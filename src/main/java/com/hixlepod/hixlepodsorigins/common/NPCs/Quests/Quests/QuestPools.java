package com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests;

import net.minecraft.world.item.ItemStack;

public interface QuestPools  {

    default double getWeight() {
        return 0;
    }

    default String getDisplayName() {
        return null;
    }

    default String getDescription() {
        return null;
    }

    default ItemStack getWanted() {
        return null;
    }

    default ItemStack getReward() {
        return null;
    }
}
