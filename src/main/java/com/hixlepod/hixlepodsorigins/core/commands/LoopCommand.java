package com.hixlepod.hixlepodsorigins.core.commands;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoopCommand {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);

    public static int Command(CommandSourceStack source, String Amount, String Delay, Component command) {

        if (source.isPlayer()) {

            Player player = source.getPlayer();

            int loops = Integer.parseInt(Amount);
            int delay = (int) (1000 * Float.parseFloat(Delay));

            String command_list = String.join(" ", command.getString());

            for (int i = 0; i < loops; i++) {
                scheduler.schedule(new Runnable() {
                    public void run() {
                        Loop(source, loops, command_list);
                    }
                }, delay * i, TimeUnit.MILLISECONDS);
            }
        }
        return 1;
    }

    static void Loop(CommandSourceStack source, int Loops, String Command) {

        Player player = source.getPlayer();
        player.getServer().getCommands().performPrefixedCommand(source, Command);
        player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Loop!"));

    }

}
