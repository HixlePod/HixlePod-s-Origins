package com.hixlepod.hixlepodsorigins.core.init.CreativeTabs;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.init.ComponentModuleItems;
import com.hixlepod.hixlepodsorigins.core.init.CreativeTabsInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Origins_CarpentryAndPreciseEngineering {

    public static final DeferredRegister<CreativeModeTab> ORIGINS_CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HixlePodsOrigins.MODID);

    public static final RegistryObject<CreativeModeTab> ORIGINS_COMPONENT_TAB = ORIGINS_CREATIVE_TABS.register("hixlepods_origins_engineering_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ComponentModuleItems.ELECTRONIC_SCRAP.get()))
                    .title(Component.literal("HixlePod's Origins: Engineering"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ComponentModuleItems.ELECTRONIC_SCRAP.get());
                        output.accept(ComponentModuleItems.SIMPLE_COMPONENT.get());
                        output.accept(ComponentModuleItems.COMMS.get());
                    })
                    .build());
}
