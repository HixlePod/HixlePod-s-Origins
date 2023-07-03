package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeatureInit {

    //OVERWORLD
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, HixlePodsOrigins.MODID);

    public static final RegistryObject<PlacedFeature> ENERGON_ORE_OVERWORLD = PLACED_FEATURES.register("energon_ore_overworld",
            () -> new PlacedFeature(ConfiguredFeatureInit.ENERGON_ORE_OVERWORLD.getHolder().get(),
                    commmonOrePlacement(7, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(40)
                    ))));

    public static final RegistryObject<PlacedFeature> SYNTH_EM_ORE_OVERWORLD = PLACED_FEATURES.register("synth_en_ore_overworld",
            () -> new PlacedFeature(ConfiguredFeatureInit.SYNTH_EN_ORE_OVERWORLD.getHolder().get(),
                    commmonOrePlacement(3, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(30)
                    ))));

    public static final RegistryObject<PlacedFeature> DARK_ENERGON_ORE_OVERWORLD = PLACED_FEATURES.register("dark_energon_ore_overworld",
            () -> new PlacedFeature(ConfiguredFeatureInit.DARK_ENERGON_ORE_OVERWORLD.getHolder().get(),
                    commmonOrePlacement(2, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(15)
                    ))));


    //CYBERTRON
    public static final RegistryObject<PlacedFeature> ENERGON_ORE_CYBERTRON = PLACED_FEATURES.register("energon_ore_cybertron",
            () -> new PlacedFeature(ConfiguredFeatureInit.ENERGON_ORE_CYBERTRON.getHolder().get(),
                    commmonOrePlacement(7, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(40)
                    ))));

    public static final RegistryObject<PlacedFeature> SYNTH_EM_ORE_CYBERTRON = PLACED_FEATURES.register("synth_en_ore_cybertron",
            () -> new PlacedFeature(ConfiguredFeatureInit.SYNTH_EN_ORE_CYBERTRON.getHolder().get(),
                    commmonOrePlacement(3, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(30)
                    ))));

    public static final RegistryObject<PlacedFeature> DARK_ENERGON_ORE_CYBERTRON = PLACED_FEATURES.register("dark_energon_ore_cybertron",
            () -> new PlacedFeature(ConfiguredFeatureInit.DARK_ENERGON_ORE_CYBERTRON.getHolder().get(),
                    commmonOrePlacement(2, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(15)
                    ))));

    public static final RegistryObject<PlacedFeature> RED_ENERGON_ORE_CYBERTRON = PLACED_FEATURES.register("red_energon_ore_cybertron",
            () -> new PlacedFeature(ConfiguredFeatureInit.RED_ENERGON_ORE_CYBERTRON.getHolder().get(),
                    commmonOrePlacement(4, HeightRangePlacement.triangle(
                            VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(25)
                    ))));

    private static List<PlacementModifier> commmonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }
}
