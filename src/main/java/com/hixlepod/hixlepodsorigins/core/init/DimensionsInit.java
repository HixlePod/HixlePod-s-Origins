package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionsInit {

    public static final ResourceKey<Level> CYBERTRON_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(HixlePodsOrigins.MODID, "cybertron"));

    public static final ResourceKey<DimensionType> CYBERTRON_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, CYBERTRON_KEY.registry());



    public static final ResourceKey<NoiseGeneratorSettings> CYBERTRON_NOISE_SETTIMGS = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY,
            new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_noise_settings"));

    public static final ResourceKey<NormalNoise.NoiseParameters> CYBERTRON_NOISE = ResourceKey.create(Registry.NOISE_REGISTRY,
            new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_noise"));


    public static final ResourceKey<Biome> CYBERTRON_MAIN_BIOME = ResourceKey.create(Registry.BIOME_REGISTRY,
            new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_main_biome"));



}
