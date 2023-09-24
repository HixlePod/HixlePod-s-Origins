package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ComponentModuleItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HixlePodsOrigins.MODID);

    //TODO: Add anything you can think of out of your ass. Perhaps random letters and characters for each component and modal.

    public static final RegistryObject<Item> ELECTRONIC_SCRAP = ITEMS.register("electronic_scrap", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIMPLE_COMPONENT = ITEMS.register("simple_component", () -> new Item(new Item.Properties()));


}
