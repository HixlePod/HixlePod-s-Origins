package com.hixlepod.hixlepodsorigins.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Example_Item extends Item {

    public Example_Item(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty("For I may be a simple item, I was a great example on how to create everything you are today."));
        toolTip.add(Component.nullToEmpty("Without me this mod would cease to exist."));
        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }
}
