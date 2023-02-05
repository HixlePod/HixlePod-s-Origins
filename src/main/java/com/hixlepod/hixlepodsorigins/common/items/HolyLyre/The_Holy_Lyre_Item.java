package com.hixlepod.hixlepodsorigins.common.items.HolyLyre;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class The_Holy_Lyre_Item extends Item {

    public The_Holy_Lyre_Item(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty("Can play most songs... maybe..."));

        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }
}
