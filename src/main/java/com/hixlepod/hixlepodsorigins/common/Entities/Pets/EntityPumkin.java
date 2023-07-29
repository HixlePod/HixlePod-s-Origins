package com.hixlepod.hixlepodsorigins.common.Entities.Pets;

import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityPumkin extends TamableAnimal implements NeutralMob {

    public EntityPumkin(EntityType<EntityPumkin> entityType, Level level) {
        super(entityType, level);

    }

    public void setEntityOwner(Player player) {
        this.tame(player);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F)
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.5D)
                .add(Attributes.ARMOR, 1.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getOwner() != null) {

            PetsManager.GetHostility(this.getOwner().getPersistentData().getString("PetBehaviour"), this);
        }
    }

    private static final Component CONTAINER_TITLE = Component.translatable("container.enderchest");

    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        InteractionResult interactionresult = super.mobInteract(player, interactionHand);
        if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {

            player.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> {
                return ChestMenu.threeRows(p_53124_, p_53125_, player.getEnderChestInventory());
            }, CONTAINER_TITLE));
            player.awardStat(Stats.OPEN_ENDERCHEST);

            return InteractionResult.SUCCESS;
        }

        return interactionresult;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parebt) {
        return EntityInit.PUMKIN.get().create(level);
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
