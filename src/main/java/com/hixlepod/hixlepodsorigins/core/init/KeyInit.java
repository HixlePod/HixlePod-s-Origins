package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

import java.security.Key;

public class KeyInit {

    public static final String KEY_CATEGORY = "key.category." + HixlePodsOrigins.MODID;
    public static final String KEY_ABILITY_1 = "key." + HixlePodsOrigins.MODID + ".ability_1";
    public static final String KEY_ABILITY_2 = "key." + HixlePodsOrigins.MODID + ".ability_2";

    public static final String KEY_LORE_MENU = "key." + HixlePodsOrigins.MODID + ".lore_menu";
    public static final String KEY_PET_MENU = "key." + HixlePodsOrigins.MODID + ".pet_menu";

    public static final KeyMapping ABILITY_1 = new KeyMapping(KEY_ABILITY_1, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);
    public static final KeyMapping ABILITY_2 = new KeyMapping(KEY_ABILITY_2, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY);
    public static final KeyMapping LORE_MENU = new KeyMapping(KEY_LORE_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY);
    public static final KeyMapping PET_MENU = new KeyMapping(KEY_PET_MENU, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y, KEY_CATEGORY);

}
