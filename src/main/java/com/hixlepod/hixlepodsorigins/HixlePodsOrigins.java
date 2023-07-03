package com.hixlepod.hixlepodsorigins;

import com.hixlepod.hixlepodsorigins.common.events.*;
import com.hixlepod.hixlepodsorigins.core.init.*;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.utils.BetterBrewingRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HixlePodsOrigins.MODID)
public class HixlePodsOrigins
{
    public static final String MODID = "hixlepodsorigins";

    public HixlePodsOrigins() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Register Inits
        ItemInit.ITEMS.register(eventBus);
        BlockInit.BLOCKS.register(eventBus);
        SoundInit.SOUND_EVENTS.register(eventBus);
        ConfiguredFeatureInit.CONFIGURED_FEATURES.register(eventBus);
        PlacedFeatureInit.PLACED_FEATURES.register(eventBus);
        EntityInit.ENTITIES.register(eventBus);
        POIInit.POI.register(eventBus);
        EffectsInit.MOB_EFFECTS.register(eventBus);
        PotionInit.POTIONS.register(eventBus);
        LootModifiersInit.LOOT_MODIFIER_SERIALIZERS.register(eventBus);
        ItemInit.VANILLA_ITEMS.register(eventBus);

        //Event listeners
        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(new ClientModEvents());
        MinecraftForge.EVENT_BUS.register(new ServerModEvents());
        MinecraftForge.EVENT_BUS.register(new CommonModEvents());
        MinecraftForge.EVENT_BUS.register(new GameplayEvents());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NetworkManager.Register();

        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.NIGHT_VISION, Items.INK_SAC, PotionInit.BLINDNESS_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ItemInit.AMBERGON_BUCKET.get(), PotionInit.AMBERGON_POTION.get()));

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(PotionInit.AMBERGON_POTION.get(), ItemInit.DARK_ENERGON_CUBE.get(), PotionInit.MALFUNCTION_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(PotionInit.AMBERGON_POTION.get(), Items.BLUE_ICE, PotionInit.FREEZE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(PotionInit.AMBERGON_POTION.get(), Items.BEDROCK, PotionInit.CHAOS_POTION.get()));

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(PotionInit.AMBERGON_POTION.get(), ItemInit.RUST.get(), PotionInit.RUST_POTION.get()));
        });
    }
}
