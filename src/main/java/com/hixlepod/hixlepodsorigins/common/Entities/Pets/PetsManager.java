package com.hixlepod.hixlepodsorigins.common.Entities.Pets;

import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;

public class PetsManager {

    public static void attemptSummon(Player player, EntityType entityType) {

        if (OriginSettings.PETS_ENABLED) {

            if (entityType != null) {
                int count = 0;

                for (Entity entity : player.getServer().getLevel(player.level().dimension()).getAllEntities()) {
                    if (entity.getType() == entityType) {
                        count++;
                    }
                }

                if (player.getPersistentData().getBoolean("IsPetSummoned")) {
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Your pet is already summoned!"));
                } else {
                    Entity entity = entityType.create(player.level());

                    if (entityType == EntityInit.ECHO.get()) {
                        EntityEcho echo = new EntityEcho(entityType, player.level());

                        echo.setEntityOwner(player);
                        echo.moveTo(player.position());
                        echo.setCustomName(Component.literal("Echo"));
                        echo.setCustomNameVisible(true);

                        player.level().addFreshEntity(echo);
                    }

                    if (entityType == EntityInit.COMPASS.get()) {
                        EntityCompass compass = new EntityCompass(entityType, player.level());

                        compass.setEntityOwner(player);
                        compass.moveTo(player.position());
                        compass.setCustomName(Component.literal("Compass"));
                        compass.setCustomNameVisible(true);

                        player.level().addFreshEntity(compass);
                    }

                    if (entityType == EntityInit.RUNE.get()) {
                        EntityRune rune = new EntityRune(entityType, player.level());

                        rune.setEntityOwner(player);
                        rune.moveTo(player.position());
                        rune.setCustomName(Component.literal("Rune"));
                        rune.setCustomNameVisible(true);

                        player.level().addFreshEntity(rune);
                    }

                    if (entityType == EntityInit.PUMKIN.get()) {
                        EntityPumkin pumkin = new EntityPumkin(entityType, player.level());

                        pumkin.setEntityOwner(player);
                        pumkin.moveTo(player.position());
                        pumkin.setCustomName(Component.literal("Pumkin"));
                        pumkin.setCustomNameVisible(true);

                        player.level().addFreshEntity(pumkin);
                    }

                    if (entityType == EntityInit.DRAGON_SLAYER.get()) {
                        EntityDragonSlayer dragonSlayer = new EntityDragonSlayer(entityType, player.level());

                        dragonSlayer.setEntityOwner(player);
                        dragonSlayer.moveTo(player.position());
                        dragonSlayer.setCustomName(Component.literal("Dragon Slayer"));
                        dragonSlayer.setCustomNameVisible(true);

                        player.level().addFreshEntity(dragonSlayer);
                    }

                    if (entityType == EntityInit.POSSUM.get()) {
                        EntityPossum entityPossum = new EntityPossum(entityType, player.level());

                        entityPossum.setEntityOwner(player);
                        entityPossum.moveTo(player.position());
                        entityPossum.setCustomName(Component.literal("Possum"));
                        entityPossum.setCustomNameVisible(true);

                        for (int i = 0; i < 3; i++) {
                            player.level().addFreshEntity(entityPossum);
                        }
                    }

                    player.getPersistentData().putBoolean("IsPetSummoned", true);
                    player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Summoning Pet!"));
                }
            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: You do not have a pet."));
            }
        } else {
            player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Origin pets have been disabled by the server administrator."));
        }
    }

    public static void attemptUnsummon(Player player, EntityType entityType) {
        if (entityType != null) {
            int count = 0;


            if (player.getPersistentData().getBoolean("IsPetSummoned")) {
                for (Entity entity : player.getServer().getLevel(player.level().dimension()).getAllEntities()) {
                    if (entity.getType() == entityType) {
                        count++;
                        entity.remove(Entity.RemovalReason.DISCARDED);

                        player.getPersistentData().putBoolean("IsPetSummoned", false);
                        player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Unsummoned Pet!"));
                    }
                }
            } else {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Your pet is already unsummoned!"));
            }
        }
    }

    static int SET_FRIENDLY = 2;
    static int SET_NEUTRAL = 3;
    static int SET_AGGRESSIVE = 4;


    public static void SetEntityBehaviour(Player player, int BehaviourType) {
        if (BehaviourType == SET_FRIENDLY) {
            player.getPersistentData().putString("PetBehaviour", "FRIENDLY");
            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Set pet behaviour Friendly!"));

        } else if (BehaviourType == SET_NEUTRAL) {
            player.getPersistentData().putString("PetBehaviour", "NEUTRAL");
            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Set pet behaviour Neutral!"));

        } else if (BehaviourType == SET_AGGRESSIVE) {
            player.getPersistentData().putString("PetBehaviour", "AGGRESSIVE");
            player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error."));

        }
    }

    public static void GetHostility(String BehaviorType, TamableAnimal pet) {
        if (BehaviorType == "FRIENDLY") {
            pet.setTarget(null);

        } else if (BehaviorType == "NEUTRAL") {
            //Pet will manage hostility themselves

        } else if (BehaviorType == "AGGRESSIVE") {
            //Doesn't work yet
        } else {
            //Same as neutral
        }
    }

    public static EntityType returnPlayerPet(Player player) {
        if (player.getName().equals(Component.literal(HixlePod.NAME))) {
            return EntityInit.ECHO.get();
        }

        if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
            return EntityInit.COMPASS.get();
        }

        if (player.getName().equals(Component.literal(Maxwell.NAME))) {
            return EntityInit.RUNE.get();
        }

        if (player.getName().equals(Component.literal(undramaticc.NAME))) {
            return EntityInit.PUMKIN.get();
        }

        if (player.getName().equals(Component.literal(J_Curve.NAME))) {
            return EntityInit.DRAGON_SLAYER.get();
        }

        if (player.getName().equals(Component.literal(Aniriai.NAME))) {
            return EntityInit.POSSUM.get();
        }

        return null;
    }
}
