package com.hixlepod.hixlepodsorigins.core.blocks;

import com.hixlepod.hixlepodsorigins.client.screen.GroundBridgeScreen.GroundBridgeMenu;
import com.hixlepod.hixlepodsorigins.core.init.BlockEntityInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GroundBridgeEntityBlock extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyOptionalHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int fuel = 0;
    private int maxFuel = 50000;

    public GroundBridgeEntityBlock(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityInit.GROUND_BRIDGE.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GroundBridgeEntityBlock.this.fuel;
                    case 1 -> GroundBridgeEntityBlock.this.maxFuel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GroundBridgeEntityBlock.this.fuel = value;
                    case 1 -> GroundBridgeEntityBlock.this.maxFuel = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Ground Bridge");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new GroundBridgeMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyOptionalHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyOptionalHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyOptionalHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {

        nbt.put("inventory", itemStackHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());

        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, GroundBridgeEntityBlock entity) {

        if (level.isClientSide()) {
            return;
        }

        if (entity.fuel >= entity.maxFuel) {

        } else {

            if (Energon(entity)) {
                entity.itemStackHandler.getStackInSlot(0).shrink(1);
                entity.fuel += 10000;
                setChanged(level, blockPos, blockState);
            }

        }
    }

    private void reset() {
        this.fuel = 0;
    }

    private static boolean Energon(GroundBridgeEntityBlock entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for (int i = 0; i < entity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        boolean hasEnergonInFirstSlot = entity.itemStackHandler.getStackInSlot(0).getItem() == ItemInit.ENERGON_CUBE.get();

        return hasEnergonInFirstSlot;
    }
}
