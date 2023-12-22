package com.hixlepod.hixlepodsorigins.common.Effect;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class AbilityRegenEffect extends MobEffect {

    protected AbilityRegenEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int Amplifier) {
        super.applyEffectTick(livingEntity, Amplifier);

        if (!livingEntity.level().isClientSide()) {
            if (livingEntity instanceof Player) {
                Player player = (Player) livingEntity;

                int ability1 = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1");

                if (player.getServer().isDedicatedServer()) {
                    if (ability1 != 0) {
                        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown1", ability1 - Amplifier);
                    }
                }

                int ability2 = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2");

                if (player.getServer().isDedicatedServer()) {
                    if (ability2 != 0) {
                        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown2", ability2 - Amplifier);
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
