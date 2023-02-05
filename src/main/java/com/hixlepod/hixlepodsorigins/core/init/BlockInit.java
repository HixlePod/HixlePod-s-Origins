package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.items.ModCreativeTab;
import com.hixlepod.hixlepodsorigins.core.blocks.GroundBridgeBlock;
import com.hixlepod.hixlepodsorigins.core.blocks.GroundBridgeEntityBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HixlePodsOrigins.MODID);

    public static final RegistryObject<Block> ENERGON_ORE_BLOCK = BLOCKS.register("energon_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f)));
    public static final RegistryObject<Block> SYNTH_EN_ORE_BLOCK = BLOCKS.register("synth_en_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f)));
    public static final RegistryObject<Block> DARK_ENERGON_ORE_BLOCK = BLOCKS.register("dark_energon_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f)));

    public static final RegistryObject<Block> GROUND_BRIDGE_BLOCK = BLOCKS.register("ground_bridge", () -> new GroundBridgeBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f)));
}
