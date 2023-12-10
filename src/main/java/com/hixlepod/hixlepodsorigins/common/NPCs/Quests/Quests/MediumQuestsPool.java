package com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests;

import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum MediumQuestsPool implements QuestPools {

    SHEAR_SHEEP_HUNT(20,
            ChatFormatting.GREEN + "Shear Sheep Hunt",
            ChatFormatting.LIGHT_PURPLE + "I need a few more beds, bring me some wool yeah?",
            new ItemStack(Items.WHITE_WOOL, OriginsUtil.randomInt(20, 50)),
            new ItemStack(ItemInit.BRONZE_YARN.get(), OriginsUtil.randomInt(5, 9)));

    private double weight;
    private String display_name;
    private String description;
    private ItemStack wanted_item;
    private ItemStack reward;

    MediumQuestsPool(double WEIGHT, String DISPLAY_NAME, String DESCRIPTION, ItemStack WANTED_ITEM, ItemStack REWARD) {
        this.weight = WEIGHT;
        this.display_name = DISPLAY_NAME;
        this.description = DESCRIPTION;
        this.wanted_item = WANTED_ITEM;
        this.reward = REWARD;
    }

    public double getWeight() {
        return weight;
    }

    public String getDisplayName() {
        return display_name;
    }

    public String getDescription() {
        return description;
    }

    public ItemStack getWanted() {
        return wanted_item;
    }

    public ItemStack getReward() {
        return reward;
    }

}
