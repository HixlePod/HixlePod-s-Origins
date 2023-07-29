package com.hixlepod.hixlepodsorigins.common.Effect;

import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.core.init.EffectsInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.api.ScaleTypes;

public class GiantEffect extends MobEffect {

    private final float Amplitude = 2;
    private boolean changed = false;

    public GiantEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int Amplifier) {

        if (entity instanceof Player) {

            Player player = (Player) entity;

            float height = ScaleTypes.HEIGHT.getScaleData(entity).getScale();
            float width = ScaleTypes.WIDTH.getScaleData(entity).getScale();

            if (entity.getEffect(EffectsInit.GIANT.get()).getDuration() <= 1) {
                ScaleData heightScaleData = ScaleTypes.HEIGHT.getScaleData(entity);
                heightScaleData.setScaleTickDelay(20 * 4);
                heightScaleData.setScale(height / (((float) Amplifier / Amplitude) + 1f));

                ScaleData widthScaleData = ScaleTypes.HEIGHT.getScaleData(entity);
                widthScaleData.setScaleTickDelay(20 * 4);
                widthScaleData.setScale(width / (((float) Amplifier / Amplitude) + 1f));

                changed = false;

            } else {
                if (!changed) {
                    ScaleData heightScaleData = ScaleTypes.HEIGHT.getScaleData(entity);
                    heightScaleData.setScaleTickDelay(20 * 4);
                    heightScaleData.setScale(height * (((float) Amplifier / Amplitude) + 1f));

                    ScaleData widthScaleData = ScaleTypes.HEIGHT.getScaleData(entity);
                    widthScaleData.setScaleTickDelay(20 * 4);
                    widthScaleData.setScale(width * (((float) Amplifier / Amplitude) + 1f));



                    changed = true;
                }
            }
        }
        super.applyEffectTick(entity, Amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
