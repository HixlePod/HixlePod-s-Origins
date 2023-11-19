package com.hixlepod.hixlepodsorigins.core.init.CreativeTabs;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.init.CreativeTabsInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Origins_EndExpansionAndMagic {

    public static final DeferredRegister<CreativeModeTab> ORIGINS_CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HixlePodsOrigins.MODID);

    public static final RegistryObject<CreativeModeTab> ORIGINS_COMPONENT_TAB = ORIGINS_CREATIVE_TABS.register("hixlepods_origins_end_expansion_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemInit.VOIDSTONE_ORE.get()))
                    .title(Component.literal("HixlePod's Origins: End Expansion & Magic"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ItemInit.VOIDSTONE_ORE.get());
                        output.accept(ItemInit.MAGIC_DUST.get());
                    })
                    .build());
}
