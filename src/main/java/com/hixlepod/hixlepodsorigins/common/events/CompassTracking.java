package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.Ores;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.networking.packet.PotionPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CompassTracking {

    @SubscribeEvent
    public static void onPlayerDeath(PlayerEvent.PlayerRespawnEvent event) {
        if(event.getEntity() instanceof Player) {
            sendAll((Player)event.getEntity());
        }
    }
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(event.getEntity() instanceof Player) {
            sendAll((Player)event.getEntity());
        }
    }

    private static void sendAll(Player player) {
        PotionPacket pkt;
        pkt = new PotionPacket(Ores.COPPER.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.COAL.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.DIAMOND.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.EMERALD.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.GOLD.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.IRON.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.LAPIS.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.NETHERITE.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);
        pkt = new PotionPacket(Ores.QUARTZ.toString());
        NetworkManager.sendToPlayer(pkt, (ServerPlayer) player);


    }
}
