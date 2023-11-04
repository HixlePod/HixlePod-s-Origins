package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.core.init.SoundInit;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TakeTheL extends Item {


    public TakeTheL(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        //player.level().playSound(null, player.position().x(), player.position().y(), player.position().z(), SoundInit.CYBER_BULLYING.get(), SoundSource.PLAYERS, 1, 1);
        player.playSound(SoundInit.CYBER_BULLYING.get(), 1, 1);
        return super.use(level, player, interactionHand);
    }
}
