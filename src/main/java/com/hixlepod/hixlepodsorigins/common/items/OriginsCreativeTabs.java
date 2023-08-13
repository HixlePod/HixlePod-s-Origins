package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class OriginsCreativeTabs {

    public static final CreativeModeTab HIXLEPOD_ORIGINS_TAB = new CreativeModeTab("hixlepodsOriginsTab") {
        @Override
        public @NotNull ItemStack makeIcon() {

            return new ItemStack(ItemInit.THE_HOLY_LYRE.get());
        }
    };

    public static final CreativeModeTab HIXLEPOD_ORIGINS_COMPONENT_TAB = new CreativeModeTab("hixlepodsOriginsComponentTab") {
        @Override
        public @NotNull ItemStack makeIcon() {

            return new ItemStack(ItemInit.THE_HOLY_LYRE.get());
        }
    };
}
