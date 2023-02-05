package com.hixlepod.hixlepodsorigins.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PassPortItem extends Item {

    public PassPortItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty("May get you arrested..."));

        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }
}
