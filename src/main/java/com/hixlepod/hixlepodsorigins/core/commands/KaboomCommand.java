package com.hixlepod.hixlepodsorigins.core.commands;

import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class KaboomCommand {

    public static int Kaboom(CommandSourceStack source, Collection<? extends Entity> entities) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            for(Entity entity : entities) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(entity.getLevel());

                lightning.moveTo(entity.position());
                lightning.setDamage(0);
                lightning.setVisualOnly(true);


                entity.getLevel().addFreshEntity(lightning);

                entity.hurtMarked = true;
                entity.setDeltaMovement(player.getDeltaMovement().add(0,8,0));

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Kaboomed " + entity.getName().getString()));
            }
        }
        return 1;
    }
}
