package com.hixlepod.hixlepodsorigins;

import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.BlockStore;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.BlockStoreBuilder;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.proxy.ClientProxy;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.proxy.CommonProxy;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.proxy.ServerProxy;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking.xray.Controller;
import com.hixlepod.hixlepodsorigins.common.PotionRecipes.*;
import com.hixlepod.hixlepodsorigins.common.events.*;
import com.hixlepod.hixlepodsorigins.core.init.*;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import sereneseasons.init.ModCreativeTab;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(HixlePodsOrigins.MODID)
public class HixlePodsOrigins
{
    public static final String MODID = "hixlepodsorigins";

    private static final String ORIGINS_VERSION = "0.9.5";
    private static final String ORIGINS_BUILD_VERSION = "BUILD-14";

    public static final String MOD_VER = ORIGINS_VERSION + " - " + ORIGINS_BUILD_VERSION;

    public static final String WINDOW_TITLE = "Minecraft* 1.20.1: HixlePod's Origins " + MOD_VER;


    public static HixlePodsOrigins MOD_INSTANCE;

    public static BlockStore blockStore = new BlockStore();
    public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public HixlePodsOrigins() {
        MOD_INSTANCE = this;

        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Register Inits
        ItemInit.ITEMS.register(eventBus);
        ComponentModuleItems.ITEMS.register(eventBus);
        CreativeTabsInit.ORIGINS_CREATIVE_TABS.register(eventBus);

        BlockInit.BLOCKS.register(eventBus);
        SoundInit.SOUND_EVENTS.register(eventBus);
        //ConfiguredFeatureInit.CONFIGURED_FEATURES.register(eventBus);
        //PlacedFeatureInit.PLACED_FEATURES.register(eventBus);
        EntityInit.ENTITIES.register(eventBus);
        POIInit.POI.register(eventBus);
        EffectsInit.MOB_EFFECTS.register(eventBus);
        PotionInit.POTIONS.register(eventBus);
        LootModifiersInit.LOOT_MODIFIER_SERIALIZERS.register(eventBus);

        BlockInit.VANILLA_BLOCKS.register(eventBus);
        ItemInit.VANILLA_ITEMS.register(eventBus);

        BlockStoreBuilder.init();

        //Event listeners
        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::enqueue);

        MinecraftForge.EVENT_BUS.register(new ClientModEvents());
        MinecraftForge.EVENT_BUS.register(new ServerModEvents());
        MinecraftForge.EVENT_BUS.register(new CommonModEvents());
        MinecraftForge.EVENT_BUS.register(new GameplayEvents());
        MinecraftForge.EVENT_BUS.register(PlayerEvents.class);
        MinecraftForge.EVENT_BUS.register(CompassTracking.class);

        //Register events on the client
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.register(new DingClientEvent());
        });
    }

    public static class PlayerEvents {
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onPlayerLogOut(PlayerEvent.PlayerLoggedOutEvent event) {
            if (Controller.drawOres()) {
                Controller.toggleDrawOres();
            }
            Controller.shutdownExecutor();
        }

    }


    private void onCommonSetup(final FMLCommonSetupEvent event) {
        NetworkManager.Register();
        proxy.init();

        event.enqueueWork(() -> {

            //OTHER POTIONS
            BrewingRecipeRegistry.addRecipe(new FreezePotionRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS)), Ingredient.of(Items.BLUE_ICE), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.FREEZE_POTION.get())));
            BrewingRecipeRegistry.addRecipe(new BlindnessPotionRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION)), Ingredient.of(Items.INK_SAC), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.BLINDNESS_POTION.get())));

            //CYBERTRON RELATED POTIONS
            BrewingRecipeRegistry.addRecipe(new AmbergonPotionRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(ItemInit.AMBERGON_BUCKET.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.AMBERGON_POTION.get())));

            BrewingRecipeRegistry.addRecipe(new RustPotionRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.AMBERGON_POTION.get())), Ingredient.of(ItemInit.RUST.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.RUST_POTION.get())));
            BrewingRecipeRegistry.addRecipe(new MalfunctionPotionRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.AMBERGON_POTION.get())), Ingredient.of(ItemInit.DARK_HIGH_GRADE_DRINK.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.MALFUNCTION_POTION.get())));

            //Goofy potions
            BrewingRecipeRegistry.addRecipe(new ChaosPotionRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.AMBERGON_POTION.get())), Ingredient.of(Items.BEDROCK), PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.CHAOS_POTION.get())));


            replaceAttributeValue((RangedAttribute) Attributes.MAX_HEALTH, 9999999); // 9,999,999
        });
    }

    protected static void replaceAttributeValue(RangedAttribute attribute, double maxValue) {
        attribute.maxValue = maxValue;
    }

    //Curios
    private void enqueue(final InterModEnqueueEvent event) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().size(2).build());
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().size(1).build());
    }
}
