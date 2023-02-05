package com.hixlepod.hixlepodsorigins;

import com.hixlepod.hixlepodsorigins.common.events.ClientModEvents;
import com.hixlepod.hixlepodsorigins.common.events.CommonModEvents;
import com.hixlepod.hixlepodsorigins.common.events.GameplayEvents;
import com.hixlepod.hixlepodsorigins.common.events.ServerModEvents;
import com.hixlepod.hixlepodsorigins.core.init.*;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HixlePodsOrigins.MODID)
public class HixlePodsOrigins
{
    public static final String MODID = "hixlepodsorigins";

    public HixlePodsOrigins() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Register Inits
        ItemInit.ITEMS.register(eventBus);
        BlockInit.BLOCKS.register(eventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(eventBus);
        SoundInit.SOUND_EVENTS.register(eventBus);
        ConfiguredFeatureInit.CONFIGURED_FEATURES.register(eventBus);
        PlacedFeatureInit.PLACED_FEATURES.register(eventBus);
        EntityInit.ENTITIES.register(eventBus);
        MenuTypesInit.MENUS.register(eventBus);

        ItemInit.VANILLA_ITEMS.register(eventBus);

        //Event listeners
        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(new ClientModEvents());
        MinecraftForge.EVENT_BUS.register(new ServerModEvents());
        MinecraftForge.EVENT_BUS.register(new CommonModEvents());
        MinecraftForge.EVENT_BUS.register(new GameplayEvents());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NetworkManager.Register();
    }
}
