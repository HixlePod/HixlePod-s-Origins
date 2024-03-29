package com.hixlepod.hixlepodsorigins.core.networking;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.networking.packet.PotionPacket;
import com.hixlepod.hixlepodsorigins.core.networking.packet.SendPetInfoPacket;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyInvisibilityOFFS2CPacket;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyInvisibilityONS2CPacket;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.FudgeInvisibilityOFFS2CPacket;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.FudgeInvisibilityONS2CPacket;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability1C2SPacket;
import com.hixlepod.hixlepodsorigins.core.networking.packet.Ability2C2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkManager {

    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }

    public static void Register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(HixlePodsOrigins.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(Ability1C2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(Ability1C2SPacket::new)
                .encoder(Ability1C2SPacket::toBytes)
                .consumerMainThread(Ability1C2SPacket::handle)
                .add();

        net.messageBuilder(Ability2C2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(Ability2C2SPacket::new)
                .encoder(Ability2C2SPacket::toBytes)
                .consumerMainThread(Ability2C2SPacket::handle)
                .add();



        net.messageBuilder(FudgeInvisibilityONS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FudgeInvisibilityONS2CPacket::new)
                .encoder(FudgeInvisibilityONS2CPacket::toBytes)
                .consumerMainThread(FudgeInvisibilityONS2CPacket::handle)
                .add();

        net.messageBuilder(FudgeInvisibilityOFFS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FudgeInvisibilityOFFS2CPacket::new)
                .encoder(FudgeInvisibilityOFFS2CPacket::toBytes)
                .consumerMainThread(FudgeInvisibilityOFFS2CPacket::handle)
                .add();

        net.messageBuilder(AllyInvisibilityONS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AllyInvisibilityONS2CPacket::new)
                .encoder(AllyInvisibilityONS2CPacket::toBytes)
                .consumerMainThread(AllyInvisibilityONS2CPacket::handle)
                .add();

        net.messageBuilder(AllyInvisibilityOFFS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AllyInvisibilityOFFS2CPacket::new)
                .encoder(AllyInvisibilityOFFS2CPacket::toBytes)
                .consumerMainThread(AllyInvisibilityOFFS2CPacket::handle)
                .add();

        net.messageBuilder(SendPetInfoPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SendPetInfoPacket::new)
                .encoder(SendPetInfoPacket::toBytes)
                .consumerMainThread(SendPetInfoPacket::handle)
                .add();

        net.messageBuilder(PotionPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PotionPacket::decode)
                .encoder(PotionPacket::encode)
                .consumerMainThread(PotionPacket.Handler::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
