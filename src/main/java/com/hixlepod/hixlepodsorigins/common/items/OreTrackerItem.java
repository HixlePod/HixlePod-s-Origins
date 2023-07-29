package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.BlockData;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.BlockStore;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.Ores;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.xray.Controller;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OreTrackerItem extends Item {

    public OreTrackerItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {

        toolTip.add(Component.nullToEmpty(ChatFormatting.WHITE + "Not an item that can be obtained, used to test ore tracking."));
        super.appendHoverText(stack, worldIn, toolTip, flagIn);
    }
}
