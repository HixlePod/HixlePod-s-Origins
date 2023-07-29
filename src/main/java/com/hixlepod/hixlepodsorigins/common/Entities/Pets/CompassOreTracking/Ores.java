package com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Ores {
    public static final TagKey<Block> DIAMOND = makeWrapperTag("ores/diamond");
    public static final TagKey<Block> LAPIS = makeWrapperTag("ores/lapis");
    public static final TagKey<Block> COPPER = makeWrapperTag("ores/copper");

    public static final TagKey<Block> GOLD = makeWrapperTag("ores/gold");
    public static final TagKey<Block> IRON = makeWrapperTag("ores/iron");
    public static final TagKey<Block> EMERALD = makeWrapperTag("ores/emerald");
    public static final TagKey<Block> COAL = makeWrapperTag("ores/coal");
    public static final TagKey<Block> REDSTONE = makeWrapperTag("ores/redstone");
    public static final TagKey<Block> NETHERITE = makeWrapperTag("ores/netherite_scrap");
    public static final TagKey<Block> QUARTZ = makeWrapperTag("ores/quartz");

    private static TagKey<Block> makeWrapperTag(String tagname) {
        return BlockTags.create(new ResourceLocation("forge", tagname));

    }
    private static TagKey<Block> vanillaWrapper(String tag){
        return BlockTags.create(new ResourceLocation("minecraft", tag));
    }
}
