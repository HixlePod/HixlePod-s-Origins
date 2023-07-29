package com.hixlepod.hixlepodsorigins.common.NPCs;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityBooNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityNimbusNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntitySmudgeNPC;
import com.hixlepod.hixlepodsorigins.common.NPCs.Dialogues.NimbusDialogue;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;

public class NPCManager {

    static boolean HAS_SPAWNED = false;

    public static void CheckNPCIsSpawnable(TickEvent.LevelTickEvent event) {

        long DayCount = event.level.getDayTime() / 24000;

        if (DayCount % OriginSettings.NPC_SPAWN_FREQUENCY == 0) {
            if (OriginSettings.SHOULD_NPC_SPAWN) {
                if (HAS_SPAWNED != true) {

                    SpawnNPCs(event.level.getServer());
                    NimbusDialogue.AnnounceCats(event.level.getServer());

                    HAS_SPAWNED = true;
                }
            }
        } else {
            HAS_SPAWNED = false;
        }
    }

    public static void SpawnNPCs(MinecraftServer server) {
        EntityNimbusNPC nimbusNPC = EntityInit.NPC_NIMBUS.get().create(server.overworld());
        SpawnMethod(nimbusNPC, new Vec3(10.5, 130, 10.5));
        server.overworld().addFreshEntity(nimbusNPC);

        EntitySmudgeNPC smudgeNPC = EntityInit.NPC_SMUDGE.get().create(server.overworld());
        SpawnMethod(smudgeNPC, new Vec3(12.5, 130, 10.5));
        server.overworld().addFreshEntity(smudgeNPC);

        EntityBooNPC booNPC = EntityInit.NPC_BOO.get().create(server.overworld());
        SpawnMethod(booNPC, new Vec3(14.5, 130, 10.5));
        server.overworld().addFreshEntity(booNPC);
    }

    static Entity SpawnMethod(Entity entity, Vec3 Pos) {
        entity.moveTo(Pos);
        if (entity instanceof Mob) {
            ((Mob) entity).finalizeSpawn(entity.getServer().getLevel(entity.getLevel().dimension()), entity.getLevel().getCurrentDifficultyAt(new BlockPos(Pos)), MobSpawnType.TRIGGERED, null, null);
        }

        return entity;
    }
}
