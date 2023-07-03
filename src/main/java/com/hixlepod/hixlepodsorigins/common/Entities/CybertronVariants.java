package com.hixlepod.hixlepodsorigins.common.Entities;

import net.minecraft.world.entity.animal.horse.Variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CybertronVariants {

    AQUAMARINE(0),
    AMETHYST(1),
    EMERALD(2),
    JASPER(3),
    QUARTZ(4),
    LAPIS(5),
    RUBY(6),
    TOPAZ(7),
    DIAMOND(8);


    private static final CybertronVariants[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(CybertronVariants::getId)).toArray((p_30989_) -> {
        return new CybertronVariants[p_30989_];
    });
    private final int id;

    private CybertronVariants(int p_30984_) {
        this.id = p_30984_;
    }

    public int getId() {
        return this.id;
    }

    public static CybertronVariants byId(int p_30987_) {
        return BY_ID[p_30987_ % BY_ID.length];
    }
}

