package com.hixlepod.hixlepodsorigins.mixin;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.events.DingClientEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.gui.screens.Overlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class DingOnStartup {

    @Inject(method = "setOverlay", at = @At(value = "HEAD"))
    private void setOverlay(@Nullable Overlay overlay, CallbackInfo callbackInfo) {
        if (DingClientEvent.postInit && HixlePodsOrigins.MOD_INSTANCE != null && overlay == null && ((Minecraft) (Object) this).getOverlay() instanceof LoadingOverlay) {

            DingClientEvent.postInit();
        }
    }
}
