/*
package com.hixlepod.hixlepodsorigins.core.init;

import com.google.common.base.Suppliers;
import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;


@Deprecated(since = "1.20 / 0.9.5", forRemoval = true)
public class ConfiguredFeatureInit {

    static RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    static RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, HixlePodsOrigins.MODID);


    //OVERWORLD
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ENERGON_ORE_OVERWORLD_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, BlockInit.ENERGON_ORE_BLOCK.get().defaultBlockState())
            )
        );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> SYNTH_EN_ORE_OVERWORLD_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, BlockInit.SYNTH_EN_ORE_BLOCK.get().defaultBlockState())
            )
    );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> DARK_ENERGON_ORE_OVERWORLD_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, BlockInit.DARK_ENERGON_ORE_BLOCK.get().defaultBlockState())
            )
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> ENERGON_ORE_OVERWORLD = CONFIGURED_FEATURES.register("energon_ore_overworld",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ENERGON_ORE_OVERWORLD_REPLACEMENT.get(), 6)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SYNTH_EN_ORE_OVERWORLD = CONFIGURED_FEATURES.register("synth_en_ore_overworld",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SYNTH_EN_ORE_OVERWORLD_REPLACEMENT.get(), 4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DARK_ENERGON_ORE_OVERWORLD = CONFIGURED_FEATURES.register("dark_energon_ore_overworld",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(DARK_ENERGON_ORE_OVERWORLD_REPLACEMENT.get(), 3)));



    //CYBERTRON
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ENERGON_ORE_CYBERTRON_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(STONE_ORE_REPLACEABLES, BlockInit.ENERGON_ORE_BLOCK.get().defaultBlockState())
            )
    );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> SYNTH_EN_ORE_CYBERTRON_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(STONE_ORE_REPLACEABLES, BlockInit.SYNTH_EN_ORE_BLOCK.get().defaultBlockState())
            )
    );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> DARK_ENERGON_ORE_CYBERTRON_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(STONE_ORE_REPLACEABLES, BlockInit.DARK_ENERGON_ORE_BLOCK.get().defaultBlockState())
            )
    );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> RED_ENERGON_ORE_CYBERTRON_REPLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(STONE_ORE_REPLACEABLES, BlockInit.RED_ENERGON_ORE_BLOCK.get().defaultBlockState())
            )
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> ENERGON_ORE_CYBERTRON = CONFIGURED_FEATURES.register("energon_ore_cybertron",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ENERGON_ORE_CYBERTRON_REPLACEMENT.get(), 6)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SYNTH_EN_ORE_CYBERTRON = CONFIGURED_FEATURES.register("synth_en_ore_cybertron",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SYNTH_EN_ORE_CYBERTRON_REPLACEMENT.get(), 4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DARK_ENERGON_ORE_CYBERTRON = CONFIGURED_FEATURES.register("dark_energon_ore_cybertron",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(DARK_ENERGON_ORE_CYBERTRON_REPLACEMENT.get(), 3)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RED_ENERGON_ORE_CYBERTRON = CONFIGURED_FEATURES.register("red_energon_ore_cybertron",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(RED_ENERGON_ORE_CYBERTRON_REPLACEMENT.get(), 4)));
}


 */