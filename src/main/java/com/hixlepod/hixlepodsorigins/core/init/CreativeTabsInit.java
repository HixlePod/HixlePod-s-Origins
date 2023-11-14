package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.init.CreativeTabs.Origins_CarpentryAndPreciseEngineering;
import com.hixlepod.hixlepodsorigins.core.init.CreativeTabs.Origins_EndExpansionAndMagic;
import com.hixlepod.hixlepodsorigins.core.init.CreativeTabs.Origins_Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabsInit {


    public static final DeferredRegister<CreativeModeTab> ORIGINS_CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HixlePodsOrigins.MODID);

    public static void register(IEventBus eventBus) {
        Origins_Main.ORIGINS_CREATIVE_TABS.register(eventBus);
        Origins_EndExpansionAndMagic.ORIGINS_CREATIVE_TABS.register(eventBus);
        Origins_CarpentryAndPreciseEngineering.ORIGINS_CREATIVE_TABS.register(eventBus);
    }




}
