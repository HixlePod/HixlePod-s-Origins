package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DamageTypes {

    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(Registries.DAMAGE_TYPE, HixlePodsOrigins.MODID);

    public static final RegistryObject<DamageType> RUST = DAMAGE_TYPES.register("rust",
            () -> new DamageType("rust", 1));

    public static final RegistryObject<DamageType> ACID_RAIN = DAMAGE_TYPES.register("acid_rain",
            () -> new DamageType("acid_rain", 1));

    public static final RegistryObject<DamageType> ENERGON_POISONING = DAMAGE_TYPES.register("energon_poisoning",
            () -> new DamageType("energon_poisoning", 1));

    public static final RegistryObject<DamageType> ANEMO_VORTEXT = DAMAGE_TYPES.register("anemo_vortext",
            () -> new DamageType("anemo_vortext", 1));

    public static final RegistryObject<DamageType> EXTINGUISH = DAMAGE_TYPES.register("extinguish",
            () -> new DamageType("extinguish", 1));

    public static final RegistryObject<DamageType> SCULK_DRAIN = DAMAGE_TYPES.register("sculk_drain",
            () -> new DamageType("sculk_drain", 1));

}
