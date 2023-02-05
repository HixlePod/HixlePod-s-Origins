package com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys;

import com.hixlepod.hixlepodsorigins.common.origins.J_Curve;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.UUID;
import java.util.function.Supplier;

import static com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys.GodOfFurrys.laser_distance;

public class SpawnParticleS2CPacket {

    public SpawnParticleS2CPacket() {

    }

    public SpawnParticleS2CPacket(FriendlyByteBuf buf) {

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


        Player player = Minecraft.getInstance().player.clientLevel.getPlayerByUUID(UUID.fromString("0db8022a-da29-4845-9831-341dfffbc1e6"));

        Level level = player.level;

        Vec3 look = player.position().add(0, 1.4, 0);

        for (int i = 0; i < laser_distance; i++) {
            look = look.add(player.getLookAngle().x(), player.getLookAngle().y(), player.getLookAngle().z());

            level.addParticle(new DustParticleOptions(new Vector3f(0, 0,0), 1), look.x(), look.y(), look.z(), 0d, 0d, 0d);
        }
    }
}
