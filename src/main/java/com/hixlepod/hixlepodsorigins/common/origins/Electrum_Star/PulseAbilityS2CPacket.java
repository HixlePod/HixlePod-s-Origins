package com.hixlepod.hixlepodsorigins.common.origins.Electrum_Star;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.AxisAlignedLinearPosTest;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PulseAbilityS2CPacket {

    public PulseAbilityS2CPacket() {

    }

    public PulseAbilityS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

        supplier.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handlePacket(supplier))
        );
        return true;
    }

    public static void handlePacket(Supplier<NetworkEvent.Context> supplier) {

        for (Entity entity : Minecraft.getInstance().player.clientLevel.getEntities(null, AABB.of(BoundingBox.infinite()))) {
            entity.setGlowingTag(true);
        }
    }
}
