package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;


public class DimensionInit {

    public static final ResourceKey<Level> MINIGAMES_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(HixlePodsOrigins.MODID, "minigames"));

    public static final ResourceKey<DimensionType> MINIGAMES_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, MINIGAMES_KEY.registry());

}
