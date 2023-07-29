package com.hixlepod.hixlepodsorigins.common.NPCs.Dialogues;

import com.hixlepod.hixlepodsorigins.core.utils.OriginsNPCUtils;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.server.MinecraftServer;

public class NimbusDialogue {

    public static void AnnounceCats(MinecraftServer server) {
        switch (OriginsUtil.randomInt(1, 4)) {
            case 1:
                OriginsNPCUtils.SendMessageToAll(server, ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": Hey friends! We've re-opened shop again at spawn, come check it out!");
                break;
            case 2:
                OriginsNPCUtils.SendMessageToAll(server, ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": ♪ The paws are back in town! The paws are back in town! ♪ Come check out what we have at spawn!");
                break;
            case 3:
                OriginsNPCUtils.SendMessageToAll(server, ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": Me and my pawsome friends are back in the cat house, come check it out!");
                break;
            case 4:
                OriginsNPCUtils.SendMessageToAll(server, ChatFormatting.LIGHT_PURPLE + "[Origins] Nimbus" + ChatFormatting.WHITE + ": Cat house has opened! I have some quests and my friends have items for sale!");
                break;
        }
    }

}
