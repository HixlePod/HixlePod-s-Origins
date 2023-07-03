package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.NPC.BooNPCRenderer;
import com.hixlepod.hixlepodsorigins.client.NPC.NimbusNPCRenderer;
import com.hixlepod.hixlepodsorigins.client.NPC.SmudgeNPCRenderer;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.*;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.*;
import com.hixlepod.hixlepodsorigins.client.Renderer.*;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.*;
import com.hixlepod.hixlepodsorigins.client.screen.LoreMenuScreen;
import com.hixlepod.hixlepodsorigins.client.screen.PetMenuScreen;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.core.init.*;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability1C2SPacket;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability2C2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientModEvents {

    @Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof Player) {
                Player player = event.getEntity();

                if (!player.getServer().isDedicatedServer()) {

                    player.sendSystemMessage(Component.literal("[HixlePod's Origins] Mod detected running on singleplayer, this is not recommended as this mod requires a multiplayer session. Most features will be broken!"));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void playerRenderEvent(RenderPlayerEvent.Pre event) {
            Player player = event.getEntity();

            if (player.getName().equals(Component.literal(Fudge105.NAME))) {
                if (Fudge105.isInvisible == true) {
                    player.setInvisible(true);
                    event.setCanceled(true);

                } else {
                    player.setInvisible(false);
                    event.setCanceled(false);
                }
            }

            if (player.getName().equals(Component.literal(AllyIsAngy.NAME))) {
                if (AllyIsAngy.isInvisible) {
                    player.setInvisible(true);
                    event.setCanceled(true);

                } else {
                    player.setInvisible(false);
                    event.setCanceled(false);
                }
            }


        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;

            if (player.getName().equals(Component.literal(HixlePod.NAME)) || player.getName().equals(Component.literal(Folf_Gaming.NAME))) {
                if (player.isInWater()) {
                    if (!player.isCrouching()) {
                        player.setDeltaMovement(player.getDeltaMovement().add(0, 0.025, 0));
                    }
                }
            }

            if (player.getName().equals(Component.literal(AmbrosiaElf.NAME)) || player.getName().equals(Component.literal(Kira_uwu69.NAME))) {
                if (player.isInWater()) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0, -0.03, 0));
                }
            }

            if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
                if (player.isInWater()) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0, -0.035, 0));
                }
            }
        }

        private static boolean inverted = false;

        @SubscribeEvent
        public static void movementInputUpdate(MovementInputUpdateEvent event) {
            Player player = Minecraft.getInstance().player;
            if (Minecraft.getInstance().player.isAlive()) {
                if (player.hasEffect(EffectsInit.MALFUNCTION.get())) {

                    if (inverted == false) {
                        Minecraft.getInstance().options.invertYMouse().set(!Minecraft.getInstance().options.invertYMouse().get());
                    }
                    invert(event);
                    inverted = true;

                } else {
                    if (inverted == true) {
                        Minecraft.getInstance().options.invertYMouse().set(!Minecraft.getInstance().options.invertYMouse().get());
                    }
                    inverted = false;
                }
            }
        }

        private static void invert(MovementInputUpdateEvent event) {
            boolean up = event.getInput().up;
            boolean down = event.getInput().down;
            boolean left = event.getInput().left;
            boolean right = event.getInput().right;
            boolean shift = event.getInput().shiftKeyDown;
            boolean jump = event.getInput().jumping;

            event.getInput().forwardImpulse = calculateImpulse(up, down);
            event.getInput().leftImpulse = calculateImpulse(left, right);

            event.getInput().up = down;
            event.getInput().down = up;
            event.getInput().left = right;
            event.getInput().right = left;
            event.getInput().shiftKeyDown = jump;
            event.getInput().jumping = shift;
        }



        private static float calculateImpulse(boolean p_205578_, boolean p_205579_) {
            if (p_205578_ == p_205579_) {
                return 0.0F;
            } else {
                return p_205578_ ? -1.0F : 1.0F;
            }
        }

        @SubscribeEvent
        public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0,0.3,0));
                }

                if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0,0.6,0));
                }

                if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0,0.7,0));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public void onDigEvent(final PlayerEvent.BreakSpeed event) {
            Player player = event.getEntity();

            float f = event.getOriginalSpeed();
            if (!player.isCreative()) {

                if (player.getName().equals(Component.literal(Fudge105.NAME))) {
                    if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) && !EnchantmentHelper.hasAquaAffinity(player)) {

                        f *= 5.0F;
                        event.setNewSpeed(f);
                    }

                    if (!player.isOnGround()) {
                        f *= 5.0F;
                        event.setNewSpeed(f);
                    }
                }

                if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
                    if (!player.isOnGround()) {
                        f *= 5.0F;
                        event.setNewSpeed(f);
                    }
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyInit.ABILITY_1.consumeClick()) {
                NetworkManager.sendToServer(new Ability1C2SPacket());
            }

            if (KeyInit.ABILITY_2.consumeClick()) {
                NetworkManager.sendToServer(new Ability2C2SPacket());
            }

            if (KeyInit.PET_MENU.consumeClick()) {
                Minecraft.getInstance().setScreen(new PetMenuScreen("Pet menu"));
            }

            if (KeyInit.ABILITY_3.consumeClick()) {
                Minecraft.getInstance().setScreen(new LoreMenuScreen("Ye"));
            }

            /*
            if (Minecraft.getInstance().options.keyJump.consumeClick()) {
                Player player = Minecraft.getInstance().player;

                NetworkManager.sendToServer(new ElytraAttemptFlyPacket());
            }

             */
        }
    }

    @Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {

        @SubscribeEvent(priority = EventPriority.LOW)
        public static void renderPlayerLayers(final EntityRenderersEvent.AddLayers event) {
            LivingEntityRenderer<Player, PlayerModel<Player>> renderer_normal = event.getSkin("default");

            FloElytraLayer<Player, PlayerModel<Player>> FloWingsLayer = new FloElytraLayer<>(renderer_normal, event.getEntityModels());
            GhostElytraLayer<Player, PlayerModel<Player>> GhostWingsLayer = new GhostElytraLayer<>(renderer_normal, event.getEntityModels());
            WhirlElytraLayer<Player, PlayerModel<Player>> WhirlElytraLayer = new WhirlElytraLayer<>(renderer_normal, event.getEntityModels());
            TricoElytraLayer<Player, PlayerModel<Player>> TricoElytraLayer = new TricoElytraLayer<>(renderer_normal, event.getEntityModels());
            RaccGoatHornsLayer<Player, PlayerModel<Player>> RaccHornsLayer = new RaccGoatHornsLayer<>(renderer_normal, event.getEntityModels());
            KyoUnicronHornLayer<Player, PlayerModel<Player>> kyoUnicronHornLayer = new KyoUnicronHornLayer<>(renderer_normal, event.getEntityModels());

            renderer_normal.addLayer(FloWingsLayer);
            renderer_normal.addLayer(GhostWingsLayer);
            renderer_normal.addLayer(WhirlElytraLayer);
            renderer_normal.addLayer(TricoElytraLayer);
            renderer_normal.addLayer(RaccHornsLayer);
            renderer_normal.addLayer(kyoUnicronHornLayer);

            //

            LivingEntityRenderer<Player, PlayerModel<Player>> renderer_slim = event.getSkin("slim");

            FloElytraLayer<Player, PlayerModel<Player>> FloWingsLayer_Slim = new FloElytraLayer<>(renderer_slim, event.getEntityModels());
            GhostElytraLayer<Player, PlayerModel<Player>> GhostWingsLayer_Slim = new GhostElytraLayer<>(renderer_slim, event.getEntityModels());
            WhirlElytraLayer<Player, PlayerModel<Player>> WhirlWingsLayer_Slim = new WhirlElytraLayer<>(renderer_slim, event.getEntityModels());
            TricoElytraLayer<Player, PlayerModel<Player>> TricoWingsLayer_Slim = new TricoElytraLayer<>(renderer_slim, event.getEntityModels());
            RaccGoatHornsLayer<Player, PlayerModel<Player>> RaccHornsLayer_Slim = new RaccGoatHornsLayer<>(renderer_slim, event.getEntityModels());
            KyoUnicronHornLayer<Player, PlayerModel<Player>> kyoUnicronHornLayer_Slim = new KyoUnicronHornLayer<>(renderer_slim, event.getEntityModels());

            renderer_slim.addLayer(FloWingsLayer_Slim);
            renderer_slim.addLayer(GhostWingsLayer_Slim);
            renderer_slim.addLayer(WhirlWingsLayer_Slim);
            renderer_slim.addLayer(TricoWingsLayer_Slim);
            renderer_slim.addLayer(RaccHornsLayer_Slim);
            renderer_slim.addLayer(kyoUnicronHornLayer_Slim);
        }

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(EchoModel.LAYER_LOCATION, EchoModel::createBodyLayer);
            event.registerLayerDefinition(CompassModel.LAYER_LOCATION, CompassModel::createBodyLayer);
            event.registerLayerDefinition(RuneModel.LAYER_LOCATION, RuneModel::createBodyLayer);
            event.registerLayerDefinition(PumkinModel.LAYER_LOCATION, PumkinModel::createBodyLayer);
            event.registerLayerDefinition(DragonSlayerModel.LAYER_LOCATION, DragonSlayerModel::createBodyLayer);

            event.registerLayerDefinition(FloElytraLayer.WINGS_LOCATION, FloElytraModel::createLayer);
            event.registerLayerDefinition(GhostElytraLayer.WINGS_LOCATION, GhostElytraModel::createLayer);
            event.registerLayerDefinition(WhirlElytraLayer.WINGS_LOCATION, WhirlElytraModel::createLayer);
            event.registerLayerDefinition(TricoElytraLayer.WINGS_LOCATION, TricoElytraModel::createLayer);
            event.registerLayerDefinition(RaccGoatHornsLayer.LAYER_LOCATION, RaccGoatHornsModel::createLayer);
            event.registerLayerDefinition(KyoUnicronHornLayer.LAYER_LOCATION, KyoUnicornHornModel::createLayer);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {

            event.register(KeyInit.ABILITY_1);
            event.register(KeyInit.ABILITY_2);
            event.register(KeyInit.ABILITY_3);
            event.register(KeyInit.PET_MENU);
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            //Pets
            event.registerEntityRenderer(EntityInit.ECHO.get(), EchoRenderer::new);
            event.registerEntityRenderer(EntityInit.COMPASS.get(), CompassRenderer::new);
            event.registerEntityRenderer(EntityInit.RUNE.get(), RuneRenderer::new);
            event.registerEntityRenderer(EntityInit.PUMKIN.get(), PumkinRenderer::new);
            event.registerEntityRenderer(EntityInit.DRAGON_SLAYER.get(), DragonSlayerRenderer::new);

            //Cybertron
            event.registerEntityRenderer(EntityInit.CYBERTRON_PIG.get(), CybertronPigRenderer::new);
            event.registerEntityRenderer(EntityInit.CYBERTRON_CHICKEN.get(), CybertronChickenRenderer::new);
            event.registerEntityRenderer(EntityInit.CYBERTRON_COW.get(), CybertronCowRenderer::new);
            event.registerEntityRenderer(EntityInit.TEN.get(), TenRenderer::new);
            event.registerEntityRenderer(EntityInit.CYBERTRON_HORSE.get(), CybertronHorseRenderer::new);

            event.registerEntityRenderer(EntityInit.SCRAPLET.get(), ScrapletRenderer::new);
            event.registerEntityRenderer(EntityInit.CYBERTRON_CREEPER.get(), CybertronCreeperRenderer::new);
            event.registerEntityRenderer(EntityInit.CYBERTRON_HOSTILE_COW.get(), CybertronHostileCowRenderer::new);

            //Bosses
            event.registerEntityRenderer(EntityInit.SCRAPLET_BOSS.get(), ScrapletBossRenderer::new);
            event.registerEntityRenderer(EntityInit.BETA_SCRAPLET.get(), BetaScrapletRenderer::new);

            //NPC
            event.registerEntityRenderer(EntityInit.NPC_NIMBUS.get(), NimbusNPCRenderer::new);
            event.registerEntityRenderer(EntityInit.NPC_BOO.get(), BooNPCRenderer::new);
            event.registerEntityRenderer(EntityInit.NPC_SMUDGE.get(), SmudgeNPCRenderer::new);
        }
    }
}
