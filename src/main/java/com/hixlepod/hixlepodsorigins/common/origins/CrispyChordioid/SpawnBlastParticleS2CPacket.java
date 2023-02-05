package com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid;

import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SpawnBlastParticleS2CPacket {

    public SpawnBlastParticleS2CPacket() {

    }

    public SpawnBlastParticleS2CPacket(FriendlyByteBuf buf) {

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


        Player player = Minecraft.getInstance().player.clientLevel.getPlayerByUUID(UUID.fromString("0a167caf-f3f8-4dd7-9de4-7a9852ecd9ad"));

        Level level = player.level;

        level.addParticle(ParticleTypes.EXPLOSION, player.getX(), player.getY(), player.getZ(), 0D, 0D, 0D);
    }
}
