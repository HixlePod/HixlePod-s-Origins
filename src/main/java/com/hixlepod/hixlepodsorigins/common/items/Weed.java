package com.hixlepod.hixlepodsorigins.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Weed extends Item {


    public Weed(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty(ChatFormatting.STRIKETHROUGH + "ur welk racc"));
        toolTip.add(Component.nullToEmpty(ChatFormatting.BLACK + "" + ChatFormatting.ITALIC + "I regret ever adding this"));
        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }
}
