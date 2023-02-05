package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid.CrispyChordioid;
import com.hixlepod.hixlepodsorigins.common.origins.Electrum_Star.Electrum_Star;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys.GodOfFurrys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class OriginsManager {

    public static void setOriginStats(Player player) {

        if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
            AmbrosiaElf.setStats(player);

        } else if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
            CrispyChordioid.setStats(player);

        } else if (player.getName().equals(Component.literal(Fudge105.NAME))) {
            Fudge105.setStats(player);

        } else if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
            GodOfFurrys.setStats(player);

        } else if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
            Blakpaw2244.setStats(player);

        } else if (player.getName().equals(Component.literal(HixlePod.NAME))) {
            HixlePod.setStats(player);

        } else if (player.getName().equals(Component.literal(Toupster02.NAME))) {
            Toupster02.setStats(player);

        } else if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
            Electrum_Star.setStats(player);

        } else if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
            Flo_Plays_.setStats(player);

        } else if (player.getName().equals(Component.literal(Aniriai.NAME))) {
            Aniriai.setStats(player);

        } else if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
            KyoWing3809.setStats(player);

        } else if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
            gh0stlure.setStats(player);

        } else if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            CatGirlSeeka.setStats(player);

        } else if (player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
            ArtificalMemes.setStats(player);

        } else if (player.getName().equals(Component.literal(matt4tea.NAME))) {
            matt4tea.setStats(player);

        }
    }

    public static void setAbilityData(Player player) {
        if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
            Electrum_Star.setAbilityData(player);

        }
        if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            CatGirlSeeka.setAbilityData(player);

        }

        if (player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
            ArtificalMemes.setAbilityData(player);

        }
    }

    public static int ticks = 40;

    public static int actualTicks = 20;

    public static int returnAbilityMaxCooldown1(Player player) {

        if (player.getName().equals(Component.literal("non"))) {
            return 5 * ticks;
        }
        else if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
            return 60 * ticks;

        } else if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
            return 15 * ticks;

        } else if (player.getName().equals(Component.literal(Fudge105.NAME))){
            return 80 * ticks;

        } else if (player.getName().equals(Component.literal(AllyIsAngy.NAME))) {
            return 80 * ticks;

        } else if (player.getName().equals(Component.literal(undramaticc.NAME))) {
            return 65 * ticks;

        } else if (player.getName().equals(Component.literal(J_Curve.NAME))) {
            return 120 * ticks;

        } else if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
            return 1 * ticks;

        } else if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
            return 100 * ticks;

        } else if (player.getName().equals(Component.literal(HixlePod.NAME))) {
            return 150 * ticks;

        } else if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
            return 8;

        } else if (player.getName().equals(Component.literal(Maxwell.NAME))) {
            return 8;

        } else if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
            return 15 * ticks;

        } else if (player.getName().equals(Component.literal(Aniriai.NAME))) {
            return 40 * ticks;

        } else if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
            return 15 * ticks;

        } else if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            return 8;

        } else if (player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
            return 8;

        } else if (player.getName().equals(Component.literal(matt4tea.NAME))) {
            return 70 * ticks;

        }

        return -1;
    }

    public static int returnAbilityMaxCooldown2(Player player) {

        if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
            return 15 * ticks;

        } else if (player.getName().equals(Component.literal(Fudge105.NAME))){
            return 60 * ticks;

        } else if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
            return 150 * ticks;

        } else if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
            return 5 * ticks;

        } else if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
            return 60 * ticks;

        } else if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
            return 25 * ticks;

        } else if (player.getName().equals(Component.literal(Aniriai.NAME))) {
            return 40 * ticks;

        } else if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
            return 100 * ticks;

        } else if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
            return 60 * ticks;

        } else if (player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
            return 60 * ticks;

        }

        return -1;
    }
}
