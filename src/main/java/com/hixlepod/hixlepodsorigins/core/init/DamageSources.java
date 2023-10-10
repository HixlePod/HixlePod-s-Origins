package com.hixlepod.hixlepodsorigins.core.init;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class DamageSources {

    private final Registry<DamageType> damageTypes;

    private final DamageSource rust;
    private final DamageSource acid_rain;
    private final DamageSource energon_poisoning;
    private final DamageSource anemo_vortext;
    private final DamageSource extinguish;
    private final DamageSource sculk_drain;


    public DamageSources(RegistryAccess pRegistry) {
        this.damageTypes = pRegistry.registryOrThrow(Registries.DAMAGE_TYPE);

        this.rust = this.source(DamageTypes.RUST);
        this.acid_rain = this.source(DamageTypes.ACID_RAIN);
        this.energon_poisoning = this.source(DamageTypes.ENERGON_POISONING);
        this.anemo_vortext = this.source(DamageTypes.ANEMO_VORTEXT);
        this.extinguish = this.source(DamageTypes.EXTINGUISH);
        this.sculk_drain = this.source(DamageTypes.SCULK_DRAIN);
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey));
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey), pEntity);
    }

    private DamageSource source(ResourceKey<DamageType> pDamageTypeKey, @Nullable Entity pCausingEntity, @Nullable Entity pDirectEntity) {
        return new DamageSource(this.damageTypes.getHolderOrThrow(pDamageTypeKey), pCausingEntity, pDirectEntity);
    }

    //
    public DamageSource rust() {
        return this.rust;
    }

    public DamageSource acid_rain() {
        return this.acid_rain;
    }

    public DamageSource energon_poisoning() {
        return this.energon_poisoning;
    }

    public DamageSource anemo_vortext() {
        return this.anemo_vortext;
    }

    public DamageSource extinguish() {
        return this.extinguish;
    }

    public DamageSource sculk_drain() {
        return this.sculk_drain;
    }
}
