package com.hixlepod.hixlepodsorigins.common.Entities.Pets;

import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityPossum extends TamableAnimal implements NeutralMob {

    private int attackCooldown = 0;

    public EntityPossum(EntityType<EntityPossum> entityType, Level level) {
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

        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, EntityEcho.class, 6.0F, 1.0D, 1.2D));

        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
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
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.4F)
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.5D)
                .add(Attributes.ARMOR, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {

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
            if (this.level() != this.getOwner().level()) {
                this.changeDimension((ServerLevel) this.getOwner().level());
            }


            PetsManager.GetHostility(this.getOwner().getPersistentData().getString("PetBehaviour"), this);
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

    //TODO: Make the possum yeetable
    /*
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

     */

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parebt) {
        return EntityInit.POSSUM.get().create(level);
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
