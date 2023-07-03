package com.hixlepod.hixlepodsorigins.common.Entities.Pets;

import com.hixlepod.hixlepodsorigins.common.Entities.EntityScraplet;
import com.hixlepod.hixlepodsorigins.common.origins.OriginsManager;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityEcho extends TamableAnimal implements NeutralMob {

    private float interestedAngle;
    private float interestedAngleO;
    private boolean isWet;
    private boolean isShaking;
    private float shakeAnim;
    private float shakeAnimO;


    private int attackCooldown = 0;

    public EntityEcho(EntityType<EntityEcho> entityType, Level level) {
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
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.33F)
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.5D)
                .add(Attributes.ARMOR, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.45);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {

            if (this.getTarget().position().distanceTo(this.position()) < 20) {

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
                            this.getServer().getLevel(this.getLevel().dimension()).sendParticles(ParticleTypes.SCULK_CHARGE_POP, p1.x(), p1.y(), p1.z(), 0, 0d, 0d, 0d, 0d);

                        }
                    }


                    this.getTarget().hurt(DamageSource.mobAttack(this), OriginsUtil.damageScale(3, (Player) this.getOwner()));
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
        }

        if (this.getOwner() != null) {
            if (this.getLevel() != this.getOwner().getLevel()) {
                this.level = this.getOwner().getLevel();
            }
        }
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
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {

        ItemStack itemstack = player.getItemInHand(interactionHand);
        Item item = itemstack.getItem();

        if (this.isTame()) {

            if (!(item instanceof DyeItem)) {
                InteractionResult interactionresult = super.mobInteract(player, interactionHand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                }

                return interactionresult;
            }

        }

        return super.mobInteract(player, interactionHand);
    }

    public float getBodyRollAngle(float p_30433_, float p_30434_) {
        float f = (Mth.lerp(p_30433_, this.shakeAnimO, this.shakeAnim) + p_30434_) / 1.8F;
        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }

        return Mth.sin(f * (float)Math.PI) * Mth.sin(f * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    public float getHeadRollAngle(float p_30449_) {
        return Mth.lerp(p_30449_, this.interestedAngleO, this.interestedAngle) * 0.15F * (float)Math.PI;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parebt) {
        return EntityInit.ECHO.get().create(level);
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
