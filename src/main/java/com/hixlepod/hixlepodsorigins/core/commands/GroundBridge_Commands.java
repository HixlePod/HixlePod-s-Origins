package com.hixlepod.hixlepodsorigins.core.commands;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.J_Curve;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.datafixers.types.templates.CompoundList;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandRuntimeException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.targets.FMLServerLaunchHandler;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroundBridge_Commands {

    public static int SAVE_LOCATION(CommandSourceStack source, String name) {
        if (source.isPlayer()) {
            if (OriginSettings.GROUND_BRIDGE_ENABLED) {

                Player player = source.getPlayer();

                CompoundTag SaveData = new CompoundTag();
                CompoundTag Locations = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeLocations");

                SaveData.putString("Name", name);
                SaveData.putString("Level", player.getLevel().dimension().location().toString());
                SaveData.putDouble("PosX", player.position().x());
                SaveData.putDouble("PosY", player.position().y());
                SaveData.putDouble("PosZ", player.position().z());
                SaveData.putFloat("HeadRot", player.yHeadRot);
                //SaveData.putFloat("HeadRot0", player.yHeadRotO);

                Locations.put(name, SaveData);

                player.getPersistentData().put(HixlePodsOrigins.MODID + "_GroundBridgeLocations", Locations);

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Saved location as " + ChatFormatting.GOLD + name + ChatFormatting.GREEN + "!"));

            } else {
                source.sendSystemMessage(Component.literal(ChatFormatting.RED + "Ground bridge commands have been disabled by the server administrator."));
            }
        } else {
            source.sendSystemMessage(Component.literal(ChatFormatting.RED + "You must be a player to execute that command."));
        }

        return 1;
    }

    public static int DELETE_SAVE(CommandSourceStack source, String name) {
        if (source.isPlayer()) {
            if (OriginSettings.GROUND_BRIDGE_ENABLED) {

                Player player = source.getPlayer();

                CompoundTag Locations = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeLocations");

                Locations.remove(name);

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Removed " + ChatFormatting.GOLD + name + ChatFormatting.GREEN + "!"));

            } else {
                source.sendSystemMessage(Component.literal(ChatFormatting.RED + "Ground bridge commands have been disabled by the server administrator."));
            }
        } else {
            source.sendSystemMessage(Component.literal(ChatFormatting.RED + "You must be a player to execute that command."));
        }

        return 1;
    }

    public static int HELP(CommandSourceStack source) {
        if (source.isPlayer()) {
            if (OriginSettings.GROUND_BRIDGE_ENABLED) {

                Player player = source.getPlayer();

                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "\n\nStart by saving some locations.: \n" +
                        ChatFormatting.GOLD + " /groundbridge SAVE_LOCATION " + returnBaseName() + " \n" +
                        ChatFormatting.GOLD + " /groundbridge DELETE_SAVE " + returnBaseName() + " \n\n" +
                        ChatFormatting.GREEN + "Now you want to teleport, start by teleporting yourself or create a party to teleport a group:\n" +
                        ChatFormatting.GOLD + " /groundbridge TELEPORT Player Location"));

            } else {
                source.sendSystemMessage(Component.literal(ChatFormatting.RED + "Ground bridge commands have been disabled by the server administrator."));
            }

        } else {
            source.sendSystemMessage(Component.literal(ChatFormatting.RED + "You must be a player to execute that command."));
        }

        return 1;
    }

    public static int TELEPORT(CommandContext<CommandSourceStack> source, String targetedPlayerString, String location) {
        if (source.getSource().isPlayer()) {

            if (OriginSettings.GROUND_BRIDGE_ENABLED) {

                Player player = source.getSource().getPlayer();
                ServerPlayer targetedPlayer = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(targetedPlayerString);

                if (HasGroundBridge(player)) {
                    if (PlayerIsTeammateOrClose(player, targetedPlayer)) {

                        CompoundTag Locations = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeLocations");

                        CompoundTag data = Locations.getCompound(location);


                        //Convert the level string into an actual level variable that can be used
                        ResourceKey<Level> resourcekey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(data.getString("Level")));
                        ServerLevel serverlevel = player.getServer().getLevel(resourcekey);
                        if (serverlevel == null) {
                        }

                        targetedPlayer.teleportTo(serverlevel, data.getDouble("PosX"), data.getDouble("PosY"), data.getDouble("PosZ"), data.getFloat("HeadRot"), 0f);
                        targetedPlayer.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You have been teleported by " + ChatFormatting.GOLD + player.getName().getString() + ChatFormatting.GREEN + " to " + ChatFormatting.GOLD + location + ChatFormatting.GREEN + "!"));
                        OriginsManager.setAbilityData(targetedPlayer);

                        player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Teleported " + ChatFormatting.GOLD + targetedPlayer.getName().getString() + ChatFormatting.GREEN + " to " + ChatFormatting.GOLD + location + ChatFormatting.GREEN + "!"));

                    } else { player.sendSystemMessage(Component.literal(ChatFormatting.RED + "Player is too far away or not in the same dimension.")); }
                } else { player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You do not have a Ground Bridge.")); }
            } else { source.getSource().sendSystemMessage(Component.literal(ChatFormatting.RED + "Ground bridge commands have been disabled by the server administrator.")); }
        } else { source.getSource().sendSystemMessage(Component.literal(ChatFormatting.RED + "You must be a player to execute that command.")); }

        return 1;
    }

    public static int TELEPORT_TEAM(CommandContext<CommandSourceStack> source, String location) {
        if (source.getSource().isPlayer()) {

            if (OriginSettings.GROUND_BRIDGE_ENABLED) {

                Player player = source.getSource().getPlayer();

                if (HasGroundBridge(player)) {

                    for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
                        if (entity instanceof Player) {
                            if (player.getTeam() == entity.getTeam()) {
                                ServerPlayer targetedPlayer = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(entity.getName().getString());

                                CompoundTag Locations = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeLocations");

                                CompoundTag data = Locations.getCompound(location);


                                //Convert the level string into an actual level variable that can be used
                                ResourceKey<Level> resourcekey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(data.getString("Level")));
                                ServerLevel serverlevel = player.getServer().getLevel(resourcekey);
                                if (serverlevel == null) {
                                }

                                targetedPlayer.teleportTo(serverlevel, data.getDouble("PosX"), data.getDouble("PosY"), data.getDouble("PosZ"), data.getFloat("HeadRot"), 0f);
                                targetedPlayer.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You have been team teleported by " + ChatFormatting.GOLD + player.getName().getString() + ChatFormatting.GREEN + " to " + ChatFormatting.GOLD + location + ChatFormatting.GREEN + "!"));
                                OriginsManager.setAbilityData(targetedPlayer);

                                player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "Teleported team " + player.getTeam().getColor() + player.getTeam().getName() + ChatFormatting.GREEN + " to " + ChatFormatting.GOLD + location + ChatFormatting.GREEN + "!"));

                            }
                        }
                    }
                } else { player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You do not have a Ground Bridge.")); }
            } else { source.getSource().sendSystemMessage(Component.literal(ChatFormatting.RED + "Ground bridge commands have been disabled by the server administrator.")); }
        } else {source.getSource().sendSystemMessage(Component.literal(ChatFormatting.RED + "You must be a player to execute that command."));}

        return 1;
    }

    public static boolean PlayerIsTeammateOrClose(Player player, Player target) {
        if ((player.getTeam() == target.getTeam()) || (target.position().distanceTo(player.position()) < 20 && (target.getLevel() == player.getLevel()))) {
            return true;
        }

        return false;
    }

    public static boolean HasGroundBridge(Player player) {
        if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_HasGroundBridge") == true) {
            return true;
        }

        return false;
    }

    public static String returnBaseName() {
        String[] list = {"My_Cool_Base", "Rocky_Mines", "Campus", "Elderstock_Stronghold", "GHOST_OF_SPARTA", "RAACCCCCCCC", "Rolling_Hills"};
        Random random = new Random();

        return list[random.nextInt(list.length)];
    }
}
