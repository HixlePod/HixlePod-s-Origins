package com.hixlepod.hixlepodsorigins.mixin;

import com.hixlepod.hixlepodsorigins.access.EntityCollisionContextAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCollisionContext.class)
public class EntityShapeContextMixin implements EntityCollisionContextAccess {

    private Entity entity;

    @Inject(at = @At("TAIL"), method = " <init>(Lnet/minecraft/world/entity/Entity;)V")
    private void setEntityField(net.minecraft.world.entity.Entity entity, CallbackInfo ci) {
        this.entity = entity;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}

