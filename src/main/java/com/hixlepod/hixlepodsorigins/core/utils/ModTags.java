package com.hixlepod.hixlepodsorigins.core.utils;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(HixlePodsOrigins.MODID, name));
        }
    }

}
