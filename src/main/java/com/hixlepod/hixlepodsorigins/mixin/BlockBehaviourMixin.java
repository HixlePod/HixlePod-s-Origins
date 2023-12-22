package com.hixlepod.hixlepodsorigins.mixin;

import com.hixlepod.hixlepodsorigins.core.init.TagsInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.NonNullSupplier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockBehaviourMixin {

    @Shadow
    public abstract Block getBlock();

    @Shadow
    protected abstract @NotNull BlockState asState();

    @Inject(at = @At("HEAD"), method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;", cancellable = true)
    private void phaseThroughBlocks(BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> info) {
        VoxelShape blockShape = this.getBlock().getCollisionShape(this.asState(), world, pos, context);
        if (!blockShape.isEmpty() && context instanceof EntityCollisionContext esc) {
            if (esc.getEntity() != null) {
                Entity entity = esc.getEntity();
                boolean phasThroughTop = !entity.isCrouching() && this.isAbove(entity, blockShape, pos, false) ? false : true;
                if (world instanceof LevelReader reader && entity instanceof LivingEntity living && ShouldPhaseThrough(living, reader, pos, this::asState, phasThroughTop))
                    info.setReturnValue(Shapes.empty());
            }
        }
    }


    @Unique
    private boolean isAbove(Entity entity, VoxelShape shape, BlockPos pos, boolean defaultValue) {
        return entity.getY() > (double) pos.getY() + shape.max(Direction.Axis.Y) - (entity.onGround() ? 8.05 / 16.0 : 0.0015);
    }

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void preventCollisionWhenPhasing(Level world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (entity instanceof LivingEntity living && ShouldPhaseThrough(living, pos)) {
            ci.cancel();
        }
    }

    @Unique
    private boolean ShouldPhaseThrough(LivingEntity livingEntity, BlockPos pos) {
        return ShouldPhaseThrough(livingEntity, livingEntity.level(), pos, () -> livingEntity.level().getBlockState(pos), false);
    }

    @Unique
    private boolean ShouldPhaseThrough(Entity entity, LevelReader reader, BlockPos pos, NonNullSupplier<BlockState> stateGetter, boolean phasThroughTop) {
        if (entity != null && entity instanceof Player) {
            Player player = (Player) entity;

            if (player.level() != null) {
                if (phasThroughTop) {
                    if (reader.getBlockState(pos).getBlock() == Blocks.STONE) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
