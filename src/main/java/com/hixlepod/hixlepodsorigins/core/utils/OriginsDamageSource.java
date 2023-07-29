package com.hixlepod.hixlepodsorigins.core.utils;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public class OriginsDamageSource {

    public static String RUST = HixlePodsOrigins.MODID + "_rust";
    public static String ACID_RAIN = HixlePodsOrigins.MODID + "_acid_rain";

    public static String ENERGON_POISONING = HixlePodsOrigins.MODID + "_energon_poisoning";

    public static String EXTINGUISH = HixlePodsOrigins.MODID + "_extinguish";

    public static String ANEMO_VORTEXT = HixlePodsOrigins.MODID + "_anema_vortext";

    public static String SCULK_DRAIN = HixlePodsOrigins.MODID + "_sculk_drain";

    public static void hurt(Entity entity, float Damage, String Source) {
        DamageSource damageSource = new DamageSource(Source);
        damageSource.bypassArmor();
        entity.hurt(damageSource, Damage);
    }

}
