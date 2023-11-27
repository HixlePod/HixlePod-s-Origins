package com.hixlepod.hixlepodsorigins.common.NPCs.Quests;

import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.EasyQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.EndGameQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.HardQuestsPool;
import com.hixlepod.hixlepodsorigins.common.NPCs.Quests.Quests.MediumQuestsPool;

public class QuestsManager {

    static QuestWeightedRandom<EasyQuestsPool> easyQuests = new QuestWeightedRandom<>();
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
