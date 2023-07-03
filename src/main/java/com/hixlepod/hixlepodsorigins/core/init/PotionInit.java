package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionInit {

    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, HixlePodsOrigins.MODID);

    public static final RegistryObject<Potion> AMBERGON_POTION = POTIONS.register("ambergon_potion",
            () -> new Potion(new MobEffectInstance(EffectsInit.AMBERGON_EFFECT.get(), 0, 0)));

    public static final RegistryObject<Potion> MALFUNCTION_POTION = POTIONS.register("malfunction_potion",
            () -> new Potion(new MobEffectInstance(EffectsInit.MALFUNCTION.get(), 120 * 20, 0)));

    public static final RegistryObject<Potion> LIGHTNING_POTION = POTIONS.register("lightning_potion",
            () -> new Potion(new MobEffectInstance(EffectsInit.LIGHTNING.get(), 5 * 20, 0)));

    public static final RegistryObject<Potion> FREEZE_POTION = POTIONS.register("freeze_potion",
            () -> new Potion(new MobEffectInstance(EffectsInit.FREEZE.get(), 120 * 20, 0)));

    public static final RegistryObject<Potion> CHAOS_POTION = POTIONS.register("chaos_potion",
            () -> new Potion(new MobEffectInstance(EffectsInit.CHAOS.get(), 30 * 20, 0)));

    public static final RegistryObject<Potion> RUST_POTION = POTIONS.register("rust_potion",
            () -> new Potion(new MobEffectInstance(EffectsInit.RUST.get(), 60 * 60 * 60 * 20, 0)));

    public static final RegistryObject<Potion> BLINDNESS_POTION = POTIONS.register("blindness_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.BLINDNESS, 2 * 60 * 20, 0)));
}
