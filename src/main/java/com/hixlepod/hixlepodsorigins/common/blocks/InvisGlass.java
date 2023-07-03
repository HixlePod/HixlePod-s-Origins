package com.hixlepod.hixlepodsorigins.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class InvisGlass extends Block {

    public static final BooleanProperty IS_GLASS = BooleanProperty.create("is_glass");

    public InvisGlass(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public Object getRenderPropertiesInternal() {
        return super.getRenderPropertiesInternal();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_GLASS);
    }
}
