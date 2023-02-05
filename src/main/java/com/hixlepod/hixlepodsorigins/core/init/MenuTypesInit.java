package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.screen.GroundBridgeScreen.GroundBridgeMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesInit {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, HixlePodsOrigins.MODID);

    public static final RegistryObject<MenuType<GroundBridgeMenu>> GROUND_BRIDGE_MENU =
            registerMenuTypes(GroundBridgeMenu::new, "ground_bridge_menu");

    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuTypes(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
