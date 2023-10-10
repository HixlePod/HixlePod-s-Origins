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

public interface DamageTypes {

    ResourceKey<DamageType> RUST = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HixlePodsOrigins.MODID, "rust"));

    ResourceKey<DamageType> ACID_RAIN = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HixlePodsOrigins.MODID, "acid_rain"));

    ResourceKey<DamageType> ENERGON_POISONING = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HixlePodsOrigins.MODID, "energon_poisoning"));

    ResourceKey<DamageType> ANEMO_VORTEXT = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HixlePodsOrigins.MODID, "anemo_vortext"));

    ResourceKey<DamageType> EXTINGUISH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HixlePodsOrigins.MODID, "extinguish"));

    ResourceKey<DamageType> SCULK_DRAIN = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(HixlePodsOrigins.MODID, "sculk_drain"));
}
