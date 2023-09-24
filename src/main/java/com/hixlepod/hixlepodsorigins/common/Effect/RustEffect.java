package com.hixlepod.hixlepodsorigins.common.Effect;

import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.DamageTypes;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class RustEffect extends MobEffect {

    public RustEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int Amplifier) {

        if (!livingEntity.level().isClientSide()) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;

                if (player.getName().equals(Component.literal(HixlePod.NAME))
                        || player.getName().equals(Component.literal(AmbrosiaElf.NAME))
                        || player.getName().equals(Component.literal(Blakpaw2244.NAME))
                        || player.getName().equals(Component.literal(Folf_Gaming.NAME))
                        || player.getName().equals(Component.literal(Kira_uwu69.NAME))) {

                    if (OriginsUtil.didChance(15)) {

                        float TOTAL_DAMAGE = OriginsUtil.randomFloat(0.4f, 3f) * ((float) Amplifier + 1.5f);

                        //OriginsDamageSource.hurt(player, TOTAL_DAMAGE, OriginsDamageSource.RUST);
                        player.hurt(new DamageSource(DamageTypes.RUST.getHolder().get()), TOTAL_DAMAGE);
                    }
                } else {
                    player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 2, Amplifier));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 2, Amplifier));
                }
            }
        }

        super.applyEffectTick(livingEntity, Amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
