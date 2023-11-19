package com.hixlepod.hixlepodsorigins.common.Entities.Bosses.EnderDragonKing;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EnderKingUtils {


    public static void SummonLightning(Level level, Vec3 Position) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setPos(Position);
        level.addFreshEntity(lightningBolt);
    }

    public static void SummonVisualLightning(Level level, Vec3 Position) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setPos(Position);
        lightningBolt.setVisualOnly(true);
        level.addFreshEntity(lightningBolt);
    }

}
