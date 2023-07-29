package com.hixlepod.hixlepodsorigins.common.items;

import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class ExampleCuriosItem implements ICurio {

    private final ItemStack stack;

    public ExampleCuriosItem(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ItemStack getStack() {
        return this.stack;
    }
}
