package com.hixlepod.hixlepodsorigins.common.blocks;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;

public class EnergonOreBlock extends Block {

    public EnergonOreBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        return super.getDrops(p_60537_, p_60538_);
    }
}
