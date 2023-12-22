package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsInit {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HixlePodsOrigins.MODID);

    public static final RegistryObject<MobEffect> AMBERGON_EFFECT = MOB_EFFECTS.register("ambergon",
            () -> new ChaosEffect(MobEffectCategory.NEUTRAL, 16440987));

    public static final RegistryObject<MobEffect> MALFUNCTION = MOB_EFFECTS.register("malfunction",
            () -> new MalfunctionEffect(MobEffectCategory.HARMFUL, 9830655));

    public static final RegistryObject<MobEffect> LIGHTNING = MOB_EFFECTS.register("lightning",
            () -> new LightningEffect(MobEffectCategory.NEUTRAL, 28415));

    public static final RegistryObject<MobEffect> FREEZE = MOB_EFFECTS.register("freeze",
            () -> new FreezeEffect(MobEffectCategory.HARMFUL, 65535));

    public static final RegistryObject<MobEffect> CHAOS = MOB_EFFECTS.register("chaos",
            () -> new ChaosEffect(MobEffectCategory.HARMFUL, 0));

    public static final RegistryObject<MobEffect> RUST = MOB_EFFECTS.register("rust",
            () -> new RustEffect(MobEffectCategory.HARMFUL, 12814090));


    public static final RegistryObject<MobEffect> GIANT = MOB_EFFECTS.register("giant",
            () -> new GiantEffect(MobEffectCategory.NEUTRAL, 65280));

    public static final RegistryObject<MobEffect> DWARF = MOB_EFFECTS.register("dwarf",
            () -> new DwarfEffect(MobEffectCategory.NEUTRAL, 16711680));

    public static final RegistryObject<MobEffect> ABILITY_REGENERATION = MOB_EFFECTS.register("ability_regeneration",
            () -> new DwarfEffect(MobEffectCategory.NEUTRAL, 10079232));
}
