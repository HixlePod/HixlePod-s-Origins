package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundEventRegistration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HixlePodsOrigins.MODID);

    public static final RegistryObject<SoundEvent> ZA_WARUDO_ACTIVATE = registrySoundEvent("za_warudo_activate");

    public static final RegistryObject<SoundEvent> ZA_WARUDO_DEACTIVATE = registrySoundEvent("za_warudo_deactivate");

    public static final RegistryObject<SoundEvent> NINJA = registrySoundEvent("ninja");

    public static final RegistryObject<SoundEvent> GROUND_BRIDGE_PUMPS = registrySoundEvent("ground_bridge_pumps");
    public static final RegistryObject<SoundEvent> GROUND_BRIDGE_HUMMING_BEGINS = registrySoundEvent("ground_bridge_humming_begins");
    public static final RegistryObject<SoundEvent> GROUND_BRIDGE_HUMMING = registrySoundEvent("ground_bridge_humming");

    public static final RegistryObject<SoundEvent> CYBER_BULLYING = registrySoundEvent("cyber_bullying");
    public static final RegistryObject<SoundEvent> LASER_SHOOT = registrySoundEvent("laser_shoot");

    private static RegistryObject<SoundEvent> registrySoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HixlePodsOrigins.MODID, name)));
    }

    private static SoundEvent register(ResourceLocation p_263388_, ResourceLocation p_263340_) {
        return (SoundEvent) Registry.register(BuiltInRegistries.SOUND_EVENT, p_263388_, SoundEvent.createVariableRangeEvent(p_263340_));
    }

    //hack mathew pc
    //Dont remove comment above, it is very important
}
