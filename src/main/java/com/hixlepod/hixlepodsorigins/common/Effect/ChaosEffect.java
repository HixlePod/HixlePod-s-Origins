package com.hixlepod.hixlepodsorigins.common.Effect;

import com.hixlepod.hixlepodsorigins.core.init.EffectsInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ChaosEffect extends MobEffect {

    public ChaosEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int Amplifier) {
        if (!livingEntity.level().isClientSide()) {

            if (OriginsUtil.didChance(25)) {

                int random = OriginsUtil.randomInt(1, 13);

                switch (random) {
                    case 1:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 10 * 20, 0, true, false));
                        break;
                    case 2:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10 * 20, 0, true, false));
                        break;
                    case 3:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10 * 20, 0, true, false));
                        break;
                    case 4:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10 * 20, 0, true, false));
                        break;
                    case 5:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10 * 20, 0, true, false));
                        break;
                    case 6:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10 * 20, 0, true, false));
                        break;
                    case 7:
                        livingEntity.addEffect(new MobEffectInstance(EffectsInit.MALFUNCTION.get(), 10 * 20, 0, true, false));
                        break;
                    case 8:
                        livingEntity.addEffect(new MobEffectInstance(EffectsInit.FREEZE.get(), 10 * 20, 0, true, false));
                        break;
                    case 9:
                        livingEntity.addEffect(new MobEffectInstance(EffectsInit.LIGHTNING.get(), 3 * 20, 0, true, false));
                        break;
                    case 10:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 10 * 20, 0, true, false));
                        break;
                    case 11:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 10 * 20, 0, true, false));
                        break;
                    case 12:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 10 * 20, 0, true, false));
                        break;
                    case 13:
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 10 * 20, 0, true, false));
                        break;

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
