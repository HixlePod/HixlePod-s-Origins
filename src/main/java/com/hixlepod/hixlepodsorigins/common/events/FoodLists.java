package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class FoodLists {


    public static MutableComponent[] ROBOTS = {
            Component.literal(HixlePod.NAME),
            Component.literal(AmbrosiaElf.NAME),
            Component.literal(Blakpaw2244.NAME),
            Component.literal(Kira_uwu69.NAME),
            Component.literal(Folf_Gaming.NAME)
    };



    public static Item[] DRAGONS_BANNED_FOOD = {Items.APPLE, Items.MUSHROOM_STEW, Items.COOKIE, Items.MELON_SLICE, Items.DRIED_KELP, Items.CARROT,
            Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.BEETROOT, Items.BEETROOT_SOUP,
            Items.SWEET_BERRIES, Items.GLOW_BERRIES, Items.HONEY_BOTTLE};

    public static Item[] KYOWING_BANNED_FOODS = {Items.MUSHROOM_STEW, Items.PORKCHOP, Items.COOKED_PORKCHOP,
            Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE,
            Items.MELON_SLICE, Items.DRIED_KELP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
            Items.SPIDER_EYE, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.RABBIT,
            Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP, Items.SWEET_BERRIES,
            Items.GLOW_BERRIES, Items.HONEY_BOTTLE, Items.BOW, Items.CROSSBOW, Items.WOODEN_SWORD, Items.IRON_SWORD,
            Items.STONE_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD};

    public static Item[] GHOSTLURE_BANNED_FOODS = {Items.MUSHROOM_STEW, Items.BREAD, Items.COOKED_PORKCHOP, Items.COOKED_COD,
            Items.COOKED_SALMON, Items.COOKIE, Items.DRIED_KELP, Items.COOKED_BEEF, Items.COOKED_CHICKEN,
            Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.PUMPKIN_PIE, Items.COOKED_RABBIT, Items.RABBIT_STEW,
            Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP};

    public static Item[] ANIRIAL_BANNED_FOODS = {Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH,
            Items.COOKED_COD, Items.COOKED_SALMON, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
            Items.SPIDER_EYE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON};

    public static Item[] GOAT_FOODS = {ItemInit.CUSTOM_COAL.get(), ItemInit.CUSTOM_CHARCOAL.get(), ItemInit.CUSTOM_DIAMOND.get(), ItemInit.CUSTOM_EMERALD.get(),
            ItemInit.CUSTOM_LAPIS_LAZULI.get(), ItemInit.CUSTOM_RAW_COPPER.get(), ItemInit.CUSTOM_COPPER_INGOT.get(), ItemInit.CUSTOM_RAW_GOLD.get(),
            ItemInit.CUSTOM_GOLD_INGOT.get(), ItemInit.CUSTOM_NETHERITE_INGOT.get(), ItemInit.CUSTOM_NETHERITE_SCRAP.get(), ItemInit.CUSTOM_STICK.get(),
            ItemInit.CUSTOM_RAW_IRON.get(), ItemInit.CUSTOM_IRON_INGOT.get(), ItemInit.CUSTOM_PAPER.get(), ItemInit.CUSTOM_BOOK.get(), ItemInit.CUSTOM_GUNPOWDER.get(),
            ItemInit.CUSTOM_SLIMEBALL.get(), ItemInit.CUSTOM_TOTEM_OF_UNDYING.get(), ItemInit.CUSTOM_BROWN_MUSHROOM.get(), ItemInit.CUSTOM_RED_MUSHROOM.get(),
            ItemInit.CUSTOM_DIRT.get(), ItemInit.CUSTOM_BONE.get()};

    public static Item[] TRANSFORMER_BANNED_FOODS = {Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP,
            Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE,
            Items.MELON_SLICE, Items.DRIED_KELP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
            Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.RABBIT,
            Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP, Items.SWEET_BERRIES,
            Items.GLOW_BERRIES, Items.HONEY_BOTTLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, ItemInit.BATTLE_BURRITO.get()};

    public static Item[] TRANSFORMER_FOODS =
            {ItemInit.REFINED_ENERGON.get(), ItemInit.REFINED_SYNTH_EN.get(), ItemInit.REFINED_DARK_ENERGON.get(), ItemInit.REFINED_RED_ENERGON.get(),
                    ItemInit.ENERGON_CUBE.get(), ItemInit.SYNTH_EN_CUBE.get(), ItemInit.DARK_ENERGON_CUBE.get(), ItemInit.RED_ENERGON_CUBE.get(),
                    ItemInit.ENERGON_BITS.get(), ItemInit.SYNTH_EN_BITS.get(), ItemInit.DARK_ENERGON_BITS.get(), ItemInit.RED_ENERGON_BITS.get(),
                    ItemInit.ENERJOLLY.get(), ItemInit.HYPER_ENERJOLLY.get(), ItemInit.BATTLE_DONUT.get(), ItemInit.AZZIP_NOGRENE.get(),
            };

    public static Item[] TRANSFORMERS_DRINKS = {
        ItemInit.ENERGON_LOW_GRADE_DRINK.get(),
            ItemInit.ENERGON_MID_GRADE_DRINK.get(), ItemInit.SYNTH_EN_MID_GRADE_DRINK.get(), ItemInit.DARK_MID_GRADE_DRINK.get(), ItemInit.RED_MID_GRADE_DRINK.get(),
            ItemInit.ENERGON_HIGH_GRADE_DRINK.get(), ItemInit.DARK_HIGH_GRADE_DRINK.get(),
    };

}
