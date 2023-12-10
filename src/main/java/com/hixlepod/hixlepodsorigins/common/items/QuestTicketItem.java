package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.EasyQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.QuestsManager;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuestTicketItem extends Item {

    String DisplayName;
    String Description;
    ItemStack Wanted;
    ItemStack Reward;

    public QuestTicketItem(Properties properties) {
        super(properties);

        DisplayName =  .getString("DisplayName");
        Description = SaveData.getString("Description");
        Wanted = SaveData.get("Wanted");
        Reward = SaveData.get("Reward");


    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, toolTip, flagIn);
        
        toolTip.add(Component.literal(stack.getTag().getString("DisplayName")));
        toolTip.add(Component.literal(ChatFormatting.DARK_GRAY + "----------------------"));
        toolTip.add(Component.literal(stack.getTag().getString("Description")));
        toolTip.add(Component.literal(" "));
        toolTip.add(Component.literal("  "));
        toolTip.add(Component.literal(ChatFormatting.GOLD + "Wanted: " + ChatFormatting.DARK_GREEN + stack.getTag().get("Wanted").getAsString() + ChatFormatting.GRAY + " x" + this.wanted_item.getCount()));
        toolTip.add(Component.literal(ChatFormatting.GOLD + "Reward: " + ChatFormatting.DARK_GREEN + stack.getTag().get("Reward").getAsString() + ChatFormatting.GRAY + " x" + this.reward.getCount()));
    }
}
