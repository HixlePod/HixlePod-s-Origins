package com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.animal.horses;

import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import virtuoel.pehkui.util.ScaleUtils;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public class EntityCybertronHorse extends AbstractHorse {

    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(EntityCybertronHorse.class, EntityDataSerializers.INT);

    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemInit.ENERGON_BITS.get(), ItemInit.SYNTH_EN_BITS.get(), ItemInit.DARK_ENERGON_BITS.get());

    private int mouthCounter;

    public EntityCybertronHorse(EntityType<? extends EntityCybertronHorse> horse, Level level) {
        super(horse, level);
        this.setMaxUpStep(1.5F);
    }

    @Override
    protected void randomizeAttributes(RandomSource p_218815_) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) OriginsUtil.randomDouble(40, 50));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(OriginsUtil.randomDouble(0.3, 0.4));
        this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(OriginsUtil.randomDouble(0.4, 1));
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return FOOD_ITEMS.test(itemStack);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }



    public void addAdditionalSaveData(CompoundTag p_30716_) {
        super.addAdditionalSaveData(p_30716_);
        p_30716_.putInt("Variant", this.getTypeVariant());
        if (!this.inventory.getItem(1).isEmpty()) {
            p_30716_.put("ArmorItem", this.inventory.getItem(1).save(new CompoundTag()));
        }

    }

    public void readAdditionalSaveData(CompoundTag p_30711_) {
        super.readAdditionalSaveData(p_30711_);
        this.setTypeVariant(p_30711_.getInt("Variant"));
        if (p_30711_.contains("ArmorItem", 10)) {
            ItemStack itemstack = ItemStack.of(p_30711_.getCompound("ArmorItem"));
            if (!itemstack.isEmpty() && this.isArmor(itemstack)) {
                this.inventory.setItem(1, itemstack);
            }
        }

        this.updateContainerEquipment();
    }

    public ItemStack getArmor() {
        return this.getItemBySlot(EquipmentSlot.CHEST);
    }


    private void setArmor(ItemStack itemStack) {
        this.setItemSlot(EquipmentSlot.CHEST, itemStack);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
    }


    public boolean canMate(Animal p_30553_) {
        return true;
    }

    private void setTypeVariant(int typeVariant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, typeVariant);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariantAndMarkings(CybertronVariants p_30700_) {
        this.setTypeVariant(p_30700_.getId() & 255);
    }


    public CybertronVariants getVariant() {
        return CybertronVariants.byId(this.getTypeVariant() & 255);
    }

    protected void updateContainerEquipment() {
        if (!this.level().isClientSide) {
            super.updateContainerEquipment();
            this.setArmorEquipment(this.inventory.getItem(1));
            this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        }
    }

    private void setArmorEquipment(ItemStack p_30735_) {
        this.setArmor(p_30735_);
        if (!this.level().isClientSide) {
            this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            if (this.isArmor(p_30735_)) {
                int i = ((HorseArmorItem)p_30735_.getItem()).getProtection();
                if (i != 0) {
                    this.getAttribute(Attributes.ARMOR).addTransientModifier(new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double)i, AttributeModifier.Operation.ADDITION));
                }
            }
        }

    }

    @Override
    public double getPassengersRidingOffset() {
        return 1.75D;
    }


    @Override
    protected boolean canAddPassenger(Entity p_38390_) {
        return this.getPassengers().size() < this.getMaxPassengers();
    }


    protected int getMaxPassengers() {
        return 2;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (this.isControlledByLocalInstance() && this.lerpSteps > 0) {
            this.lerpSteps = 0;
            this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float)this.lerpYRot, (float)this.lerpXRot);
        }
    }

    public void containerChanged(Container p_30696_) {
        ItemStack itemstack = this.getArmor();
        super.containerChanged(p_30696_);
        ItemStack itemstack1 = this.getArmor();
        if (this.tickCount > 20 && this.isArmor(itemstack1) && itemstack != itemstack1) {
            this.playSound(SoundEvents.HORSE_ARMOR, 0.5F, 1.0F);
        }

    }

    protected void playGallopSound(SoundType p_30709_) {
        super.playGallopSound(p_30709_);
        if (this.random.nextInt(10) == 0) {
            this.playSound(SoundEvents.HORSE_BREATHE, p_30709_.getVolume() * 0.6F, p_30709_.getPitch());
        }

        ItemStack stack = this.inventory.getItem(1);
        if (isArmor(stack)) stack.onHorseArmorTick(this.level(), this);
    }

    private void openMouth() {
        if (!this.level().isClientSide) {
            this.mouthCounter = 1;
            this.setFlag(64, true);
        }

    }

    private void eating() {
        this.openMouth();
        if (!this.isSilent()) {
            SoundEvent soundevent = this.getEatingSound();
            if (soundevent != null) {
                this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }
        }

    }


    @Override
    protected boolean handleEating(Player p_30593_, ItemStack p_30594_) {
        boolean flag = false;
        float f = 0.0F;
        int i = 0;
        int j = 0;
        if (p_30594_.is(ItemInit.ENERGON_BITS.get())) {
            f = 2.0F;
            i = 20;
            j = 3;
        } else if (p_30594_.is(ItemInit.RED_ENERGON_BITS.get())) {
            f = 1.0F;
            i = 30;
            j = 3;
        } else if (p_30594_.is(ItemInit.ENERGON_BITS.get())) {
            f = 20.0F;
            i = 180;
        } else if (p_30594_.is(ItemInit.SYNTH_EN_BITS.get())) {
            f = 3.0F;
            i = 60;
            j = 3;
        } else if (p_30594_.is(ItemInit.ENERGON_CUBE.get())) {
            f = 4.0F;
            i = 60;
            j = 5;
            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                flag = true;
                this.setInLove(p_30593_);
            }
        } else if (p_30594_.is(ItemInit.SYNTH_EN_CUBE.get()) || p_30594_.is(ItemInit.DARK_ENERGON_CUBE.get())) {
            f = 10.0F;
            i = 240;
            j = 10;
            if (!this.level().isClientSide && this.isTamed() && this.getAge() == 0 && !this.isInLove()) {
                flag = true;
                this.setInLove(p_30593_);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            flag = true;
        }

        if (this.isBaby() && i > 0) {
            this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
            if (!this.level().isClientSide) {
                this.ageUp(i);
            }

            flag = true;
        }

        if (j > 0 && (flag || !this.isTamed()) && this.getTemper() < this.getMaxTemper()) {
            flag = true;
            if (!this.level().isClientSide) {
                this.modifyTemper(j);
            }
        }

        if (flag) {
            this.eating();
            this.gameEvent(GameEvent.EAT);
        }

        return flag;
    }

    public static AttributeSupplier.Builder createBaseHorseAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.JUMP_STRENGTH)
                .add(Attributes.MAX_HEALTH, 40.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4)
                .add(Attributes.ARMOR, 3)
                .add(Attributes.MOVEMENT_SPEED, (double)0.225F);
    }


    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        boolean flag = !this.isBaby() && this.isTamed() && player.isSecondaryUseActive();
        if (!this.isVehicle() && !flag) {
            ItemStack itemstack = player.getItemInHand(interactionHand);
            if (!itemstack.isEmpty()) {
                if (this.isFood(itemstack)) {
                    return this.fedFood(player, itemstack);
                }

                if (!this.isTamed()) {
                    this.makeMad();
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

            InteractionResult interactionresult = itemstack.interactLivingEntity(player, this, interactionHand);
            if (interactionresult.consumesAction()) {
                return interactionresult;
            } else if (this.isFood(itemstack)) {
                return this.fedFood(player, itemstack);
            } else {
                if (this.getPassengers().size() < 2 && !this.isBaby()) {
                    this.doPlayerRide(player);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        AbstractHorse abstracthorse;

        EntityCybertronHorse horse = (EntityCybertronHorse) ageableMob;
        abstracthorse = EntityInit.CYBERTRON_HORSE.get().create(serverLevel);
        int i = this.random.nextInt(9);
        CybertronVariants variant;
        if (i < 4) {
            variant = this.getVariant();
        } else if (i < 8) {
            variant = horse.getVariant();
        } else {
            variant = Util.getRandom(CybertronVariants.values(), this.random);
        }

        ((EntityCybertronHorse) abstracthorse).setVariantAndMarkings(variant);


        this.setOffspringAttributes(ageableMob, abstracthorse);
        return abstracthorse;
    }

    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.SKELETON_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.SKELETON_HORSE_DEATH;
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.HORSE_EAT;
    }

    protected SoundEvent getHurtSound(DamageSource p_30720_) {
        super.getHurtSound(p_30720_);
        return SoundEvents.SKELETON_HORSE_HURT;
    }

    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return SoundEvents.SKELETON_HORSE_AMBIENT;
    }

    public boolean canWearArmor() {
        return true;
    }

    public boolean isArmor(ItemStack p_30731_) {
        return p_30731_.getItem() instanceof HorseArmorItem;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_30703_, DifficultyInstance p_30704_, MobSpawnType p_30705_, @Nullable SpawnGroupData p_30706_, @Nullable CompoundTag p_30707_) {
        RandomSource randomsource = p_30703_.getRandom();
        CybertronVariants variant;
        if (p_30706_ instanceof EntityCybertronHorse.HorseGroupData) {
            variant = ((EntityCybertronHorse.HorseGroupData) p_30706_).variant;
        } else {
            variant = Util.getRandom(CybertronVariants.values(), randomsource);
            p_30706_ = new EntityCybertronHorse.HorseGroupData(variant);
        }

        this.setVariantAndMarkings(variant);
        return super.finalizeSpawn(p_30703_, p_30704_, p_30705_, p_30706_, p_30707_);
    }

    public static class HorseGroupData extends AgeableMob.AgeableMobGroupData {
        public final CybertronVariants variant;

        public HorseGroupData(CybertronVariants p_30740_) {
            super(true);
            this.variant = p_30740_;
        }
    }
}
