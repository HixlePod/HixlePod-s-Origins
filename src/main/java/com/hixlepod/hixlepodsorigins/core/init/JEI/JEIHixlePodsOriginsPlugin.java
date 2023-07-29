package com.hixlepod.hixlepodsorigins.core.init.JEI;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@Deprecated
@JeiPlugin
public class JEIHixlePodsOriginsPlugin implements IModPlugin {


    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(HixlePodsOrigins.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

    }
}
