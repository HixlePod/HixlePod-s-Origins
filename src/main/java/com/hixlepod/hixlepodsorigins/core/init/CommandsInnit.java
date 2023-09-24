package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.commands.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class CommandsInnit {

    private final CommandDispatcher<CommandSourceStack> dispatcher = new CommandDispatcher<>();

    public static void Register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> GroundbridgeCommand = dispatcher.register(Commands.literal("groundbridge")
                .then(Commands.literal("SAVE_LOCATION").requires(cs -> { return cs.hasPermission(0); }).then(Commands.argument("NAME", StringArgumentType.string()).executes((context) -> { return GroundBridge_Commands.SAVE_LOCATION(context.getSource(), StringArgumentType.getString(context, "NAME")); })))
                .then(Commands.literal("DELETE_LOCATION").requires(cs -> { return cs.hasPermission(0); }).then(Commands.argument("NAME", StringArgumentType.string()).suggests((context, builder) -> { return returnLocations(context, builder); }).executes((context) -> { return GroundBridge_Commands.DELETE_SAVE(context.getSource(), StringArgumentType.getString(context, "NAME")); })))

                .then(Commands.literal("TELEPORT").requires(cs -> { return cs.hasPermission(0); }).then(Commands.argument("PLAYER", StringArgumentType.string()).suggests((context, builder) -> returnPlayers(context, builder)).then(Commands.argument("LOCATION", StringArgumentType.string()).suggests((context, builder) -> { return returnLocations(context, builder); }).executes((context) -> { return GroundBridge_Commands.TELEPORT(context, StringArgumentType.getString(context, "PLAYER"), StringArgumentType.getString(context, "LOCATION")); }))))

                .then(Commands.literal("TELEPORT_PARTY").requires(cs -> { return cs.hasPermission(0); }).then(Commands.argument("NAME", StringArgumentType.string()).executes((context) -> { return GroundBridge_Commands.DELETE_SAVE(context.getSource(), StringArgumentType.getString(context, "NAME")); })))
                .then(Commands.literal("TELEPORT_TEAM").requires(cs -> { return cs.hasPermission(0); }).then(Commands.argument("NAME", StringArgumentType.string()).suggests((context, builder) -> { return returnLocations(context, builder); }).executes((context) -> { return GroundBridge_Commands.TELEPORT_TEAM(context, StringArgumentType.getString(context, "NAME")); })))
                .then(Commands.literal("HELP").requires(cs -> { return cs.hasPermission(0); }).executes((context) -> { return GroundBridge_Commands.HELP(context.getSource()); }))
        );

        LiteralCommandNode<CommandSourceStack> DisableOriginsMainCommand = dispatcher.register(Commands.literal("disable")
                .then(Commands.literal("TRIGGER_ABILITIES")     .requires(cs -> { return cs.hasPermission(1); }).then(Commands.argument("STATUS", StringArgumentType.string()).suggests((context, builder) -> returnBooleanValues(context, builder)).executes((context) -> { return DisableOriginsCommnad.CHANGE_TRIGGER_ABILITIES(context.getSource(), StringArgumentType.getString(context, "STATUS")); })))
                .then(Commands.literal("PETS")                  .requires(cs -> { return cs.hasPermission(1); }).then(Commands.argument("STATUS", StringArgumentType.string()).suggests((context, builder) -> returnBooleanValues(context, builder)).executes((context) -> { return DisableOriginsCommnad.CHANGE_PETS_ENABLED(context.getSource(), StringArgumentType.getString(context, "STATUS")); })))
                .then(Commands.literal("SMALL_ORIGIN_SITTING")  .requires(cs -> { return cs.hasPermission(1); }).then(Commands.argument("STATUS", StringArgumentType.string()).suggests((context, builder) -> returnBooleanValues(context, builder)).executes((context) -> { return DisableOriginsCommnad.CHANGE_SITTING_ENABLED(context.getSource(), StringArgumentType.getString(context, "STATUS")); })))
                .then(Commands.literal("ALL")                   .requires(cs -> { return cs.hasPermission(1); }).then(Commands.argument("STATUS", StringArgumentType.string()).suggests((context, builder) -> returnBooleanValues(context, builder)).executes((context) -> { return DisableOriginsCommnad.CHANGE_ALL(context.getSource(), StringArgumentType.getString(context, "STATUS")); })))
                .then(Commands.literal("GROUND_BRIDGES")        .requires(cs -> { return cs.hasPermission(1); }).then(Commands.argument("STATUS", StringArgumentType.string()).suggests((context, builder) -> returnBooleanValues(context, builder)).executes((context) -> { return DisableOriginsCommnad.CHANGE_GROUND_BRIDGE_ENABLED(context.getSource(), StringArgumentType.getString(context, "STATUS")); })))
        );

        LiteralCommandNode<CommandSourceStack> SetNameCommand = dispatcher.register(Commands.literal("name")
                .then(Commands.argument("NAME", StringArgumentType.string()).executes((context) -> { return SetCustomNameCommand.SET_CUSTOM_NAME(context.getSource(), StringArgumentType.getString(context, "NAME")); }))
        );


        //Goofy Commands
        LiteralCommandNode<CommandSourceStack> KaboomCommand = dispatcher.register(Commands.literal("kaboom").requires((cs) -> { return cs.hasPermission(1); })
                .then(Commands.argument("PLAYER", EntityArgument.players())
                        .executes((context) -> { return com.hixlepod.hixlepodsorigins.core.commands.KaboomCommand.Kaboom(context.getSource(), EntityArgument.getPlayers(context, "PLAYER")); }))
        );

        LiteralCommandNode<CommandSourceStack> ZapCommand = dispatcher.register(Commands.literal("zap").requires((cs) -> { return cs.hasPermission(1); })
                .then(Commands.argument("PLAYER", EntityArgument.players())
                        .executes((context) -> { return LightningCommands.Zap(context.getSource(), EntityArgument.getPlayers(context, "PLAYER")); }))
        );

        LiteralCommandNode<CommandSourceStack> SmiteCommand = dispatcher.register(Commands.literal("smite").requires((cs) -> { return cs.hasPermission(1); })
                .then(Commands.argument("PLAYER", EntityArgument.players())
                        .executes((context) -> { return LightningCommands.Smite(context.getSource(), EntityArgument.getPlayers(context, "PLAYER")); }))
        );

        LiteralCommandNode<CommandSourceStack> FakeSmiteCommand = dispatcher.register(Commands.literal("fakesmite").requires((cs) -> { return cs.hasPermission(1); })
                .then(Commands.argument("PLAYER", EntityArgument.players())
                        .executes((context) -> { return LightningCommands.FakeSmite(context.getSource(), EntityArgument.getPlayers(context, "PLAYER")); }))
        );

        LiteralCommandNode<CommandSourceStack> LoopCommand = dispatcher.register(Commands.literal("loop").requires((cs) -> { return cs.hasPermission(1); })
                .then(Commands.argument("AMOUNT", StringArgumentType.string())
                        .then(Commands.argument("DELAY", StringArgumentType.string())
                                .then(Commands.argument("COMMAND", MessageArgument.message())
                                        .executes((context) -> { return com.hixlepod.hixlepodsorigins.core.commands.LoopCommand.Command(context.getSource(), StringArgumentType.getString(context, "AMOUNT"), StringArgumentType.getString(context, "DELAY"), MessageArgument.getMessage(context, "COMMAND")); }))))
        );
    }

    public static CompletableFuture<Suggestions> returnBooleanValues(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {

        builder.suggest("ENABLE");
        builder.suggest("DISABLE");

        return builder.buildFuture();
    }

    public static CompletableFuture<Suggestions> returnLocations(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {

        Player player = context.getSource().getPlayer();

        CompoundTag Locations = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeLocations");

        for (String location : Locations.getAllKeys()) {
            builder.suggest(location);

        }

        return builder.buildFuture();
    }

    public static CompletableFuture<Suggestions> returnPlayers(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        Player player = context.getSource().getPlayer();

        for (String currentPlayer : player.getServer().getPlayerList().getPlayerNamesArray()) {
            builder.suggest(currentPlayer);
        }

        return builder.buildFuture();
    }
}