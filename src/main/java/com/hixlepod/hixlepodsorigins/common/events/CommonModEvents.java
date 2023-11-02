package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.*;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.Corruptling.EntityCorruptling;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss.EntityBetaScraplet;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss.EntityScrapletBoss;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityBooNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityNimbusNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntitySmudgeNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.*;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronChicken;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronCow;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronHorse;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronPig;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityCybertronCreeper;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityCybertronHostileCow;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityCybertronZombie;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityScraplet;
import com.hixlepod.hixlepodsorigins.common.origins.Flo_Plays_;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import sereneseasons.api.season.Season;
import sereneseasons.api.season.SeasonChangedEvent;

public class CommonModEvents {

    @Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class CommonModEventsMod {

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            //Mobs
            event.put(EntityInit.BLUE_SLIME.get(), EntityBlueSlime.createAttributes().build());

            //PETS
            event.put(EntityInit.ECHO.get(), EntityEcho.createAttributes().build());
            event.put(EntityInit.COMPASS.get(), EntityCompass.createAttributes().build());
            event.put(EntityInit.RUNE.get(), EntityRune.createAttributes().build());
            event.put(EntityInit.PUMKIN.get(), EntityPumkin.createAttributes().build());
            event.put(EntityInit.DRAGON_SLAYER.get(), EntityDragonSlayer.createAttributes().build());
            event.put(EntityInit.POSSUM.get(), EntityPossum.createAttributes().build());


            //CYBER MOBS
            event.put(EntityInit.CYBERTRON_PIG.get(), EntityCybertronPig.createAttributes().build());
            event.put(EntityInit.CYBERTRON_CHICKEN.get(), EntityCybertronChicken.createAttributes().build());
            event.put(EntityInit.CYBERTRON_COW.get(), EntityCybertronCow.createAttributes().build());
            event.put(EntityInit.TEN.get(), EntityTen.createAttributes().build());
            event.put(EntityInit.CYBERTRON_HORSE.get(), EntityCybertronHorse.createBaseHorseAttributes().build());

            event.put(EntityInit.SCRAPLET.get(), EntityScraplet.createAttributes().build());
            event.put(EntityInit.CYBERTRON_CREEPER.get(), EntityCybertronCreeper.createAttributes().build());
            event.put(EntityInit.CYBERTRON_HOSTILE_COW.get(), EntityCybertronHostileCow.createAttributes().build());
            event.put(EntityInit.CYBERTRON_ZOMBIE.get(), EntityCybertronZombie.createAttributes().build());


            //Bosses
            event.put(EntityInit.SCRAPLET_BOSS.get(), EntityScrapletBoss.createAttributes().build());
            event.put(EntityInit.BETA_SCRAPLET.get(), EntityBetaScraplet.createAttributes().build());

            event.put(EntityInit.CORRUPTLING.get(), EntityCorruptling.createAttributes().build());

            //NPC
            event.put(EntityInit.NPC_NIMBUS.get(), EntityNimbusNPC.createAttributes().build());
            event.put(EntityInit.NPC_BOO.get(), EntityBooNPC.createAttributes().build());
            event.put(EntityInit.NPC_SMUDGE.get(), EntitySmudgeNPC.createAttributes().build());
        }
    }



    @Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class CommonModEventsForge {

        @SubscribeEvent
        public static void seasonChange(SeasonChangedEvent event) {
            if (event.getNewSeason() == Season.SubSeason.EARLY_AUTUMN || event.getNewSeason() == Season.SubSeason.EARLY_SPRING || event.getNewSeason() == Season.SubSeason.EARLY_SUMMER || event.getNewSeason() == Season.SubSeason.EARLY_WINTER) {
                ServerPlayer player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(Flo_Plays_.NAME);
                if (player != null) {
                    player.getInventory().add(new ItemStack(Items.FEATHER, OriginsUtil.randomInt(11, 24)));
                }
            }
        }
    }
}


