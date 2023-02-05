package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.EntityOverrides.WanderingTraderOverride;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.Electrum_Star.Electrum_Star;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GameplayEvents {

    static Item[] DRAGONS_BANNED_FOOD = {Items.APPLE, Items.MUSHROOM_STEW, Items.COOKIE, Items.MELON_SLICE, Items.DRIED_KELP, Items.CARROT,
            Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.BEETROOT, Items.BEETROOT_SOUP,
            Items.SWEET_BERRIES, Items.GLOW_BERRIES, Items.HONEY_BOTTLE};

    static Item[] KYOWING_BANNED_FOODS = {Items.MUSHROOM_STEW, Items.PORKCHOP, Items.COOKED_PORKCHOP,
            Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE,
            Items.MELON_SLICE, Items.DRIED_KELP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
            Items.SPIDER_EYE, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.RABBIT,
            Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP, Items.SWEET_BERRIES,
            Items.GLOW_BERRIES, Items.HONEY_BOTTLE, Items.BOW, Items.CROSSBOW, Items.WOODEN_SWORD, Items.IRON_SWORD,
            Items.STONE_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD};

    static Item[] GHOSTLURE_BANNED_FOODS = {Items.MUSHROOM_STEW, Items.BREAD, Items.COOKED_PORKCHOP, Items.COOKED_COD,
            Items.COOKED_SALMON, Items.COOKIE, Items.DRIED_KELP, Items.COOKED_BEEF, Items.COOKED_CHICKEN,
            Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.PUMPKIN_PIE, Items.COOKED_RABBIT, Items.RABBIT_STEW,
            Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP};

    static Item[] ANIRIAL_BANNED_FOODS = {Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH,
            Items.COOKED_COD, Items.COOKED_SALMON, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
            Items.SPIDER_EYE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON};

    static Item[] GOAT_EAT = {ItemInit.CUSTOM_COAL.get(), ItemInit.CUSTOM_CHARCOAL.get(), ItemInit.CUSTOM_DIAMOND.get(), ItemInit.CUSTOM_EMERALD.get(),
            ItemInit.CUSTOM_LAPIS_LAZULI.get(), ItemInit.CUSTOM_RAW_COPPER.get(), ItemInit.CUSTOM_COPPER_INGOT.get(), ItemInit.CUSTOM_RAW_GOLD.get(),
            ItemInit.CUSTOM_GOLD_INGOT.get(), ItemInit.CUSTOM_NETHERITE_INGOT.get(), ItemInit.CUSTOM_NETHERITE_SCRAP.get(), ItemInit.CUSTOM_STICK.get(),
            ItemInit.CUSTOM_RAW_IRON.get(), ItemInit.CUSTOM_IRON_INGOT.get(), ItemInit.CUSTOM_PAPER.get(), ItemInit.CUSTOM_BOOK.get(), ItemInit.CUSTOM_GUNPOWDER.get(),
            ItemInit.CUSTOM_SLIMEBALL.get(), ItemInit.CUSTOM_TOTEM_OF_UNDYING.get(), ItemInit.CUSTOM_BROWN_MUSHROOM.get(), ItemInit.CUSTOM_RED_MUSHROOM.get(),
            ItemInit.CUSTOM_DIRT.get()};

    static Item[] TRANSFORMER_BANNED_FOODS = {Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP,
            Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE,
            Items.MELON_SLICE, Items.DRIED_KELP, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH,
            Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.PUMPKIN_PIE, Items.RABBIT,
            Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.BEETROOT, Items.BEETROOT_SOUP, Items.SWEET_BERRIES,
            Items.GLOW_BERRIES, Items.HONEY_BOTTLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE};

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEatEvent(LivingEntityUseItemEvent.Start event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getName().equals(Component.literal(Electrum_Star.NAME)) || player.getName().equals(Component.literal(CatGirlSeeka.NAME)) || player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
                if (Arrays.asList(DRAGONS_BANNED_FOOD).contains(player.getMainHandItem().getItem())) {

                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
                if (Arrays.asList(KYOWING_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
                if (Arrays.asList(GHOSTLURE_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                if (Arrays.asList(ANIRIAL_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            } else {
                if (Arrays.asList(GOAT_EAT).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }
            }

            if (player.getName().equals(Component.literal(HixlePod.NAME)) || player.getName().equals(Component.literal(AmbrosiaElf.NAME)) || player.getName().equals(Component.literal(Blakpaw2244.NAME))) {

                if (Arrays.asList(TRANSFORMER_BANNED_FOODS).contains(player.getMainHandItem().getItem())) {
                    event.setCanceled(true);
                }

            } else {
                if (player.getMainHandItem().getItem().equals(ItemInit.ENERGON_CUBE.get()) || player.getMainHandItem().getItem().equals(ItemInit.SYNTH_EN_CUBE.get()) || player.getMainHandItem().getItem().equals(ItemInit.DARK_ENERGON_CUBE.get())
                 || player.getMainHandItem().getItem().equals(ItemInit.REFINED_ENERGON.get()) || player.getMainHandItem().getItem().equals(ItemInit.REFINED_SYNTH_EN.get()) || player.getMainHandItem().getItem().equals(ItemInit.REFINED_DARK_ENERGON.get())) {
                    event.setCanceled(true);
                }
            }
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
}
