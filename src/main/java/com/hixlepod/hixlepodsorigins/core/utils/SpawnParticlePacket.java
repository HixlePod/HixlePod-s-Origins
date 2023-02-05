package com.hixlepod.hixlepodsorigins.core.utils;

import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

import static com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys.GodOfFurrys.laser_distance;

public class SpawnParticlePacket {

    static Vec3 location;
    static ParticleOptions particle;

    public SpawnParticlePacket(Vec3 vec3, ParticleOptions particleOptions) {
        this.location = vec3;
        this.particle = particleOptions;
    }

    public SpawnParticlePacket(FriendlyByteBuf buf) {

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

        Minecraft.getInstance().player.level.addParticle(particle, location.x(), location.y(), location.z(), 0d,0d,0d);
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("" + location + " : " + particle));
    }
}
