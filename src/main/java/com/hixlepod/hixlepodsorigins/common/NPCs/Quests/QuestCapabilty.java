package com.hixlepod.hixlepodsorigins.common.NPCs.Quests;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
//LUKES A CUCK
public class QuestCapabilty {

    private String DisplayName;
    private String Description;
    private ItemStack Wanted;
    private ItemStack Reward;

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        this.DisplayName = displayName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public ItemStack getWanted() {
        return Wanted;
    }

    public void setWanted(ItemStack wanted) {
        this.Wanted = wanted;
    }

    public ItemStack getReward() {
        return Reward;
    }

    public void setReward(ItemStack reward) {
        this.Reward = reward;
    }

    public void copyFrom(QuestCapabilty source) {
        this.DisplayName = source.DisplayName;
        this.Description = source.Description;
        this.Wanted = source.Wanted;
        this.Reward = source.Reward;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("DisplayName", DisplayName);
        nbt.putString("Description", Description);
        nbt.put("Wanted", Wanted.getTag());
        nbt.put("Reward", Reward.getTag());
    }

    public void loadNBTData(CompoundTag nbt) {
        nbt.getString("DisplayName");
        nbt.getString("Description");
        nbt.get("Wanted");
        nbt.get("Reward");
    }
}
