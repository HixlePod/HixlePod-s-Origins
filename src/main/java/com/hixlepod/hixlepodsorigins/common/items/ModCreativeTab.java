package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {

    public static final CreativeModeTab HIXLEPOD_ORIGINS_TAB = new CreativeModeTab("hixlepodsOriginsTab") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ItemInit.THE_HOLY_LYRE.get());
        }
    };
}
