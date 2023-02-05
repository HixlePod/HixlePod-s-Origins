package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.items.Example_Item;
import com.hixlepod.hixlepodsorigins.common.items.ModCreativeTab;
import com.hixlepod.hixlepodsorigins.common.items.Ninja;
import com.hixlepod.hixlepodsorigins.common.items.HolyLyre.The_Holy_Lyre_Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HixlePodsOrigins.MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Example_Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));
    public static final RegistryObject<Item> THE_HOLY_LYRE = ITEMS.register("the_holy_lyre", () -> new The_Holy_Lyre_Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).stacksTo(1)));


    public static final RegistryObject<BlockItem> ENERGON_ORE_BLOCK_ITEM = ITEMS.register("energon_ore_block", () -> new BlockItem(BlockInit.ENERGON_ORE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));
    public static final RegistryObject<BlockItem> SYNTH_EN_ORE_ITEM = ITEMS.register("synth_en_ore_block", () -> new BlockItem(BlockInit.SYNTH_EN_ORE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));
    public static final RegistryObject<BlockItem> DARK_ENERGON_ORE_ITEM = ITEMS.register("dark_energon_ore_block", () -> new BlockItem(BlockInit.DARK_ENERGON_ORE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));

    public static final RegistryObject<BlockItem> GROUND_BRIDGE_BLOCK_ITEM = ITEMS.register("ground_bridge_block", () -> new BlockItem(BlockInit.GROUND_BRIDGE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));

    public static final RegistryObject<Item> UNREFINED_ENERGON = ITEMS.register("unrefined_energon", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));
    public static final RegistryObject<Item> UNREFINED_SYNTH_EN = ITEMS.register("unrefined_synth_en", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));
    public static final RegistryObject<Item> UNREFINED_DARK_ENERGON = ITEMS.register("unrefined_dark_energon", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));

    public static final RegistryObject<Item> REFINED_ENERGON = ITEMS.register("refined_energon", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> REFINED_SYNTH_EN = ITEMS.register("refined_synth_en", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> REFINED_DARK_ENERGON = ITEMS.register("refined_dark_energon", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));

    public static final RegistryObject<Item> ENERGON_CUBE = ITEMS.register("energon_cube", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> SYNTH_EN_CUBE = ITEMS.register("synth_en_cube", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> DARK_ENERGON_CUBE = ITEMS.register("dark_energon_cube", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));

    public static final RegistryObject<Item> NINJA = ITEMS.register("ninja", () -> new Ninja(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));

    public static final RegistryObject<Item> DRAGON_SCALE = ITEMS.register("dragon_scale", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB)));

    public static final RegistryObject<Item> MAXWELL = ITEMS.register("maxwell", () -> new Item(new Item.Properties().tab(ModCreativeTab.HIXLEPOD_ORIGINS_TAB).food(new FoodProperties.Builder().alwaysEat().build())));


    //Vanilla items
    public static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> CUSTOM_COAL = VANILLA_ITEMS.register("coal", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_CHARCOAL = VANILLA_ITEMS.register("charcoal", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_DIAMOND = VANILLA_ITEMS.register("diamond", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_EMERALD = VANILLA_ITEMS.register("emerald", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_LAPIS_LAZULI = VANILLA_ITEMS.register("lapis_lazuli", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_RAW_COPPER = VANILLA_ITEMS.register("raw_copper", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_COPPER_INGOT = VANILLA_ITEMS.register("copper_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_RAW_GOLD = VANILLA_ITEMS.register("raw_gold", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_GOLD_INGOT = VANILLA_ITEMS.register("gold_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_NETHERITE_INGOT = VANILLA_ITEMS.register("netherite_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_NETHERITE_SCRAP = VANILLA_ITEMS.register("netherite_scrap", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_STICK = VANILLA_ITEMS.register("stick", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_RAW_IRON = VANILLA_ITEMS.register("raw_iron", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_IRON_INGOT = VANILLA_ITEMS.register("iron_ingot", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_PAPER = VANILLA_ITEMS.register("paper", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_BOOK = VANILLA_ITEMS.register("book", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_GUNPOWDER = VANILLA_ITEMS.register("gunpowder", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_SLIMEBALL = VANILLA_ITEMS.register("slime_ball", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_TOTEM_OF_UNDYING = VANILLA_ITEMS.register("totem_of_undying", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_BROWN_MUSHROOM = VANILLA_ITEMS.register("brown_mushroom", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_RED_MUSHROOM = VANILLA_ITEMS.register("red_mushroom", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_BONE = VANILLA_ITEMS.register("bone", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().nutrition(1).fast().build())));

    public static final RegistryObject<BlockItem> CUSTOM_DIRT = VANILLA_ITEMS.register("dirt", () -> new BlockItem(Blocks.DIRT, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS).food(new FoodProperties.Builder().build())));

}
