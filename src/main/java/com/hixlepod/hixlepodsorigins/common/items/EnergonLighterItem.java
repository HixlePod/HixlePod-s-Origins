package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.common.blocks.CybertronPortalBlock;
import com.hixlepod.hixlepodsorigins.core.init.BlockInit;
import com.hixlepod.hixlepodsorigins.core.init.DimensionInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnergonLighterItem extends Item {

    public EnergonLighterItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty("Used to light the Cybertron portal."));
        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (context.getPlayer() != null) {
            if (context.getPlayer().level().dimension() == DimensionInit.CYBERTRON_KEY || context.getPlayer().level().dimension() == Level.OVERWORLD) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);

                    if (((CybertronPortalBlock) BlockInit.CYBERTRON_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        context.getLevel().playSound(context.getPlayer(), framePos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                    else return InteractionResult.FAIL;
                }
            }
        }

        return InteractionResult.FAIL;
    }
}
