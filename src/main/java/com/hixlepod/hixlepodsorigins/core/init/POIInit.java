package com.hixlepod.hixlepodsorigins.core.init;

import com.google.common.collect.ImmutableSet;
import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.world.entity.ai.village.poi.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class POIInit {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, HixlePodsOrigins.MODID);

    public static final RegistryObject<PoiType> CYBERTRON_PORTAL =
            POI.register("cybertron_portal", () -> new PoiType(
                    getBlockStates(BlockInit.CYBERTRON_PORTAL.get()), 0, 1));

    private static Set<BlockState> getBlockStates(Block p_218074_) {
        return ImmutableSet.copyOf(p_218074_.getStateDefinition().getPossibleStates());
    }

}
