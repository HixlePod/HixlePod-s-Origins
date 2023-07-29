package com.hixlepod.hixlepodsorigins.mixin;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class DisableVanillaTitle {

    @Inject(at = @At(value = "HEAD"), method = "updateTitle", cancellable = true)
    private void updateTitle(final CallbackInfo info) {
        info.cancel();

        final Window window = Minecraft.getInstance().getWindow();

        window.setTitle(HixlePodsOrigins.WINDOW_TITLE);
    }
}
