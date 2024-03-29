package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagsInit {



    public static class Blocks {
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS = tag("portal_frame_blocks");
        public static TagKey<Block> TRICO_PHASABLE = tag("trico_phasable");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(HixlePodsOrigins.MODID, name));
        }

    }

}
