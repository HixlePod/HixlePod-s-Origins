package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityCompass;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityEcho;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityPumkin;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityRune;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.ECHO.get(), EntityEcho.createAttributes().build());
        event.put(EntityInit.COMPASS.get(), EntityCompass.createAttributes().build());
        event.put(EntityInit.RUNE.get(), EntityRune.createAttributes().build());
        event.put(EntityInit.PUMKIN.get(), EntityPumkin.createAttributes().build());
    }

}
