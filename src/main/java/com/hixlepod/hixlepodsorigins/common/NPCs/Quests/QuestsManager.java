package com.hixlepod.hixlepodsorigins.common.NPCs.Quests;

import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.*;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class QuestsManager {

    public static QuestWeightedRandom<EasyQuestsPool> easyQuests = new QuestWeightedRandom<>();
    static QuestWeightedRandom<MediumQuestsPool> mediumQuests = new QuestWeightedRandom<>();
    static QuestWeightedRandom<HardQuestsPool> hardQuests = new QuestWeightedRandom<>();
    static QuestWeightedRandom<EndGameQuestsPool> endgameQuests = new QuestWeightedRandom<>();

    public static void register() {
        for (EasyQuestsPool quest : EasyQuestsPool.values()) {
            easyQuests.addEntry(quest, quest.getWeight());
        }

        for (MediumQuestsPool quest : MediumQuestsPool.values()) {
            mediumQuests.addEntry(quest, quest.getWeight());
        }

        for (HardQuestsPool quest : HardQuestsPool.values()) {
            hardQuests.addEntry(quest, quest.getWeight());
        }

        for (EndGameQuestsPool quest : EndGameQuestsPool.values()) {
            endgameQuests.addEntry(quest, quest.getWeight());
        }
    }

    public static EasyQuestsPool getEasyQuest() {
        return easyQuests.getRandom();
    }

    public static MediumQuestsPool getMediumQuest() {
        return mediumQuests.getRandom();
    }

    public static HardQuestsPool getHardQuest() {
        return hardQuests.getRandom();
    }

    public static EndGameQuestsPool getEndgameQuest() {
        return endgameQuests.getRandom();
    }


    public static ItemStack generateQuestTicket(QuestDifficulty questDifficulty) {

                QuestPools quest = null;

                switch (questDifficulty) {
                    case EASY -> quest = getEasyQuest();
                    case MEDIUM -> quest = getMediumQuest();
                    case HARD -> quest = getHardQuest();
                    case ENDGAME -> quest = getEndgameQuest();
                }

                ItemStack itemStack = new ItemStack(ItemInit.QUEST_TICKET.get());

                CompoundTag SaveData = itemStack.getOrCreateTag();


                SaveData.putDouble("Weight", quest.getWeight());
                SaveData.putString("DisplayName", quest.getDisplayName());
                SaveData.putString("Description", quest.getDescription());

                CompoundTag wantedTag = quest.getWanted().getOrCreateTag();
                wantedTag.putString("Item", quest.getWanted().getDisplayName().getString());
                wantedTag.putInt("Count", quest.getWanted().getCount());

                SaveData.put("Wanted", wantedTag);


                CompoundTag rewardTag = quest.getReward().getOrCreateTag();
                rewardTag.putString("Item", quest.getReward().getDisplayName().getString());
                rewardTag.putInt("Count", quest.getReward().getCount());

                SaveData.put("Reward", rewardTag);


                itemStack.setTag(SaveData);

                return itemStack;
    }


    public enum Quest {
        GATHER_ITEM,
        KILL_ENTITY,
        KILL_BOSS
    }

    public enum QuestDifficulty {
        EASY,
        MEDIUM,
        HARD,
        ENDGAME
    }
}
