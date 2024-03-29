package com.hixlepod.hixlepodsorigins.common.Entities.NPC;

import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;
import sereneseasons.api.season.Season;
import sereneseasons.block.SeasonSensorBlock;
import sereneseasons.core.SereneSeasons;
import sereneseasons.season.SeasonHooks;
import sereneseasons.season.SeasonTime;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.UUID;

public class EntityBooNPC extends PathfinderMob implements NeutralMob {


    public EntityBooNPC(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);

        ScaleTypes.WIDTH.getScaleData(this).setScale(0.8f);
        ScaleTypes.HEIGHT.getScaleData(this).setScale(0.78f);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        //this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.45F)
                .add(Attributes.MAX_HEALTH, 100000.0)
                .add(Attributes.ATTACK_DAMAGE, 50.0D)
                .add(Attributes.ARMOR, 20.0)
                .add(Attributes.ARMOR_TOUGHNESS, 20.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1);
    }


    @Override
    public void checkDespawn() {
        super.checkDespawn();

        long DayCount = this.level().getDayTime() / 24000;

        if (DayCount % OriginSettings.NPC_SPAWN_FREQUENCY != 0) {
            this.discard();
        } else {
            this.noActionTime = 0;
        }
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_217055_, DifficultyInstance p_217056_) {
        super.populateDefaultEquipmentSlots(p_217055_, p_217056_);

        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SPYGLASS));

        if (SeasonTime.ZERO.getSeason() == Season.WINTER) {
            this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType p_146748_, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag p_146750_) {

        RandomSource randomsource = serverLevelAccessor.getRandom();

        this.populateDefaultEquipmentSlots(randomsource, difficultyInstance);

        return spawnGroupData;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.CAT_HURT;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.hasCustomName() || !this.isCustomNameVisible()) {
            this.setCustomName(Component.literal(ChatFormatting.LIGHT_PURPLE + "[Origins] Boo"));
            this.setCustomNameVisible(true);
        }
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
