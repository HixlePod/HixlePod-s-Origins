package com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.xray;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, value = Dist.CLIENT)
public class XrayEvents {

    @SubscribeEvent
    public static void onExit(ServerStoppingEvent event) {
        if ((Controller.drawOres())) {
            Controller.toggleDrawOres();
        }
        Controller.shutdownExecutor();
    }
    @SubscribeEvent
    public static void pickupItem(BlockEvent.BreakEvent event) {
        RenderEnqueue.checkBlock(event.getPos(), event.getState(), false);
    }

    @SubscribeEvent
    public static void placeItem(BlockEvent.EntityPlaceEvent event) {
        RenderEnqueue.checkBlock(event.getPos(), event.getState(), true);
    }

    @SubscribeEvent
    public static void chunkLoad(ChunkEvent.Load event) {
        Controller.requestBlockFinder(true);
    }


    @SubscribeEvent
    public static void tickEnd(TickEvent.ClientTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {

            Controller.requestBlockFinder(false);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onWorldRenderLast(RenderLevelStageEvent event) // Called when drawing the world.
    {

        if ((Controller.drawOres()) && (HixlePodsOrigins.proxy.getMinecraft().player != null) && (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
            // this is a world pos of the player
            try {
                Render.INSTANCE.drawOres(event);
            } catch (Throwable ignore) {
            }
        }
    }
}