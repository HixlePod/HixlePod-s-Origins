package com.hixlepod.hixlepodsorigins.common.Entities.Pets;

import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

public class PetsManager {

    public static void attemptSummon(Player player, EntityType entityType) {

        if (OriginSettings.PETS_ENABLED) {

            if (entityType != null) {
                int count = 0;

                for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
                    if (entity.getType() == entityType) {
                        count++;
                    }
                }

                if (player.getPersistentData().getBoolean("IsPetSummoned")) {
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Your pet is already summoned!"));
                } else {
                    Entity entity = entityType.create(player.getLevel());

                    if (entityType == EntityInit.ECHO.get()) {
                        EntityEcho echo = new EntityEcho(entityType, player.getLevel());

                        echo.setEntityOwner(player);
                        echo.moveTo(player.position());
                        echo.setCustomName(Component.literal("Echo"));
                        echo.setCustomNameVisible(true);

                        player.getLevel().addFreshEntity(echo);
                    }

                    if (entityType == EntityInit.COMPASS.get()) {
                        EntityCompass compass = new EntityCompass(entityType, player.getLevel());

                        compass.setEntityOwner(player);
                        compass.moveTo(player.position());
                        compass.setCustomName(Component.literal("Compass"));
                        compass.setCustomNameVisible(true);

                        player.getLevel().addFreshEntity(compass);
                    }

                    if (entityType == EntityInit.RUNE.get()) {
                        EntityRune rune = new EntityRune(entityType, player.getLevel());

                        rune.setEntityOwner(player);
                        rune.moveTo(player.position());
                        rune.setCustomName(Component.literal("Rune"));
                        rune.setCustomNameVisible(true);

                        player.getLevel().addFreshEntity(rune);
                    }

                    if (entityType == EntityInit.PUMKIN.get()) {
                        EntityPumkin pumkin = new EntityPumkin(entityType, player.getLevel());

                        pumkin.setEntityOwner(player);
                        pumkin.moveTo(player.position());
                        pumkin.setCustomName(Component.literal("Pumkin"));
                        pumkin.setCustomNameVisible(true);

                        player.getLevel().addFreshEntity(pumkin);
                    }

                    if (entityType == EntityInit.DRAGON_SLAYER.get()) {
                        EntityDragonSlayer dragonSlayer = new EntityDragonSlayer(entityType, player.getLevel());

                        dragonSlayer.setEntityOwner(player);
                        dragonSlayer.moveTo(player.position());
                        dragonSlayer.setCustomName(Component.literal("Dragon Slayer"));
                        dragonSlayer.setCustomNameVisible(true);

                        player.getLevel().addFreshEntity(dragonSlayer);
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
                for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
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

        return null;
    }
}
