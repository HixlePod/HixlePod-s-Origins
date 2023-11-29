package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.EasyQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.QuestsManager;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuestTicketItem extends Item {

    private String display_name;
    private String description;
    private ItemStack wanted_item;
    private ItemStack reward;


    public QuestTicketItem(Properties properties) {
        super(properties);

        EasyQuestsPool quest = QuestsManager.easyQuests.getRandom();

        this.display_name = quest.getDisplayName();
        this.description = quest.getDescription();
        this.wanted_item = quest.getWantedItem();
        this.reward = quest.getReward();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> toolTip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, toolTip, flagIn);
        
        toolTip.add(Component.literal(this.display_name));
        toolTip.add(Component.literal(ChatFormatting.DARK_GRAY + "----------------------"));
        toolTip.add(Component.literal(this.description));
        toolTip.add(Component.literal(" "));
        toolTip.add(Component.literal("  "));
        toolTip.add(Component.literal(ChatFormatting.GOLD + "Wanted: " + ChatFormatting.DARK_GREEN + this.wanted_item.getDisplayName() + ChatFormatting.GRAY + " x" + this.wanted_item.getCount()));
        toolTip.add(Component.literal(ChatFormatting.GOLD + "Reward: " + ChatFormatting.DARK_GREEN + this.reward.getDisplayName() + ChatFormatting.GRAY + " x" + this.reward.getCount()));
    }
}
