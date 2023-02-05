package com.hixlepod.hixlepodsorigins.common.Pets;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.AmbrosiaElf;
import com.hixlepod.hixlepodsorigins.common.origins.HixlePod;
import com.hixlepod.hixlepodsorigins.common.origins.Maxwell;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PetsManager {

    public static void attemptSummon(Player player, EntityType entityType) {

        if (entityType != null) {
            int count = 0;

            for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
                if (entity.getType() == entityType) {
                    count++;
                }
            }

            if (player.getPersistentData().getBoolean("IsPetSummoned") == true) {
                player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Your pet is already summoned!"));
            } else {
                Entity entity = entityType.create(player.getLevel());

                if (entityType == EntityInit.ECHO.get()) {
                    EntityEcho echo = new EntityEcho(entityType, player.getLevel());

                    echo.setEntityOwner(player);
                    echo.moveTo(player.position());

                    player.getLevel().addFreshEntity(echo);
                }

                if (entityType == EntityInit.COMPASS.get()) {
                    EntityCompass compass = new EntityCompass(entityType, player.getLevel());

                    compass.setEntityOwner(player);
                    compass.moveTo(player.position());

                    player.getLevel().addFreshEntity(compass);
                }

                if (entityType == EntityInit.RUNE.get()) {
                    EntityRune rune = new EntityRune(entityType, player.getLevel());

                    rune.setEntityOwner(player);
                    rune.moveTo(player.position());

                    player.getLevel().addFreshEntity(rune);
                }

                player.getPersistentData().putBoolean("IsPetSummoned", true);
                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Summoning Pet!"));
            }
        } else {
            player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Error: You do not have a pet."));
        }
    }

    public static void attemptUnsummon(Player player, EntityType entityType) {
        if (entityType != null) {
            int count = 0;


            if (player.getPersistentData().getBoolean("IsPetSummoned") == true) {
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

        return null;
    }
}
