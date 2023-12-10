package com.hixlepod.hixlepodsorigins.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuestTicketItem extends Item {

    public QuestTicketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, toolTip, flagIn);

        CompoundTag tags = this.getShareTag(stack);




        CompoundTag wantedTag = (CompoundTag) tags.get("Wanted");
        CompoundTag rewardTag = (CompoundTag) tags.get("Reward");

        toolTip.add(Component.literal(tags.getString("DisplayName")));
        toolTip.add(Component.literal(ChatFormatting.DARK_GRAY + "----------------------"));
        toolTip.add(Component.literal(tags.getString("Description")));
        toolTip.add(Component.literal(" "));
        toolTip.add(Component.literal(ChatFormatting.GOLD + "Wanted: " + ChatFormatting.DARK_GREEN + wantedTag.getString("Item") + ChatFormatting.GRAY + " x" + wantedTag.getInt("Count")));
        toolTip.add(Component.literal(ChatFormatting.GOLD + "Reward: " + ChatFormatting.DARK_GREEN + rewardTag.getString("Item") + ChatFormatting.GRAY + " x" + rewardTag.getInt("Count")));
    }
}
