package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.caelus.api.CaelusApi;
import virtuoel.pehkui.api.ScaleTypes;

public class Flo_Plays_ extends Origin {

    public static String NAME = "Flo_Plays_";

    public static void setStats(Player player) {
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).setBaseValue(0.16);
        player.getAttribute(CaelusApi.getInstance().getFlightAttribute()).setBaseValue(1);

        ScaleTypes.HEIGHT.getScaleData(player).setScale(1.05f);
    }

    public static void Ability1(ServerPlayer player) {
        player.hurtMarked = true;
        player.setDeltaMovement(player.getDeltaMovement().add(0,0.5 * 5,0));
    }

    public static void Ability2(ServerPlayer player) {

        switch (OriginsUtil.randomInt(1, 3)) {
            case 1:
                sound(player, SoundEvents.GENERIC_EXPLODE);
                break;

            case 2:
                sound(player, SoundEvents.WARDEN_EMERGE);
                break;

            case 3:
                sound(player, SoundEvents.ENDER_DRAGON_GROWL);
                break;
        }
    }

    public static void sound(Player player, SoundEvent soundEvent) {
        for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
            serverPlayer.level().playSound(null, player.position().x, player.position().y, player.position().z, soundEvent, SoundSource.PLAYERS, 1, 1);
        }
    }
}
