package com.hixlepod.hixlepodsorigins.common.Entities.Bosses.Corruptling;

import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityCorruptling extends Monster {

    private static final EntityDataAccessor<Boolean> SCREAMING = SynchedEntityData.defineId(EntityCorruptling.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState walkingAnimationState = new AnimationState();
    public final AnimationState runningAnimationState = new AnimationState();
    public final AnimationState screamingAnimationState = new AnimationState();
    private int screamingAnimationTimeout = 0;

    public EntityCorruptling(EntityType<? extends EntityCorruptling> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
    }

    //Knockback ability
    private int KNOCKBACK_COOLDOWN = 20 * 15; //5 second cooldown
    private int KNOCKBACK_TIMER = 20 * 15;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        if (this.level() != null && !this.level().isClientSide()) {

            Vec3 position = this.position();
            position.add(new Vec3(0, 2, 0));

            OriginsUtil.sendParticle((ServerLevel) this.level(), ParticleTypes.WITCH, position, new Vec3(1, 2, 1), 0, 3);

            //Knockback Ability
            if (this.KNOCKBACK_TIMER != 0) {
                this.KNOCKBACK_TIMER = this.KNOCKBACK_TIMER - 1;
            } else {
                for (int i = 0; i < 4; i++) {
                    CorruptlingAttcks.PushBack(this);
                }

                this.KNOCKBACK_TIMER = this.KNOCKBACK_COOLDOWN;
            }
        }


    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isScreaming() && screamingAnimationTimeout <= 0) {
            screamingAnimationTimeout = 24;
            screamingAnimationState.start(this.tickCount);
        } else {
            --this.screamingAnimationTimeout;
        }

        if (!this.isScreaming()) {
            screamingAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(p_268283_ * 6F, 1F);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0)
                .add(Attributes.ARMOR, 20.0)
                .add(Attributes.ARMOR_TOUGHNESS, 20.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8);
    }

    public void setScreaming(boolean attacking) {
        this.entityData.set(SCREAMING, attacking);
    }

    public boolean isScreaming() {
        return this.entityData.get(SCREAMING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCREAMING, false);
    }

    public static class CorruptlingAttcks {
        public static void PushBack(EntityCorruptling corruptling) {

            for (Entity entity : corruptling.level().getServer().getLevel(corruptling.level().dimension()).getAllEntities()) {
                if (entity instanceof LivingEntity) {
                    if (entity.position().distanceTo(corruptling.position()) < 10) {

                        Vec3 difference = corruptling.position().subtract(entity.position());
                        Vec3 normalizedDifference = difference.normalize();
                        entity.setDeltaMovement(normalizedDifference.multiply(-5, -4, -5));

                        //entity.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(4, player));
                        entity.hurt(corruptling.damageSources().mobAttack(corruptling), 5);
                    }
                }
            }

        }
    }
}
