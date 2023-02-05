package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.FloElytraLayer;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.GhostElytraLayer;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.FloElytraModel;
import com.hixlepod.hixlepodsorigins.client.OnCharacters.Model.GhostElytraModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.CompassRenderer;
import com.hixlepod.hixlepodsorigins.client.Renderer.EchoRenderer;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CompassModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.EchoModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.PumkinModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.RuneModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.PumkinRenderer;
import com.hixlepod.hixlepodsorigins.client.Renderer.RuneRenderer;
import com.hixlepod.hixlepodsorigins.client.screen.GroundBridgeScreen.GroundBridgeScreen;
import com.hixlepod.hixlepodsorigins.client.screen.LoreMenuScreen;
import com.hixlepod.hixlepodsorigins.client.screen.PetMenuScreen;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.Electrum_Star.Electrum_Star;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.init.KeyInit;
import com.hixlepod.hixlepodsorigins.core.init.MenuTypesInit;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability1C2SPacket;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability2C2SPacket;
import com.hixlepod.hixlepodsorigins.core.networking.packet.ElytraAttemptFlyPacket;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.WardenEmissiveLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.RenderTypeGroup;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.extensions.IForgeKeyMapping;
import net.minecraftforge.client.model.renderable.ITextureRenderTypeLookup;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import org.checkerframework.checker.units.qual.C;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

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
            /*
            if (player.getName().equals(Component.literal(gh0stlure.NAME))) {

                PoseStack poseStack = event.getPoseStack();

                poseStack.pushPose();
                poseStack.mulPoseMatrix(Matrix4f.createTranslateMatrix(0, 1.8f, 0));
                poseStack.mulPose(new Quaternion(Vector3f.XN, -180, true));

                event.getRenderer().getModel().getHead().setRotation(180, 0, 0);
                //poseStack.mulPose(new Quaternion(Vector3f.ZN, -player.yHeadRot, true));

            }

             */
        }

        /*
        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void renderEvent(RenderPlayerEvent.Post event) {
            PoseStack poseStack = event.getPoseStack();
            poseStack.popPose();
        }


        static Item[] Electrum_Star_Banned_Foods = {Items.APPLE, Items.MUSHROOM_STEW, Items.COOKIE, Items.MELON_SLICE, Items.DRIED_KELP, Items.CARROT,
                Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.BEETROOT, Items.BEETROOT_SOUP,
                Items.SWEET_BERRIES, Items.GLOW_BERRIES, Items.HONEY_BOTTLE};

        static Item[] Electrum_Star_Banned_Armour = {Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS,
                Items.NETHERITE_BOOTS};

        static Item[] Transformer_Foods = {Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP,
        Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE,
        Items.MELON_SLICE, Items.DRIED_KELP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
        Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.RABBIT,
        Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP, Items.SWEET_BERRIES,
        Items.GLOW_BERRIES, Items.HONEY_BOTTLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE};

        static Item[] KyoWing_Foods = {Items.MUSHROOM_STEW, Items.PORKCHOP, Items.COOKED_PORKCHOP,
                Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE,
                Items.MELON_SLICE, Items.DRIED_KELP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
                Items.SPIDER_EYE, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.RABBIT,
                Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP, Items.SWEET_BERRIES,
                Items.GLOW_BERRIES, Items.HONEY_BOTTLE, Items.BOW, Items.CROSSBOW, Items.WOODEN_SWORD, Items.IRON_SWORD,
                Items.STONE_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD};

        static Item[] Aniriai_food = {Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH,
                Items.COOKED_COD, Items.COOKED_SALMON, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
                Items.SPIDER_EYE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON};

        static Item[] GOAT_EAT = {ItemInit.CUSTOM_COAL.get(), ItemInit.CUSTOM_CHARCOAL.get(), ItemInit.CUSTOM_DIAMOND.get(), ItemInit.CUSTOM_EMERALD.get(),
                ItemInit.CUSTOM_LAPIS_LAZULI.get(), ItemInit.CUSTOM_RAW_COPPER.get(), ItemInit.CUSTOM_COPPER_INGOT.get(), ItemInit.CUSTOM_RAW_GOLD.get(),
                ItemInit.CUSTOM_GOLD_INGOT.get(), ItemInit.CUSTOM_NETHERITE_INGOT.get(), ItemInit.CUSTOM_NETHERITE_SCRAP.get(), ItemInit.CUSTOM_STICK.get(),
                ItemInit.CUSTOM_RAW_IRON.get(), ItemInit.CUSTOM_IRON_INGOT.get(), ItemInit.CUSTOM_PAPER.get(), ItemInit.CUSTOM_BOOK.get(), ItemInit.CUSTOM_GUNPOWDER.get(),
                ItemInit.CUSTOM_SLIMEBALL.get(), ItemInit.CUSTOM_TOTEM_OF_UNDYING.get(), ItemInit.CUSTOM_BROWN_MUSHROOM.get(), ItemInit.CUSTOM_RED_MUSHROOM.get(),
                ItemInit.CUSTOM_DIRT.get()};

        static Item[] GHOSTLURE_EAT = {Items.MUSHROOM_STEW, Items.BREAD, Items.COOKED_PORKCHOP, Items.COOKED_COD,
        Items.COOKED_SALMON, Items.COOKIE, Items.DRIED_KELP, Items.COOKED_BEEF, Items.COOKED_CHICKEN,
        Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.PUMPKIN_PIE, Items.COOKED_RABBIT, Items.RABBIT_STEW,
        Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP};

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onEatEvent(LivingEntityUseItemEvent.Start event) {

            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                if (player.getName().equals(Component.literal(Electrum_Star.NAME)) ||
                        player.getName().equals(Component.literal(CatGirlSeeka.NAME)) ||
                        player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
                    if (Arrays.asList(Electrum_Star_Banned_Foods).contains(player.getMainHandItem().getItem())) {

                        event.setCanceled(true);
                    }

                    if (Arrays.asList(Electrum_Star_Banned_Armour).contains(player.getMainHandItem().getItem())) {
                        event.setCanceled(true);
                    }
                }


                if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
                    if (Arrays.asList(KyoWing_Foods).contains(player.getMainHandItem().getItem())) {
                        event.setCanceled(true);
                    }
                }

                if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
                    if (Arrays.asList(GHOSTLURE_EAT).contains(player.getMainHandItem().getItem())) {
                        event.setCanceled(true);
                    }
                }

                if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                    if (Arrays.asList(Aniriai_food).contains(player.getMainHandItem().getItem())) {
                        event.setCanceled(true);
                    }
                }

                if (!player.getName().equals(Component.literal(Aniriai.NAME))) {
                    if (Arrays.asList(GOAT_EAT).contains(player.getMainHandItem().getItem())) {
                        event.setCanceled(true);
                    }
                }

                if (player.getName().equals(Component.literal(HixlePod.NAME))
                        || player.getName().equals(Component.literal(AmbrosiaElf.NAME))
                        || player.getName().equals(Component.literal(Blakpaw2244.NAME))) {

                    if (Arrays.asList(Transformer_Foods).contains(player.getMainHandItem().getItem())) {
                        event.setCanceled(true);
                    }

                } else {
                    if (player.getMainHandItem().getItem().equals(ItemInit.ENERGON_CUBE.get())
                            || player.getMainHandItem().getItem().equals(ItemInit.SYNTH_EN_CUBE.get())
                            || player.getMainHandItem().getItem().equals(ItemInit.DARK_ENERGON_CUBE.get())) {
                        event.setCanceled(true);
                    }
                }
            }
        }

         */

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;

            if (player.getName().equals(Component.literal(HixlePod.NAME))) {
                if (player.isInWater()) {
                    if (!player.isCrouching()) {
                        player.setDeltaMovement(player.getDeltaMovement().add(0, 0.025, 0));
                    }
                }
            }

            if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
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

                if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
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

            renderer_normal.addLayer(FloWingsLayer);
            renderer_normal.addLayer(GhostWingsLayer);

            //

            LivingEntityRenderer<Player, PlayerModel<Player>> renderer_slim = event.getSkin("slim");

            FloElytraLayer<Player, PlayerModel<Player>> FloWingsLayer_Slim = new FloElytraLayer<>(renderer_slim, event.getEntityModels());
            GhostElytraLayer<Player, PlayerModel<Player>> GhostWingsLayer_Slim = new GhostElytraLayer<>(renderer_slim, event.getEntityModels());

            renderer_slim.addLayer(FloWingsLayer_Slim);
            renderer_slim.addLayer(GhostWingsLayer_Slim);

        }

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(EchoModel.LAYER_LOCATION, EchoModel::createBodyLayer);
            event.registerLayerDefinition(CompassModel.LAYER_LOCATION, CompassModel::createBodyLayer);
            event.registerLayerDefinition(RuneModel.LAYER_LOCATION, RuneModel::createBodyLayer);
            event.registerLayerDefinition(PumkinModel.LAYER_LOCATION, PumkinModel::createBodyLayer);

            event.registerLayerDefinition(FloElytraLayer.WINGS_LOCATION, FloElytraModel::createLayer);
            event.registerLayerDefinition(GhostElytraLayer.WINGS_LOCATION, GhostElytraModel::createLayer);
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
            event.registerEntityRenderer(EntityInit.ECHO.get(), EchoRenderer::new);
            event.registerEntityRenderer(EntityInit.COMPASS.get(), CompassRenderer::new);
            event.registerEntityRenderer(EntityInit.RUNE.get(), RuneRenderer::new);
            event.registerEntityRenderer(EntityInit.PUMKIN.get(), PumkinRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(MenuTypesInit.GROUND_BRIDGE_MENU.get(), GroundBridgeScreen::new);
        }
    }
}
