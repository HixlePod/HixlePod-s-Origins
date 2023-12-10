package com.hixlepod.hixlepodsorigins.common.NPCs.Quests;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.EasyQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.EndGameQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.HardQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.MediumQuestsPool;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.checkerframework.checker.units.qual.C;

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

        for (int i = 0; i < 10; i++) {
            System.out.println(easyQuests.getRandom());
        }
    }


    public static ItemStack generateQuestTicket() {
        EasyQuestsPool quest = easyQuests.getRandom();
        ItemStack itemStack = new ItemStack(Items.PAPER);
        CompoundTag SaveData = new CompoundTag();




        /*
        SaveData.putDouble("Weight", quest.getWeight());
        SaveData.putString("DisplayName", quest.getDisplayName());
        SaveData.putString("Description", quest.getDescription());
        SaveData.put("Wanted", quest.getWantedItem().getTag());
        SaveData.put("Reward", quest.getReward().getTag());

        itemStack.setTag(SaveData);


         */



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
