package com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.Laserbeak;

import com.hixlepod.hixlepodsorigins.common.Entities.Projectile.EntityLaserProjectile;
import com.hixlepod.hixlepodsorigins.common.events.FoodLists;
import com.hixlepod.hixlepodsorigins.core.init.SoundInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class EntityLaserbeak extends FlyingMob {

    Vec3 moveTargetPoint;
    BlockPos anchorPoint;
    EntityLaserbeak.AttackPhase attackPhase;

    public EntityLaserbeak(EntityType<? extends EntityLaserbeak> p_20806_, Level p_20807_) {
        super(p_20806_, p_20807_);
        this.moveTargetPoint = Vec3.ZERO;
        this.anchorPoint = BlockPos.ZERO;
        this.attackPhase = EntityLaserbeak.AttackPhase.CIRCLE;
        this.xpReward = 10;
        this.moveControl = new EntityLaserbeak.LaserbeakMoveControl(this);
        this.lookControl = new EntityLaserbeak.LaserbeakLookControl(this);
    }

    protected BodyRotationControl createBodyControl() {
        return new EntityLaserbeak.LaserbeakBodyRotationControl(this);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new EntityLaserbeak.LaserbeakAttackStrategyGoal());
        this.goalSelector.addGoal(2, new EntityLaserbeak.LaserbeakSweepAttackGoal(this));
        this.goalSelector.addGoal(3, new EntityLaserbeak.LaserbeakCircleAroundAnchorGoal());
        this.targetSelector.addGoal(1, new EntityLaserbeak.LaserbeakAttackPlayerTargetGoal());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.ARMOR_TOUGHNESS, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 15.0);
    }

    double rangeX = -0.3d, rangeY = 0.3d;

    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {

            /*
            float $$2 = 0.5f;
            float distance = 0.1f;
            float $$3 = Mth.cos(this.getYRot() * 0.017453292F) * (1.3F + 0.21F * (float)$$2);
            float $$4 = Mth.sin(this.getYRot() * 0.017453292F) * (1.3F + 0.21F * (float)$$2);
            float $$5 = (0.3F * 0.45F) * ((float)$$2 * 0.2F + 1.0F);

            OriginsUtil.addClientParticle(this.level(), ParticleTypes.FLAME, new Vec3(this.getX() + (double)$$3, this.getY() + (double)$$5, this.getZ() + (double)$$4), new Vec3(0, 0, 0), false);
            OriginsUtil.addClientParticle(this.level(), ParticleTypes.FLAME, new Vec3(this.getX() - (double)$$3, this.getY() + (double)$$5, this.getZ() - (double)$$4), new Vec3(0, 0, 0), false);
            */

            //TEMP
            //OriginsUtil.addClientParticle(this.level(), ParticleTypes.FLAME, this.position().add(new Vec3(0, 0.25, 0)), new Vec3(0.01, 0.01, 0.01), false);

            OriginsUtil.addClientParticle(this.level(), new DustParticleOptions(new Vector3f(0, 0, 0), 1), this.position().add(new Vec3(OriginsUtil.randomDouble(rangeX, rangeY), OriginsUtil.randomDouble(rangeX, rangeY), OriginsUtil.randomDouble(rangeX, rangeY))), new Vec3(0, 0,0 ), false);
            OriginsUtil.addClientParticle(this.level(), new DustParticleOptions(new Vector3f(0, 0, 0), 1), this.position().add(new Vec3(OriginsUtil.randomDouble(rangeX, rangeY), OriginsUtil.randomDouble(rangeX, rangeY), OriginsUtil.randomDouble(rangeX, rangeY))), new Vec3(0, 0,0 ), false);
        }
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33126_, DifficultyInstance p_33127_, MobSpawnType p_33128_, @Nullable SpawnGroupData p_33129_, @Nullable CompoundTag p_33130_) {
        this.anchorPoint = this.blockPosition().above(5);
        return super.finalizeSpawn(p_33126_, p_33127_, p_33128_, p_33129_, p_33130_);
    }

    public boolean shouldRenderAtSqrDistance(double p_33107_) {
        return true;
    }

    public void readAdditionalSaveData(CompoundTag p_33132_) {
        super.readAdditionalSaveData(p_33132_);
        if (p_33132_.contains("AX")) {
            this.anchorPoint = new BlockPos(p_33132_.getInt("AX"), p_33132_.getInt("AY"), p_33132_.getInt("AZ"));
        }
    }

    public void addAdditionalSaveData(CompoundTag p_33141_) {
        super.addAdditionalSaveData(p_33141_);
        p_33141_.putInt("AX", this.anchorPoint.getX());
        p_33141_.putInt("AY", this.anchorPoint.getY());
        p_33141_.putInt("AZ", this.anchorPoint.getZ());
    }

    private static enum AttackPhase {
        CIRCLE,
        SWOOP;

        private AttackPhase() {
        }
    }

    class LaserbeakMoveControl extends MoveControl {
        private float speed = 0.5F;

        public LaserbeakMoveControl(Mob p_33241_) {
            super(p_33241_);
        }

        public void tick() {
            if (EntityLaserbeak.this.horizontalCollision) {
                EntityLaserbeak.this.setYRot(EntityLaserbeak.this.getYRot() + 180.0F);
                this.speed = 0.5F;
            }

            double $$0 = EntityLaserbeak.this.moveTargetPoint.x - EntityLaserbeak.this.getX();
            double $$1 = EntityLaserbeak.this.moveTargetPoint.y - EntityLaserbeak.this.getY();
            double $$2 = EntityLaserbeak.this.moveTargetPoint.z - EntityLaserbeak.this.getZ();
            double $$3 = Math.sqrt($$0 * $$0 + $$2 * $$2);
            if (Math.abs($$3) > 9.999999747378752E-6) {
                double $$4 = 1.0 - Math.abs($$1 * 0.699999988079071) / $$3;
                $$0 *= $$4;
                $$2 *= $$4;
                $$3 = Math.sqrt($$0 * $$0 + $$2 * $$2);
                double $$5 = Math.sqrt($$0 * $$0 + $$2 * $$2 + $$1 * $$1);
                float $$6 = EntityLaserbeak.this.getYRot();
                float $$7 = (float) Mth.atan2($$2, $$0);
                float $$8 = Mth.wrapDegrees(EntityLaserbeak.this.getYRot() + 90.0F);
                float $$9 = Mth.wrapDegrees($$7 * 57.295776F);
                EntityLaserbeak.this.setYRot(Mth.approachDegrees($$8, $$9, 4.0F) - 90.0F);
                EntityLaserbeak.this.yBodyRot = EntityLaserbeak.this.getYRot();
                if (Mth.degreesDifferenceAbs($$6, EntityLaserbeak.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float $$10 = (float)(-(Mth.atan2(-$$1, $$3) * 57.2957763671875));
                EntityLaserbeak.this.setXRot($$10);
                float $$11 = EntityLaserbeak.this.getYRot() + 90.0F;
                double $$12 = (double)(this.speed * Mth.cos($$11 * 0.017453292F)) * Math.abs($$0 / $$5);
                double $$13 = (double)(this.speed * Mth.sin($$11 * 0.017453292F)) * Math.abs($$2 / $$5);
                double $$14 = (double)(this.speed * Mth.sin($$10 * 0.017453292F)) * Math.abs($$1 / $$5);
                Vec3 $$15 = EntityLaserbeak.this.getDeltaMovement();
                EntityLaserbeak.this.setDeltaMovement($$15.add((new Vec3($$12, $$14, $$13)).subtract($$15).scale(0.2)));
            }

        }
    }

    private class LaserbeakAttackStrategyGoal extends Goal {
        private int nextSweepTick;

        LaserbeakAttackStrategyGoal() {
        }

        public boolean canUse() {
            LivingEntity $$0 = EntityLaserbeak.this.getTarget();
            return $$0 != null ? EntityLaserbeak.this.canAttack($$0, TargetingConditions.DEFAULT) : false;
        }

        public void start() {
            this.nextSweepTick = this.adjustedTickDelay(10);
            EntityLaserbeak.this.attackPhase = EntityLaserbeak.AttackPhase.CIRCLE;
            this.setAnchorAboveTarget();
        }

        public void stop() {
            EntityLaserbeak.this.anchorPoint = EntityLaserbeak.this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, EntityLaserbeak.this.anchorPoint).above(10 + EntityLaserbeak.this.random.nextInt(20));
        }

        public void tick() {
            if (EntityLaserbeak.this.attackPhase == EntityLaserbeak.AttackPhase.CIRCLE) {
                --this.nextSweepTick;
                if (this.nextSweepTick <= 0) {
                    EntityLaserbeak.this.attackPhase = EntityLaserbeak.AttackPhase.SWOOP;
                    this.setAnchorAboveTarget();
                    this.nextSweepTick = this.adjustedTickDelay((8 + EntityLaserbeak.this.random.nextInt(4)) * 20);
                    //EntityLaserbeak.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + EntityLaserbeak.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void setAnchorAboveTarget() {
            EntityLaserbeak.this.anchorPoint = EntityLaserbeak.this.getTarget().blockPosition().above(20 + EntityLaserbeak.this.random.nextInt(20));
            if (EntityLaserbeak.this.anchorPoint.getY() < EntityLaserbeak.this.level().getSeaLevel()) {
                EntityLaserbeak.this.anchorPoint = new BlockPos(EntityLaserbeak.this.anchorPoint.getX(), EntityLaserbeak.this.level().getSeaLevel() + 1, EntityLaserbeak.this.anchorPoint.getZ());
            }

        }
    }

    class LaserbeakSweepAttackGoal extends EntityLaserbeak.LaserbeakMoveTargetGoal {
        private static final int CAT_SEARCH_TICK_DELAY = 20;
        private boolean isScaredOfCat;
        private int catSearchTick;

        private int ATTACK_COOLDOWN = 5;
        private int ATTACK_COUNT = 0;

        private final EntityLaserbeak laserbeak;

        LaserbeakSweepAttackGoal(EntityLaserbeak laserbeak) {
            this.laserbeak = laserbeak;
        }

        public boolean canUse() {
            return EntityLaserbeak.this.getTarget() != null && EntityLaserbeak.this.attackPhase == EntityLaserbeak.AttackPhase.SWOOP;
        }

        public boolean canContinueToUse() {
            LivingEntity $$0 = EntityLaserbeak.this.getTarget();
            if ($$0 == null) {
                return false;
            } else if (!$$0.isAlive()) {
                return false;
            } else {
                if ($$0 instanceof Player) {
                    Player $$1 = (Player)$$0;
                    if ($$0.isSpectator() || $$1.isCreative()) {
                        return false;
                    }
                }

                if (!this.canUse()) {
                    return false;
                } else {
                    if (EntityLaserbeak.this.tickCount > this.catSearchTick) {
                        this.catSearchTick = EntityLaserbeak.this.tickCount + 20;
                        List<Cat> $$2 = EntityLaserbeak.this.level().getEntitiesOfClass(Cat.class, EntityLaserbeak.this.getBoundingBox().inflate(16.0), EntitySelector.ENTITY_STILL_ALIVE);
                        Iterator var3 = $$2.iterator();

                        while(var3.hasNext()) {
                            Cat $$3 = (Cat)var3.next();
                            $$3.hiss();
                        }

                        this.isScaredOfCat = !$$2.isEmpty();
                    }

                    return !this.isScaredOfCat;
                }
            }
        }

        public void start() {
        }

        public void stop() {
            this.laserbeak.setTarget((LivingEntity)null);
            this.laserbeak.attackPhase = EntityLaserbeak.AttackPhase.CIRCLE;
        }

        public void tick() {
            LivingEntity target = this.laserbeak.getTarget();
            if (target != null) {
                this.laserbeak.moveTargetPoint = new Vec3(target.getX(), target.getY(0.5), target.getZ());
                if (this.laserbeak.getBoundingBox().inflate(0.20000000298023224).intersects(target.getBoundingBox())) {
                    this.laserbeak.doHurtTarget(target);
                    this.laserbeak.attackPhase = EntityLaserbeak.AttackPhase.CIRCLE;
                    /* Must be the audio
                    if (!this.laserbeak.isSilent()) {
                        this.laserbeak.level().levelEvent(1039, this.laserbeak.blockPosition(), 0);
                    }
                     */
                } else if (this.laserbeak.horizontalCollision || this.laserbeak.hurtTime > 0) {
                    this.laserbeak.attackPhase = EntityLaserbeak.AttackPhase.CIRCLE;
                }

                //Shoot laser
                if (ATTACK_COUNT == 0) {
                    if (target.distanceToSqr(this.laserbeak) < 4096.0 && this.laserbeak.hasLineOfSight(target)) {
                        Level level = this.laserbeak.level();

                        Vec3 LaserbeakViewVector = this.laserbeak.getViewVector(1.0F);
                        double rotX = target.getX() - (this.laserbeak.getX() + LaserbeakViewVector.x * 4.0);
                        double rotY = target.getY(0.5) - (0.5 + this.laserbeak.getY(-0.5));
                        double rotZ = target.getZ() - (this.laserbeak.getZ() + LaserbeakViewVector.z * 4.0);

                        EntityLaserProjectile laser = new EntityLaserProjectile(level, this.laserbeak, rotX, rotY, rotZ, 1f);
                        laser.setPos(this.laserbeak.getX() + LaserbeakViewVector.x * 4.0, this.laserbeak.getY() - 1, laser.getZ() + LaserbeakViewVector.z * 4.0);
                        level.addFreshEntity(laser);

                        //level.playSound(null, this.laserbeak.position().x(), this.laserbeak.position().y(), this.laserbeak.position().z(), SoundInit.LASER_SHOOT.get(), SoundSource.HOSTILE, 0.1f, 2);
                        this.laserbeak.playSound(SoundInit.LASER_SHOOT.get(), 0.1f, 2);
                    }
                    ATTACK_COUNT = ATTACK_COOLDOWN;
                } else {
                    ATTACK_COUNT--;
                }

            }
        }
    }

    private class LaserbeakCircleAroundAnchorGoal extends EntityLaserbeak.LaserbeakMoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        LaserbeakCircleAroundAnchorGoal() {
            super();
        }

        public boolean canUse() {
            return EntityLaserbeak.this.getTarget() == null || EntityLaserbeak.this.attackPhase == EntityLaserbeak.AttackPhase.CIRCLE;
        }

        public void start() {
            this.distance = 5.0F + EntityLaserbeak.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + EntityLaserbeak.this.random.nextFloat() * 9.0F;
            this.clockwise = EntityLaserbeak.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        public void tick() {
            if (EntityLaserbeak.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + EntityLaserbeak.this.random.nextFloat() * 9.0F;
            }

            if (EntityLaserbeak.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (EntityLaserbeak.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = EntityLaserbeak.this.random.nextFloat() * 2.0F * 3.1415927F;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (EntityLaserbeak.this.moveTargetPoint.y < EntityLaserbeak.this.getY() && !EntityLaserbeak.this.level().isEmptyBlock(EntityLaserbeak.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (EntityLaserbeak.this.moveTargetPoint.y > EntityLaserbeak.this.getY() && !EntityLaserbeak.this.level().isEmptyBlock(EntityLaserbeak.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(EntityLaserbeak.this.anchorPoint)) {
                EntityLaserbeak.this.anchorPoint = EntityLaserbeak.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * 0.017453292F;
            EntityLaserbeak.this.moveTargetPoint = Vec3.atLowerCornerOf(EntityLaserbeak.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }

    private class LaserbeakAttackPlayerTargetGoal extends Goal {
        private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0);
        private int nextScanTick = reducedTickDelay(20);


        LaserbeakAttackPlayerTargetGoal() {}

        public boolean canUse() {
            if (this.nextScanTick > 0) {
                --this.nextScanTick;
                return false;
            } else {
                this.nextScanTick = reducedTickDelay(60);
                List<LivingEntity> mobs = EntityLaserbeak.this.level().getNearbyEntities(LivingEntity.class, this.attackTargeting, EntityLaserbeak.this, EntityLaserbeak.this.getBoundingBox().inflate(16.0, 64.0, 16.0));
                if (!mobs.isEmpty()) {
                    //$$0.sort(Comparator.comparing(Entity::getY).reversed());

                    Iterator iterator = mobs.listIterator();

                    while(iterator.hasNext()) {
                        LivingEntity target = (LivingEntity) iterator.next();

                        if (target instanceof LaserbeakTarget || target instanceof Player) {

                            if (target instanceof Player && Arrays.asList(FoodLists.ROBOTS).contains(target.getName())) {
                                iterator.remove();
                            }

                            if (EntityLaserbeak.this.canAttack(target, TargetingConditions.DEFAULT)) {

                                EntityLaserbeak.this.setTarget(target);
                                return true;
                            }
                        } else {
                            iterator.remove();
                        }
                    }
                }

                return false;
            }
        }

        public boolean canContinueToUse() {
            LivingEntity $$0 = EntityLaserbeak.this.getTarget();
            return $$0 != null ? EntityLaserbeak.this.canAttack($$0, TargetingConditions.DEFAULT) : false;
        }
    }

    class LaserbeakLookControl extends LookControl {
        public LaserbeakLookControl(Mob p_33235_) {
            super(p_33235_);
        }

        public void tick() {
        }
    }

    class LaserbeakBodyRotationControl extends BodyRotationControl {
        public LaserbeakBodyRotationControl(Mob p_33216_) {
            super(p_33216_);
        }

        public void clientTick() {
            EntityLaserbeak.this.yHeadRot = EntityLaserbeak.this.yBodyRot;
            EntityLaserbeak.this.yBodyRot = EntityLaserbeak.this.getYRot();
        }
    }

    abstract class LaserbeakMoveTargetGoal extends Goal {
        public LaserbeakMoveTargetGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return EntityLaserbeak.this.moveTargetPoint.distanceToSqr(EntityLaserbeak.this.getX(), EntityLaserbeak.this.getY(), EntityLaserbeak.this.getZ()) < 4.0;
        }
    }
}
