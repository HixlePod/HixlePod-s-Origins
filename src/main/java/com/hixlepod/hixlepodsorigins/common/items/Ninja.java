package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.core.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Ninja extends Item {


    public Ninja(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        //player.level().playSound(null, player.position().x(), player.position().y(), player.position().z(), SoundInit.NINJA.get(), SoundSource.PLAYERS, 1, 1);
        player.playSound(SoundInit.NINJA.get(), 1, 1);
        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty("If you're gonna play like that than"));
        toolTip.add(Component.nullToEmpty("you better have shockwaves to finish"));
        toolTip.add(Component.nullToEmpty("the job..."));

        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }
}
