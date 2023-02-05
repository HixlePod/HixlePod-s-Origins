package com.hixlepod.hixlepodsorigins.common.Pets;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.world.ForgeChunkManager;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityCompass extends TamableAnimal implements NeutralMob {
    public static final float FLAP_DEGREES_PER_TICK = 74.48451F;
    public static final int TICKS_PER_FLAP = Mth.ceil(2.4166098F);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(EntityCompass.class, EntityDataSerializers.BYTE);
    private static final int FLAG_RESTING = 1;
    private static final TargetingConditions BAT_RESTING_TARGETING = TargetingConditions.forNonCombat().range(2.0D);
    @Nullable
    private BlockPos targetPosition;

    private int attackCooldown = 0;

    public EntityCompass(EntityType<EntityCompass> entityType, Level level) {
        super(entityType, level);
        this.setResting(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        /*
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        */

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.33F)
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.5D)
                .add(Attributes.ARMOR, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
    }

    public boolean isFlapping() {
        return !this.isResting() && this.tickCount % TICKS_PER_FLAP == 0;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parebt) {
        return EntityInit.COMPASS.get().create(level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
    }

    public boolean isPushable() {
        return false;
    }

    protected void doPush(Entity p_27415_) {
    }

    protected void pushEntities() {
    }

    public boolean isResting() {
        return (this.entityData.get(DATA_ID_FLAGS) & 1) != 0;
    }

    public void setResting(boolean p_27457_) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (p_27457_) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 | 1));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 & -2));
        }

    }

    public void tick() {
        super.tick();
        if (this.isResting()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.setPosRaw(this.getX(), (double)Mth.floor(this.getY()) + 1.0D - (double)this.getBbHeight(), this.getZ());
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }

        if (this.isOnFire()) {
            this.clearFire();
        }

        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10 * 20, 1, true, false, false));

        if (this.getTarget() != null) {

            if (this.getTarget().position().distanceTo(this.position()) < 15) {

                if (this.attackCooldown != 0) {
                    this.attackCooldown = this.attackCooldown - 1;
                } else {

                    Vec3 point1 = this.getEyePosition();
                    Vec3 point2 = this.getTarget().position();
                    double points = 15;

                    if (this.getLevel() == this.getTarget().getLevel()) {

                        Vec3 p1 = point1;
                        Vec3 p2 = point2;

                        p2.add(0, 1, 0);

                        Vec3 vector = p2.subtract(p1).multiply(1.0 / points, 1.0 / points, 1.0 / points);

                        for (int i = 0; i < points; i++) {
                            p1 = p1.add(vector);
                            this.getServer().getLevel(this.getLevel().dimension()).sendParticles(new DustParticleOptions(new Vector3f(255, 0,0), 1), p1.x(), p1.y(), p1.z(), 0, 0d, 0d, 0d, 0d);

                        }
                    }
                    this.getTarget().hurt(DamageSource.mobAttack(this), 3);
                    this.attackCooldown = OriginsUtil.randomInt(OriginsManager.actualTicks * 2, OriginsManager.actualTicks * 4);
                }
            }
        }

        if (this.getOwner() != null) {
            if (this.getOwner().position().distanceTo(this.position()) > 25 && this.getTarget() != null) {
                this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());

            } else if (this.getOwner().position().distanceTo(this.position()) > 15 && this.getTarget() == null) {
                this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());
            }
        }

        if (this.getOwner() != null) {
            if (this.getLevel() != this.getOwner().getLevel()) {
                this.level = this.getOwner().getLevel();
            }
        }
    }

    public void setEntityOwner(Player player) {
        this.tame(player);
    }

    public boolean wantsToAttack(LivingEntity target, LivingEntity player) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {

            if (target instanceof Wolf) {
                Wolf wolf = (Wolf)target;
                return !wolf.isTame() || wolf.getOwner() != player;

            } else if (target instanceof Player && player instanceof Player && !((Player) player).canHarmPlayer((Player) target)) {
                return false;

            } else if (target instanceof AbstractHorse && ((AbstractHorse) target).isTamed()) {
                return false;

            } else {
                return !(target instanceof TamableAnimal) || !((TamableAnimal) target).isTame();
            }
        } else {
            return false;
        }
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        BlockPos blockpos = this.blockPosition();
        BlockPos blockpos1 = blockpos.above();
        if (this.isResting()) {
            boolean flag = this.isSilent();
            if (this.level.getBlockState(blockpos1).isRedstoneConductor(this.level, blockpos)) {
                if (this.random.nextInt(200) == 0) {
                    this.yHeadRot = (float)this.random.nextInt(360);
                }

                if (this.level.getNearestPlayer(BAT_RESTING_TARGETING, this) != null) {
                    this.setResting(false);
                    if (!flag) {
                        this.level.levelEvent((Player)null, 1025, blockpos, 0);
                    }
                }
            } else {
                this.setResting(false);
                if (!flag) {
                    this.level.levelEvent((Player)null, 1025, blockpos, 0);
                }
            }
        } else {
            if (this.targetPosition != null && (!this.level.isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= this.level.getMinBuildHeight())) {
                this.targetPosition = null;
            }

            if (this.targetPosition == null || this.random.nextInt(30) == 0 || this.targetPosition.closerToCenterThan(this.position(), 2.0D)) {
                this.targetPosition = new BlockPos(this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7), this.getY() + (double)this.random.nextInt(6) - 2.0D, this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7));
            }

            double d2 = (double)this.targetPosition.getX() + 0.5D - this.getX();
            double d0 = (double)this.targetPosition.getY() + 0.1D - this.getY();
            double d1 = (double)this.targetPosition.getZ() + 0.5D - this.getZ();
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double)0.1F, (Math.signum(d0) * (double)0.7F - vec3.y) * (double)0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double)0.1F);
            this.setDeltaMovement(vec31);
            float f = (float)(Mth.atan2(vec31.z, vec31.x) * (double)(180F / (float)Math.PI)) - 90.0F;
            float f1 = Mth.wrapDegrees(f - this.getYRot());
            this.zza = 0.5F;
            this.setYRot(this.getYRot() + f1);
            if (this.random.nextInt(100) == 0 && this.level.getBlockState(blockpos1).isRedstoneConductor(this.level, blockpos1)) {
                this.setResting(true);
            }
        }



    }

    public boolean causeFallDamage(float p_148702_, float p_148703_, DamageSource p_148704_) {
        return false;
    }

    protected void checkFallDamage(double p_27419_, boolean p_27420_, BlockState p_27421_, BlockPos p_27422_) {
    }

    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    public boolean hurt(DamageSource p_27424_, float p_27425_) {
        if (this.isInvulnerableTo(p_27424_)) {
            return false;
        } else {
            if (!this.level.isClientSide && this.isResting()) {
                this.setResting(false);
            }

            return super.hurt(p_27424_, p_27425_);
        }
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {

    }

    @org.jetbrains.annotations.Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@org.jetbrains.annotations.Nullable UUID p_21672_) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }
}
