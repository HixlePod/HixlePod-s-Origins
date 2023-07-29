package com.hixlepod.hixlepodsorigins.common.blocks;

import com.hixlepod.hixlepodsorigins.common.origins.TricoFan;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class StoneBlock extends Block {

    private static final VoxelShape STABLE_SHAPE;
    private static final VoxelShape UNSTABLE_SHAPE;
    private static final VoxelShape UNSTABLE_SHAPE_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape BELOW_BLOCK = Shapes.block().move(0.0D, -1.0D, 0.0D);

    public StoneBlock(BlockBehaviour.Properties properties) {
        super(properties);

        if (Minecraft.getInstance().player != null) {
            if (Minecraft.getInstance().player.equals(Component.literal("Dev"))) {
                properties.noCollission().dynamicShape();
            }
        }
    }


    @Override
    public @NotNull VoxelShape getInteractionShape(BlockState p_56053_, BlockGetter blockGetter, BlockPos p_56055_) {

        return Shapes.block();
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState p_56068_, BlockGetter p_56069_, BlockPos blockPos, CollisionContext context) {

        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.getName().equals(Component.literal("Dev"))) {
            if (context.isAbove(Shapes.block(), blockPos, true) && !context.isDescending()) {
                return STABLE_SHAPE;
            } else {
                return Shapes.empty();
            }
        } else {
            return UNSTABLE_SHAPE;
        }
    }
    //p_56071_.isAbove(BELOW_BLOCK, p_56070_, true) ? UNSTABLE_SHAPE_BOTTOM :

    static {
        VoxelShape voxelshape = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape voxelshape1 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
        VoxelShape voxelshape2 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        VoxelShape voxelshape3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
        VoxelShape voxelshape4 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        STABLE_SHAPE = Shapes.or(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4);
        VoxelShape voxelshape5 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
        VoxelShape voxelshape6 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape7 = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape8 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
        UNSTABLE_SHAPE = Shapes.or(UNSTABLE_SHAPE_BOTTOM, STABLE_SHAPE, voxelshape6, voxelshape5, voxelshape8, voxelshape7);
    }
}
