package com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss;

import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronChicken;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronCow;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.EntityCybertronPig;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.EntityEcho;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EntityBetaScraplet extends Spider {

    public EntityBetaScraplet(EntityType<? extends EntityBetaScraplet> p_33786_, Level p_33787_) {
        super(p_33786_, p_33787_);
    }

    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemInit.CUSTOM_IRON_INGOT.get());

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new EntityBetaScraplet.SpiderAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new EntityBetaScraplet.SpiderTargetGoal<>(this, Player.class));

        this.targetSelector.addGoal(3, new EntityBetaScraplet.SpiderTargetGoal<>(this, EntityEcho.class));
        this.targetSelector.addGoal(3, new EntityBetaScraplet.SpiderTargetGoal<>(this, EntityCybertronChicken.class));
        this.targetSelector.addGoal(3, new EntityBetaScraplet.SpiderTargetGoal<>(this, EntityCybertronCow.class));
        this.targetSelector.addGoal(3, new EntityBetaScraplet.SpiderTargetGoal<>(this, EntityCybertronPig.class));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.7F)
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ARMOR, 5.0)
                .add(Attributes.ARMOR_TOUGHNESS, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_32265_, EntityDimensions p_32266_) {
        return 0.4F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_33814_) {
        return SoundEvents.SPIDER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean causeFallDamage(float p_148859_, float p_148860_, DamageSource p_148861_) {
        return false;
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    static class SpiderAttackGoal extends MeleeAttackGoal {
        public SpiderAttackGoal(Spider p_33822_) {
            super(p_33822_, 1.0D, true);
        }

        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        public boolean canContinueToUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                //this.mob.setTarget((LivingEntity)null);
                return true;
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity p_33825_) {
            return (double)(4.0F + p_33825_.getBbWidth());
        }
    }

    static class SpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public SpiderTargetGoal(Spider p_33832_, Class<T> p_33833_) {
            super(p_33832_, p_33833_, true);
        }

        public boolean canUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            return super.canUse();
        }
    }
}
