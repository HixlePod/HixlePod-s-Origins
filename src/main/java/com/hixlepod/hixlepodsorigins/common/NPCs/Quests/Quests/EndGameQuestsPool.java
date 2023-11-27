package com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests;

import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum EndGameQuestsPool {

    SHEAR_SHEEP_HUNT(20,
            ChatFormatting.GREEN + "Shear Sheep Hunt",
            ChatFormatting.LIGHT_PURPLE + "I need a few more beds, bring me some wool yeah?",
            new ItemStack(Items.WHITE_WOOL, OriginsUtil.randomInt(20, 50)),
            new ItemStack(ItemInit.BRONZE_YARN.get(), OriginsUtil.randomInt(5, 9)));

    private double weight;

    EndGameQuestsPool(double WEIGHT, String DISPLAY_NAME, String DESCRIPTION, ItemStack WANTED_ITEM, ItemStack REWARD) {
        this.weight = WEIGHT;
    }

    public double getWeight() {
        return weight;
    }

}
