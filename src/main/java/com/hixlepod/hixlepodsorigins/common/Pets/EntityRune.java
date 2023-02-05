package com.hixlepod.hixlepodsorigins.common.Pets;

import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityRune extends TamableAnimal implements NeutralMob {

    private float allowedHeightOffset = 0.5F;
    private int nextHeightOffsetChangeTick;

    public EntityRune(EntityType<EntityRune> entityType, Level level) {
        super(entityType, level);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    private int attackCooldown = 0;

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {

            if (this.getTarget().position().distanceTo(this.position()) < 40) {

                if (this.attackCooldown != 0) {
                    this.attackCooldown = this.attackCooldown - 1;
                } else {

                    Vec3 point1 = this.getEyePosition();
                    Vec3 point2 = this.getTarget().position();

                    if (this.getLevel() == this.getTarget().getLevel()) {

                        Vec3 p1 = point1;
                        Vec3 p2 = point2;

                        p2.add(0, 1, 0);

                        for (int i = 0; i < 40; i++) {

                            SmallFireball smallFireball = new SmallFireball(this.getLevel(), this, OriginsUtil.randomDouble(-1.5, 1.5), -80.0, OriginsUtil.randomDouble(-1.5, 1.5));
                            smallFireball.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 4.5F, 1.0F);
                            smallFireball.setPos(smallFireball.getX(), this.getY(0.5D) + 0.5D, smallFireball.getZ());
                            this.getLevel().addFreshEntity(smallFireball);
                        }
                    }
                    this.attackCooldown = OriginsUtil.randomInt(OriginsManager.actualTicks * 3, OriginsManager.actualTicks * 7);
                }
            }
        }

        if (this.getOwner() != null) {
            if (this.getOwner().position().distanceTo(this.position()) > 35 && this.getTarget() != null) {
                this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());

            } else if (this.getOwner().position().distanceTo(this.position()) > 20 && this.getTarget() == null) {
                this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());
            }
        }

        if (this.getOwner() != null) {
            if (this.getLevel() != this.getOwner().getLevel()) {
                this.level = this.getOwner().getLevel();
            }
        }


        /*
        if (this.getTarget() != null) {
            if (this.getTarget().getTeam() != null && this.getOwner().getTeam() != null) {
                if (this.getTarget().getTeam() == this.getOwner().getTeam()) {
                    this.setTarget(null);
                }
            }
        }

         */
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.33F)
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.5D)
                .add(Attributes.ARMOR, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
    }

    public void setEntityOwner(Player player) {
        this.tame(player);
    }

    protected void customServerAiStep() {
        --this.nextHeightOffsetChangeTick;
        if (this.nextHeightOffsetChangeTick <= 0) {
            this.nextHeightOffsetChangeTick = 100;
            this.allowedHeightOffset = (float)this.random.triangle(0.5D, 6.891D);
        }

        LivingEntity livingentity = this.getTarget();
        if (livingentity != null && livingentity.getEyeY() > this.getEyeY() + (double)this.allowedHeightOffset && this.canAttack(livingentity)) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double)0.3F - vec3.y) * (double)0.3F, 0.0D));
            this.hasImpulse = true;
        }

        super.customServerAiStep();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return EntityInit.RUNE.get().create(level);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_21672_) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }
}
