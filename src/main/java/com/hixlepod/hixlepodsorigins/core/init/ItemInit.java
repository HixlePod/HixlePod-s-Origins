package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.items.*;
import com.hixlepod.hixlepodsorigins.common.items.HolyLyre.The_Holy_Lyre_Item;
import com.hixlepod.hixlepodsorigins.common.items.OriginWeapons.BetsyBattleAxeItem;
import com.hixlepod.hixlepodsorigins.common.items.OriginWeapons.DragonBoneDagger;
import com.hixlepod.hixlepodsorigins.common.items.OriginWeapons.TheStringlessBow;
import com.hixlepod.hixlepodsorigins.common.items.OriginWeapons.WhispersHammerItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {



    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HixlePodsOrigins.MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Example_Item(new Item.Properties()));
    public static final RegistryObject<Item> THE_HOLY_LYRE = ITEMS.register("the_holy_lyre", () -> new The_Holy_Lyre_Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<BlockItem> GROUND_BRIDGE_BLOCK_ITEM = ITEMS.register("ground_bridge_block", () -> new BlockItem(BlockInit.GROUND_BRIDGE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ENERGOM_LIGHTER = ITEMS.register("energon_lighter", () -> new EnergonLighterItem(new Item.Properties()));

    public static final RegistryObject<BlockItem> ENERGON_DECORATION_BLOCK_ITEM = ITEMS.register("energon_decoration_block", () -> new BlockItem(BlockInit.ENERGON_DECORATION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SYNTH_EN_DECORATION_BLOCK_ITEM = ITEMS.register("synth_en_decoration_block", () -> new BlockItem(BlockInit.SYNTH_EN_DECORATION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DARK_ENERGON_DECORATION_BLOCK_ITEM = ITEMS.register("dark_energon_decoration_block", () -> new BlockItem(BlockInit.DARK_ENERGON_DECORATION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> RED_ENERGON_DECORATION_BLOCK_ITEM = ITEMS.register("red_energon_decoration_block", () -> new BlockItem(BlockInit.RED_ENERGON_DECORATION_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> ENERGON_ORE_BLOCK_ITEM = ITEMS.register("energon_ore_block", () -> new BlockItem(BlockInit.ENERGON_ORE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SYNTH_EN_ORE_ITEM = ITEMS.register("synth_en_ore_block", () -> new BlockItem(BlockInit.SYNTH_EN_ORE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DARK_ENERGON_ORE_ITEM = ITEMS.register("dark_energon_ore_block", () -> new BlockItem(BlockInit.DARK_ENERGON_ORE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> RED_ENERGON_ORE_ITEM = ITEMS.register("red_energon_ore_block", () -> new BlockItem(BlockInit.RED_ENERGON_ORE_BLOCK.get(), new Item.Properties()));


    public static final RegistryObject<Item> UNREFINED_ENERGON = ITEMS.register("unrefined_energon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNREFINED_SYNTH_EN = ITEMS.register("unrefined_synth_en", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNREFINED_DARK_ENERGON = ITEMS.register("unrefined_dark_energon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNREFINED_RED_ENERGON = ITEMS.register("unrefined_red_energon", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> REFINED_ENERGON = ITEMS.register("refined_energon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> REFINED_SYNTH_EN = ITEMS.register("refined_synth_en", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> REFINED_DARK_ENERGON = ITEMS.register("refined_dark_energon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> REFINED_RED_ENERGON = ITEMS.register("refined_red_energon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,15 * 20, 2), 1)
            .build())));

    public static final RegistryObject<Item> ENERGON_CUBE = ITEMS.register("energon_cube", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.REGENERATION,5 * 20, 2), 1)
            .build())));
    public static final RegistryObject<Item> SYNTH_EN_CUBE = ITEMS.register("synth_en_cube", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.REGENERATION,20 * 20, 3), 1)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION,  120 * 20, 2), 1)
            .build())));
    public static final RegistryObject<Item> DARK_ENERGON_CUBE = ITEMS.register("dark_energon_cube", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.REGENERATION,     120 * 20, 3), 1)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION,       30 * 20,  4), 1)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,  300 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,300 * 20, 0), 1)
            .build())));
    public static final RegistryObject<Item> RED_ENERGON_CUBE = ITEMS.register("red_energon_cube", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,300 * 20, 2), 1)
            .build())));

    public static final RegistryObject<Item> ENERGON_BITS = ITEMS.register("energon_bits", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> SYNTH_EN_BITS = ITEMS.register("synth_en_bits", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> DARK_ENERGON_BITS = ITEMS.register("dark_energon_bits", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> RED_ENERGON_BITS = ITEMS.register("red_energon_bits", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));

    public static final RegistryObject<BlockItem> STEELWOOD = ITEMS.register("steelwood", () -> new BlockItem(BlockInit.STEELWOOD.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> STEEL_LEAVES = ITEMS.register("steel_leaves", () -> new BlockItem(BlockInit.STEEL_LEAVES.get(), new Item.Properties()));

    public static final RegistryObject<Item> ENERGON_PIZZA = ITEMS.register("energon_pizza", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> AZZIP_NOGRENE = ITEMS.register("azzip_nogrene", () -> new Item(new Item.Properties().rarity(Rarity.RARE).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> ENERJOLLY = ITEMS.register("enerjolly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,180 * 20, 7), 1)
            .build())));

    public static final RegistryObject<Item> HYPER_ENERJOLLY = ITEMS.register("hyper_enerjolly", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,30 * 20, 11), 1)
            .build())));

    public static final RegistryObject<Item> BATTLE_DONUT = ITEMS.register("battle_donut", () -> new Item(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(10)
            .effect(new MobEffectInstance(MobEffects.GLOWING,         20 * 20, 1), 1)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,  20 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.REGENERATION,    20 * 20, 3), 1)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION,    20 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION,      20 * 20, 3), 1)
            .build())));

    public static final RegistryObject<Item> ENERGON_LOW_GRADE_DRINK = ITEMS.register("energon_low_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat().build())));


    public static final RegistryObject<Item> ENERGON_MID_GRADE_DRINK = ITEMS.register("energon_mid_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> SYNTH_EN_MID_GRADE_DRINK = ITEMS.register("synth_en_mid_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> DARK_MID_GRADE_DRINK = ITEMS.register("dark_mid_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> RED_MID_GRADE_DRINK = ITEMS.register("red_mid_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,20 * 20, 3), 1)
            .build())));

    public static final RegistryObject<Item> ENERGON_HIGH_GRADE_DRINK = ITEMS.register("energon_high_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat().build())));
    public static final RegistryObject<Item> DARK_HIGH_GRADE_DRINK = ITEMS.register("dark_high_grade_drink", () -> new DrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().alwaysEat().build())));


    public static final RegistryObject<Item> SUGAR_CUBE = ITEMS.register("sugar_cube", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBERGON_BUCKET = ITEMS.register("ambergon_bucket", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> AMBERGON_CREAM = ITEMS.register("ambergon_cream", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUST = ITEMS.register("rust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_BONE = ITEMS.register("blood_bone", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(6).saturationMod(4).effect(new MobEffectInstance(MobEffects.HUNGER, 10 * 20, 2), 1).build())));
    public static final RegistryObject<Item> BATTLE_BURRITO = ITEMS.register("battle_burrito", () -> new Item(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().alwaysEat().nutrition(10).saturationMod(10)
            .effect(new MobEffectInstance(MobEffects.GLOWING,         60 * 20, 1), 1)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,  60 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.REGENERATION,    60 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION,    60 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION,      60 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,    60 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED,       60 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST,    60 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.JUMP,            60 * 20, 0), 1)
            .effect(new MobEffectInstance(MobEffects.LUCK,            60 * 20, 2), 1)
            .effect(new MobEffectInstance(MobEffects.SATURATION,      60 * 20, 2), 1)
            .build())));


    //Weapons
    public static final RegistryObject<Item> THE_STRINGLESS = ITEMS.register("the_stringless", () -> new TheStringlessBow(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BETSY_BATTLE_AXE = ITEMS.register("betsy_battle_axe", () -> new BetsyBattleAxeItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WHISPERS_HAMMER = ITEMS.register("whispers_hammer", () -> new WhispersHammerItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DRAGON_BONE_DAGGER = ITEMS.register("dragon_bone_dagger", () -> new DragonBoneDagger(new Item.Properties().stacksTo(2)));


    //Meme items
    public static final RegistryObject<Item> NINJA = ITEMS.register("ninja", () -> new Ninja(new Item.Properties()));
    public static final RegistryObject<Item> TAKE_THE_L = ITEMS.register("take_the_l", () -> new TakeTheL(new Item.Properties()));

    public static final RegistryObject<Item> WEED = ITEMS.register("weed", () -> new Weed(new Item.Properties().food(new FoodProperties.Builder()
            .effect(new MobEffectInstance(MobEffects.LEVITATION,            10 * 20, 4), 1)
            .effect(new MobEffectInstance(MobEffects.GLOWING,               10 * 20, 4), 1)
            .effect(new MobEffectInstance(MobEffects.CONFUSION,             10 * 20, 4), 1)
            .effect(new MobEffectInstance(MobEffects.JUMP,                  10 * 20, 9), 1)
            .effect(new MobEffectInstance(MobEffects.HEAL,                  10 * 20, 4), 1)
            .effect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE,   10 * 20, 4), 1)
            .effect(new MobEffectInstance(MobEffects.UNLUCK,                10 * 20, 4), 1)
            .build())));

    public static final RegistryObject<Item> DRAGON_SCALE = ITEMS.register("dragon_scale", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAXWELL = ITEMS.register("maxwell", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> AMONG_US = ITEMS.register("among_us", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> ORE_TRACKER = ITEMS.register("ore_tracker", () -> new OreTrackerItem(new Item.Properties()));

    public static final RegistryObject<Item> BAG_OF_CAT_FOOD = ITEMS.register("bag_of_cat_food", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAN = ITEMS.register("pan", () -> new PanItem(new Item.Properties()));

    public static final RegistryObject<Item> PASSPORT = ITEMS.register("passport", () -> new PassPortItem(new Item.Properties()));

    public static final RegistryObject<Item> MYSTERY_BUNDLE = ITEMS.register("mystery_bundle", () -> new MysteryBundleItem(new Item.Properties().stacksTo(16)));

    //YARN ITEMS
    public static final RegistryObject<Item> BRONZE_YARN = ITEMS.register("bronze_yarn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_YARN = ITEMS.register("silver_yarn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOLD_YARN = ITEMS.register("gold_yarn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATINUM_YARN = ITEMS.register("platinum_yarn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_YARN = ITEMS.register("diamond_yarn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASAGNA = ITEMS.register("lasagna", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUEST_TICKET = ITEMS.register("quest_ticket", () -> new QuestTicketItem(new Item.Properties().stacksTo(1)));


    //Trophy items
    public static final RegistryObject<BlockItem> BRONZE_TROPHY = ITEMS.register("bronze_trophy", () -> new BlockItem(BlockInit.BRONZE_TROPHY_BLOCK.get(), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<BlockItem> SILVER_TROPHY = ITEMS.register("silver_trophy", () -> new BlockItem(BlockInit.SILVER_TROPHY_BLOCK.get(), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<BlockItem> GOLD_TROPHY = ITEMS.register("gold_trophy", () -> new BlockItem(BlockInit.GOLD_TROPHY_BLOCK.get(), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));


    public static final RegistryObject<Item> MAGIC_DUST = ITEMS.register("magic_dust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<BlockItem> VOIDSTONE_ORE = ITEMS.register("voidstone_ore", () -> new BlockItem(BlockInit.VOIDSTONE_ORE.get(), new Item.Properties()));


    //Vanilla items
    public static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> CUSTOM_COAL = VANILLA_ITEMS.register("coal", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_CHARCOAL = VANILLA_ITEMS.register("charcoal", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_DIAMOND = VANILLA_ITEMS.register("diamond", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_EMERALD = VANILLA_ITEMS.register("emerald", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_LAPIS_LAZULI = VANILLA_ITEMS.register("lapis_lazuli", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_RAW_COPPER = VANILLA_ITEMS.register("raw_copper", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_COPPER_INGOT = VANILLA_ITEMS.register("copper_ingot", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_RAW_GOLD = VANILLA_ITEMS.register("raw_gold", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_GOLD_INGOT = VANILLA_ITEMS.register("gold_ingot", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_NETHERITE_INGOT = VANILLA_ITEMS.register("netherite_ingot", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_NETHERITE_SCRAP = VANILLA_ITEMS.register("netherite_scrap", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_STICK = VANILLA_ITEMS.register("stick", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_RAW_IRON = VANILLA_ITEMS.register("raw_iron", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_IRON_INGOT = VANILLA_ITEMS.register("iron_ingot", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_PAPER = VANILLA_ITEMS.register("paper", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_BOOK = VANILLA_ITEMS.register("book", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_GUNPOWDER = VANILLA_ITEMS.register("gunpowder", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_SLIMEBALL = VANILLA_ITEMS.register("slime_ball", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_TOTEM_OF_UNDYING = VANILLA_ITEMS.register("totem_of_undying", () -> new Item(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_BROWN_MUSHROOM = VANILLA_ITEMS.register("brown_mushroom", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));
    public static final RegistryObject<Item> CUSTOM_RED_MUSHROOM = VANILLA_ITEMS.register("red_mushroom", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));

    public static final RegistryObject<Item> CUSTOM_BONE = VANILLA_ITEMS.register("bone", () -> new Item(new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(1).fast().build())));

    public static final RegistryObject<BlockItem> CUSTOM_DIRT = VANILLA_ITEMS.register("dirt", () -> new BlockItem(Blocks.DIRT, new Item.Properties().stacksTo(64).food(new FoodProperties.Builder().build())));

}
