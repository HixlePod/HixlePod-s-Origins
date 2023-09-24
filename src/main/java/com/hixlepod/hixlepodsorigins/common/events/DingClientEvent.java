package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DingClientEvent {

    public static boolean postInit;

    private static boolean played;
    private static boolean playWorld;
    private static boolean hasLoadingGui;


    @SubscribeEvent
    public void onClientLoggedInEvent(ClientPlayerNetworkEvent.LoggingIn event)
    {
        promptToPlayWorld();
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.LevelTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            onWorldTickEnd();
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            onClientTickEnd();
        }
    }

    public static void postInit()
    {
        postInit = true;

        if(!played)
        {
            played = true;
            playSound();
        }
    }

    public static void promptToPlayWorld()
    {
        playWorld = true;
    }

    public static void onWorldTickEnd()
    {
        if(playWorld && Minecraft.getInstance().player != null && (Minecraft.getInstance().player.tickCount > 20 || Minecraft.getInstance().isPaused()))
        {
            playWorld = false;
            playSound();
        }
    }

    public static void onClientTickEnd()
    {
        if(Minecraft.getInstance().getOverlay() == null && hasLoadingGui)
        {
            playSound();
        }
        hasLoadingGui = Minecraft.getInstance().getOverlay() instanceof LoadingOverlay;
    }

    public static void playSound()
    {
        Minecraft.getInstance().getSoundManager().play(new SimpleSoundInstance(SoundEvents.NOTE_BLOCK_BELL.get().getLocation(), SoundSource.MASTER, 10, 0, SoundInstance.createUnseededRandom(), false, 0, SoundInstance.Attenuation.NONE, 0.0D, 0.0D, 0.0D, true));
    }
}
