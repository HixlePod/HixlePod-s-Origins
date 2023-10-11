package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabsInit {


    public static final DeferredRegister<CreativeModeTab> ORIGINS_CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HixlePodsOrigins.MODID);


    public static final RegistryObject<CreativeModeTab> ORIGINS_TAB = ORIGINS_CREATIVE_TABS.register("hixlepods_origins_main_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemInit.THE_HOLY_LYRE.get()))
                    .title(Component.literal("HixlePod's Origins: Main"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ItemInit.EXAMPLE_ITEM.get());
                        output.accept(ItemInit.THE_HOLY_LYRE.get());
                        output.accept(ItemInit.GROUND_BRIDGE_BLOCK_ITEM.get());
                        output.accept(ItemInit.ENERGOM_LIGHTER.get());

                        //Energon stuffies
                        output.accept(ItemInit.ENERGON_DECORATION_BLOCK_ITEM.get());
                        output.accept(ItemInit.SYNTH_EN_DECORATION_BLOCK_ITEM.get());
                        output.accept(ItemInit.DARK_ENERGON_DECORATION_BLOCK_ITEM.get());
                        output.accept(ItemInit.RED_ENERGON_DECORATION_BLOCK_ITEM.get());

                        output.accept(ItemInit.ENERGON_ORE_BLOCK_ITEM.get());
                        output.accept(ItemInit.SYNTH_EN_ORE_ITEM.get());
                        output.accept(ItemInit.DARK_ENERGON_ORE_ITEM.get());
                        output.accept(ItemInit.RED_ENERGON_ORE_ITEM.get());

                        output.accept(ItemInit.UNREFINED_ENERGON.get());
                        output.accept(ItemInit.UNREFINED_SYNTH_EN.get());
                        output.accept(ItemInit.UNREFINED_DARK_ENERGON.get());
                        output.accept(ItemInit.UNREFINED_RED_ENERGON.get());

                        output.accept(ItemInit.REFINED_ENERGON.get());
                        output.accept(ItemInit.REFINED_SYNTH_EN.get());
                        output.accept(ItemInit.REFINED_DARK_ENERGON.get());
                        output.accept(ItemInit.REFINED_RED_ENERGON.get());

                        output.accept(ItemInit.ENERGON_CUBE.get());
                        output.accept(ItemInit.SYNTH_EN_CUBE.get());
                        output.accept(ItemInit.DARK_ENERGON_CUBE.get());
                        output.accept(ItemInit.RED_ENERGON_CUBE.get());

                        output.accept(ItemInit.ENERGON_BITS.get());
                        output.accept(ItemInit.SYNTH_EN_BITS.get());
                        output.accept(ItemInit.DARK_ENERGON_BITS.get());
                        output.accept(ItemInit.RED_ENERGON_BITS.get());

                        output.accept(ItemInit.ENERGON_PIZZA.get());
                        output.accept(ItemInit.ENERJOLLY.get());
                        output.accept(ItemInit.HYPER_ENERJOLLY.get());
                        output.accept(ItemInit.BATTLE_DONUT.get());

                        output.accept(ItemInit.ENERGON_LOW_GRADE_DRINK.get());

                        output.accept(ItemInit.ENERGON_MID_GRADE_DRINK.get());
                        output.accept(ItemInit.SYNTH_EN_MID_GRADE_DRINK.get());
                        output.accept(ItemInit.DARK_MID_GRADE_DRINK.get());
                        output.accept(ItemInit.RED_MID_GRADE_DRINK.get());

                        output.accept(ItemInit.ENERGON_HIGH_GRADE_DRINK.get());
                        output.accept(ItemInit.DARK_HIGH_GRADE_DRINK.get());

                        output.accept(ItemInit.AMBERGON_BUCKET.get());
                        output.accept(ItemInit.AMBERGON_CREAM.get());

                        output.accept(ItemInit.RUST.get());

                        //Other origins
                        output.accept(ItemInit.SUGAR_CUBE.get());

                        output.accept(ItemInit.BLOOD_BONE.get());
                        output.accept(ItemInit.BATTLE_BURRITO.get());

                        //Weapons
                        output.accept(ItemInit.THE_STRINGLESS.get());
                        output.accept(ItemInit.BETSY_BATTLE_AXE.get());
                        output.accept(ItemInit.WHISPERS_HAMMER.get());

                        output.accept(ItemInit.DRAGON_SCALE.get());

                        output.accept(ItemInit.ORE_TRACKER.get());

                        output.accept(ItemInit.BAG_OF_CAT_FOOD.get());

                        output.accept(ItemInit.PAN.get());

                        output.accept(ItemInit.PASSPORT.get());

                        output.accept(ItemInit.MYSTERY_BUNDLE.get());

                        //Currency
                        output.accept(ItemInit.BRONZE_YARN.get());
                        output.accept(ItemInit.SILVER_YARN.get());
                        output.accept(ItemInit.GOLD_YARN.get());
                        output.accept(ItemInit.PLATINUM_YARN.get());
                        output.accept(ItemInit.DIAMOND_YARN.get());

                        output.accept(ItemInit.BRONZE_TROPHY.get());
                        output.accept(ItemInit.SILVER_TROPHY.get());
                        output.accept(ItemInit.GOLD_TROPHY.get());

                        //Cursed
                        output.accept(ItemInit.NINJA.get());
                        output.accept(ItemInit.WEED.get());
                        output.accept(ItemInit.MAXWELL.get());
                        output.accept(ItemInit.AMONG_US.get());
                        output.accept(ItemInit.AZZIP_NOGRENE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ORIGINS_COMPONENT_TAB = ORIGINS_CREATIVE_TABS.register("hixlepods_origins_component_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ItemInit.THE_HOLY_LYRE.get()))
                    .title(Component.literal("HixlePod's Origins: Components"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ComponentModuleItems.ELECTRONIC_SCRAP.get());
                        output.accept(ComponentModuleItems.SIMPLE_COMPONENT.get());
                        output.accept(ComponentModuleItems.COMMS.get());
                    })
                    .build());
}
