package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.blocks.CybertronPortalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HixlePodsOrigins.MODID);

    public static final RegistryObject<Block> ENERGON_ORE_BLOCK = BLOCKS.register("energon_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f).lightLevel((amount) -> {
                return 8;
            })));
    public static final RegistryObject<Block> SYNTH_EN_ORE_BLOCK = BLOCKS.register("synth_en_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f).lightLevel((amount) -> {
                return 8;
            })));
    public static final RegistryObject<Block> DARK_ENERGON_ORE_BLOCK = BLOCKS.register("dark_energon_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f).lightLevel((amount) -> {
                return 8;
            })));

    public static final RegistryObject<Block> RED_ENERGON_ORE_BLOCK = BLOCKS.register("red_energon_ore_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(2.5f, 18.0f).lightLevel((amount) -> {
                return 8;
            })));

    public static final RegistryObject<Block> GROUND_BRIDGE_BLOCK = BLOCKS.register("ground_bridge", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_BLUE)
            .strength(1.0f, 3600000.0f).lightLevel((amount) -> {
                return 15;
            })));

    public static final RegistryObject<Block> ENERGON_DECORATION_BLOCK = BLOCKS.register("energon_decoration_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_BLUE)));

    public static final RegistryObject<Block> SYNTH_EN_DECORATION_BLOCK = BLOCKS.register("synth_en_decoration_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_BLUE)));

    public static final RegistryObject<Block> DARK_ENERGON_DECORATION_BLOCK = BLOCKS.register("dark_energon_decoration_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_BLUE)));

    public static final RegistryObject<Block> RED_ENERGON_DECORATION_BLOCK = BLOCKS.register("red_energon_decoration_block", () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.COLOR_BLUE)));

    public static final RegistryObject<Block> CYBERTRON_PORTAL = registerBlockWithoutBlockItem("cybertron_portal", CybertronPortalBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

}
