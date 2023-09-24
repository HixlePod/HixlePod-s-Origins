package com.hixlepod.hixlepodsorigins.common.Entities.Pets;

import com.hixlepod.hixlepodsorigins.common.Entities.EntityScraplet;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
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
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityDragonSlayer extends TamableAnimal implements NeutralMob {


    private int attackCooldown = 0;

    public EntityDragonSlayer(EntityType<EntityDragonSlayer> entityType, Level level) {
        super(entityType, level);

        //this.setCustomName(Component.literal(this.getTeam().getColor() + this.getTeam().getName() + " Echo"));
        //this.setCustomNameVisible(true);

    }

    public void setEntityOwner(Player player) {
        this.tame(player);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, EntityScraplet.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.33F)
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.ARMOR, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0)
                .add(ForgeMod.ENTITY_REACH.get(), 7.0);
                //.add(ForgeMod.REACH_DISTANCE.get(), 7.0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {

            if (this.getTarget().position().distanceTo(this.position()) < 20) {

                if (this.attackCooldown != 0) {
                    this.attackCooldown = this.attackCooldown - 1;
                } else {

                    for (Entity entiy : this.getServer().getLevel(this.level().dimension()).getAllEntities()) {
                        if (!entiy.equals(this) && !entiy.equals(this.getOwner()) && !(entiy.getTeam() == this.getTeam()) && entiy != null) {
                            if (entiy.position().distanceTo(this.position()) < 10) {
                                //entiy.hurt(DamageSource.mobAttack(this), OriginsUtil.damageScale(2f, (Player) this.getOwner()));
                                this.getTarget().hurt(this.damageSources().mobAttack(this), OriginsUtil.damageScale(2f, (Player) this.getOwner()));
                            }
                        }
                    }

                    OriginsUtil.sendParticle(this.getServer().getLevel(this.level().dimension()), ParticleTypes.SWEEP_ATTACK, this.position(), new Vec3(8, 3, 8), 2, 30);
                    this.attackCooldown = OriginsUtil.randomInt(OriginsManager.ticks * 5, OriginsManager.ticks * 7);
                }
            }
        }

        if (this.getOwner() != null) {
            if (!this.isOrderedToSit()) {
                if (this.getOwner().position().distanceTo(this.position()) > 35 && this.getTarget() != null) {
                    this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());

                } else if (this.getOwner().position().distanceTo(this.position()) > 20 && this.getTarget() == null) {
                    this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());
                }
            }

            PetsManager.GetHostility(this.getOwner().getPersistentData().getString("PetBehaviour"), this);
        }

        if (this.getOwner() != null) {
            if (this.level() != this.getOwner().level()) {
                this.changeDimension((ServerLevel) this.getOwner().level());
            }
        }
    }

    private void attack() {

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

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parebt) {
        return EntityInit.DRAGON_SLAYER.get().create(level);
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
