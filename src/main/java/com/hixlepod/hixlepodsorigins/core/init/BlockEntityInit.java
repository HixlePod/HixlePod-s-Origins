package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.blocks.GroundBridgeEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HixlePodsOrigins.MODID);


    public static final RegistryObject<BlockEntityType<GroundBridgeEntityBlock>> GROUND_BRIDGE = BLOCK_ENTITIES.register("ground_bridge", () ->
            BlockEntityType.Builder.of(GroundBridgeEntityBlock::new, BlockInit.GROUND_BRIDGE_BLOCK.get()).build(null));
}

