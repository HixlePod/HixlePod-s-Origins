package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.BlockInit;
import com.hixlepod.hixlepodsorigins.core.init.CommandsInnit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GameplayEvents {

    static String[] ROBOTS = {HixlePod.NAME, AmbrosiaElf.NAME, Blakpaw2244.NAME, Kira_uwu69.NAME, Folf_Gaming.NAME};


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEatStartEvent(LivingEntityUseItemEvent.Start event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (Arrays.asList(ROBOTS).contains(player.getName().getString())) {
                if (Arrays.asList(FoodLists.TRANSFORMER_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }

            }

            if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
                if (Arrays.asList(FoodLists.DRAGONS_BANNED_FOOD).contains(player.getMainHandItem().getItem())) {

                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
                if (Arrays.asList(FoodLists.KYOWING_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
                if (Arrays.asList(FoodLists.GHOSTLURE_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            } else {
                if (player.getMainHandItem().getItem().equals(ItemInit.BLOOD_BONE.get())) {
                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                if (Arrays.asList(FoodLists.ANIRIAL_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            } else {
                if (Arrays.asList(FoodLists.GOAT_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEatFinishEvent(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (!Arrays.asList(ROBOTS).contains(player.getName().getString())) {
                if (Arrays.asList(FoodLists.TRANSFORMER_FOODS).contains(player.getMainHandItem().getItem())) {

                    OriginsDamageSource.hurt(player, 3000f, OriginsDamageSource.ENERGON_POISONING);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootLoad(LivingDropsEvent event) {
       if (event.getEntity() instanceof Skeleton || event.getEntity() instanceof Zombie ||
               event.getEntity() instanceof WitherSkeleton || event.getEntity() instanceof Creeper) {

           event.getDrops().add(new ItemEntity(event.getEntity().getLevel(),
                   event.getEntity().position().x(),
                   event.getEntity().position().y(),
                   event.getEntity().position().z(), new ItemStack(ItemInit.BLOOD_BONE.get())));
       }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getPlacedBlock().getBlock() == BlockInit.GROUND_BRIDGE_BLOCK.get()) {
                if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_HasGroundBridge")) {
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You are either flexing or trying to trap people. nice try."));
                    event.setCanceled(true);
                    return;
                }

                player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_HasGroundBridge", true);

                CompoundTag GroundBridge = new CompoundTag();

                GroundBridge.putDouble("PosX", event.getPos().getX());
                GroundBridge.putDouble("PosY", event.getPos().getY());
                GroundBridge.putDouble("PosZ", event.getPos().getZ());

                player.getPersistentData().put(HixlePodsOrigins.MODID + "_GroundBridgeBlock", GroundBridge);

                player.sendSystemMessage(Component.literal(ChatFormatting.GOLD + "You now have access to Ground Bridge commands, type '/groundbridge HELP' for more information."));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = event.getPlayer();

            if (event.getState().getBlock() == BlockInit.GROUND_BRIDGE_BLOCK.get()) {

                CompoundTag GroundBridge = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeBlock");

                BlockPos blockPos = event.getPos();

                if (blockPos.getX() == GroundBridge.getDouble("PosX") &&
                        blockPos.getY() == GroundBridge.getDouble("PosY") &&
                        blockPos.getZ() == GroundBridge.getDouble("PosZ")) {
                    player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_HasGroundBridge", false);
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You no longer have access to ground bridge commands."));
                } else {
                    player.sendSystemMessage(Component.literal(ChatFormatting.RED + "You cannot break other people's ground bridges."));
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void DisplayName(PlayerEvent.NameFormat event) {
        Player player = event.getEntity();

        String name = player.getPersistentData().getString(HixlePodsOrigins.MODID + "_CustomDisplayName");

        if (!name.isEmpty()) {
            event.getEntity().setCustomName(Component.literal(name));
            event.setDisplayname(Component.literal(name));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void WanderingTraderTrades(WandererTradesEvent event) {
        event.getGenericTrades().clear();
        event.getRareTrades().clear();


        //Common Trades

        //Rare Items
        event.getGenericTrades().add(Trade(new ItemStack(Items.IRON_INGOT, OriginsUtil.randomInt(6, 11)), new ItemStack(Items.ENDER_PEARL, OriginsUtil.randomInt(2, 4)), 16, 10));
        event.getGenericTrades().add(Trade(new ItemStack(Items.IRON_INGOT, OriginsUtil.randomInt(6, 11)), new ItemStack(Items.SPORE_BLOSSOM, 1), 16, 10));


        //Epic Items
        event.getGenericTrades().add(Trade(new ItemStack(Items.EMERALD, OriginsUtil.randomInt(6, 11)), new ItemStack(Items.NAME_TAG, 1), 4, 20));
        event.getGenericTrades().add(Trade(new ItemStack(Items.EMERALD, OriginsUtil.randomInt(6, 11)), new ItemStack(Items.SADDLE, 1), 4, 20));

        event.getGenericTrades().add(Trade(new ItemStack(Items.EMERALD, OriginsUtil.randomInt(7, 21)), new ItemStack(Items.MUSIC_DISC_PIGSTEP, 1), 4, 20));
        event.getGenericTrades().add(Trade(new ItemStack(Items.EMERALD,1), new ItemStack(Items.EXPERIENCE_BOTTLE, 1), 64, 20));

        event.getGenericTrades().add(Trade(new ItemStack(Items.EMERALD, OriginsUtil.randomInt(23, 39)), new ItemStack(Items.GOLDEN_APPLE, 1), 16, 20));
        event.getGenericTrades().add(Trade(new ItemStack(Items.EMERALD, OriginsUtil.randomInt(23, 39)), new ItemStack(ItemInit.SYNTH_EN_CUBE.get(), 1), 16, 20));


        //Legendary Items
        event.getGenericTrades().add(Trade(new ItemStack(Items.DIAMOND, 5), new ItemStack(Items.ANCIENT_DEBRIS, 2), new ItemStack(Items.NETHERITE_SCRAP, 5), 1, 40));

        event.getGenericTrades().add(Trade(new ItemStack(Items.DIAMOND, OriginsUtil.randomInt(17, 25)), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1), 1, 20));
        event.getGenericTrades().add(Trade(new ItemStack(Items.DIAMOND, OriginsUtil.randomInt(17, 25)), new ItemStack(ItemInit.DARK_ENERGON_CUBE.get(), 1), 1, 20));


        //Troll Items
        event.getRareTrades().add(Trade(new ItemStack(Items.BEDROCK, 1), new ItemStack(Items.DRAGON_EGG, 1), 1, 9999));
    }

    private static BasicItemListing Trade(ItemStack wanted, ItemStack offer, int maxTrades, int xp) {
        return new BasicItemListing(wanted, offer, maxTrades, xp, 10);
    }

    private static BasicItemListing Trade(ItemStack wanted, ItemStack wanted1, ItemStack offer, int maxTrades, int xp) {
        return new BasicItemListing(wanted, wanted1, offer, maxTrades, xp, 10);
    }

    /*
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void replaceVanillaSnowball(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level world = event.getLevel();

        if (entity instanceof WanderingTrader) {

            Entity CUSTOM_TRADER = EntityInit.CUSTOM_TRADER.get().create(world);

            world.addFreshEntity(CUSTOM_TRADER);

            CUSTOM_TRADER.moveTo(entity.position());

            entity.remove(Entity.RemovalReason.DISCARDED);
        }
    }

     */
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        CommandsInnit.Register(event.getDispatcher());
    }
}
