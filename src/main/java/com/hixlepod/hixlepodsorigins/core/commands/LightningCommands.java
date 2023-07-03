package com.hixlepod.hixlepodsorigins.core.commands;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class LightningCommands {

    public static int Zap(CommandSourceStack source, Collection<? extends Entity> entities) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            for(Entity entity : entities) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(entity.getLevel());

                lightning.moveTo(entity.position());
                lightning.setDamage(5);

                entity.getLevel().addFreshEntity(lightning);

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Zapped " + entity.getName().getString()));
            }
        }
        return 1;
    }

    public static int Smite(CommandSourceStack source, Collection<? extends Entity> entities) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            for(Entity entity : entities) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(entity.getLevel());

                lightning.moveTo(entity.position());
                lightning.setDamage(5);
                lightning.setVisualOnly(true);

                entity.getLevel().addFreshEntity(lightning);

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Smited " + entity.getName().getString()));
            }
        }
        return 1;
    }

    public static int FakeSmite(CommandSourceStack source, Collection<? extends Entity> entities) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            for(Entity entity : entities) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(entity.getLevel());

                lightning.moveTo(entity.position());
                lightning.setDamage(0);
                lightning.setVisualOnly(true);

                entity.getLevel().addFreshEntity(lightning);

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Fake Smited " + entity.getName().getString()));
            }
        }
        return 1;
    }
}
