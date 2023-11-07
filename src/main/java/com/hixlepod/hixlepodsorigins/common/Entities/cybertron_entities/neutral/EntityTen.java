package com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.neutral;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
public class EntityTen extends TamableAnimal implements NeutralMob {

    private int attackAnimationTick;
    private int offerFlowerTick;

    private enum States {
        FOLLOW,
        WONDER_AROUND_SPOT,
        SLEEP
    }

    private States state;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemInit.ENERGON_CUBE.get(), ItemInit.SYNTH_EN_CUBE.get(), ItemInit.DARK_ENERGON_CUBE.get());

    @Override
    public boolean isFood(ItemStack itemStack) {
        return FOOD_ITEMS.test(itemStack);
    }

    public EntityTen(EntityType<EntityTen> entityType, Level level) {
        super(entityType, level);
    }

    public void setEntityOwner(Player player) {
        this.tame(player);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new SitWhenOrderedToGoal(this));
        //this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
        }));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double) 0.32F)
                .add(Attributes.MAX_HEALTH, 350.0D)
                .add(Attributes.ATTACK_DAMAGE, 20.5D)
                .add(Attributes.ARMOR, 8.0)
                .add(Attributes.ARMOR_TOUGHNESS, 8.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5)
                .add(ForgeMod.ENTITY_REACH.get(), 5.5);
    }

    private int timeToRecalcPath;

    @Override
    public void tick() {
        super.tick();

        if (this.state == States.SLEEP) {

            /*
            for(Goal goal : this.goalSelector.getAvailableGoals()) {
                if (goal instanceof FollowOwnerGoal) {
                    this.goalSelector.removeGoal(goal);
                }
            }

             */

            this.jumping = false;
            this.navigation.stop();
            this.setTarget((LivingEntity)null);

            this.setDeltaMovement(new Vec3(0, 0, 0));

        } else if (this.state == States.FOLLOW) {

            //this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
            if (this.getOwner() != null) {
                if (this.getOwner().position().distanceTo(this.position()) > 35 && this.getTarget() != null) {
                    this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());

                } else if (this.getOwner().position().distanceTo(this.position()) > 20 && this.getTarget() == null) {
                    this.teleportTo(this.getOwner().position().x(), this.getOwner().position().y(), this.getOwner().position().z());
                }
            }

        } else if (this.state == States.WONDER_AROUND_SPOT) {

            /*
            for(Goal goal : this.goalSelector.getAvailableGoals()) {
                if (goal instanceof FollowOwnerGoal) {
                    this.goalSelector.removeGoal(goal);
                }
            }

             */

            CompoundTag component = this.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_StayPosition");

            Vec3 vec3 = new Vec3(component.getDouble("PosX"), component.getDouble("PosY"), component.getDouble("PosZ"));

            if (this.position().distanceTo(vec3) > 50) {
                this.teleportTo(vec3.x(), vec3.y(), vec3.z());
            }
        }
    }


    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    public int getOfferFlowerTick() {
        return this.offerFlowerTick;
    }

    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(ItemInit.DARK_ENERGON_CUBE.get()) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal(30.0f);
                    this.gameEvent(GameEvent.EAT, this);
                    return InteractionResult.SUCCESS;
                }

                if (itemstack.isEmpty()) {
                    if (this.isOwnedBy(player)) {


                        double x = this.getX();
                        double y = this.getY();
                        double z = this.getZ();

                        if (this.state == States.FOLLOW) {
                            this.state = States.SLEEP;

                            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You commanded Ten to" + ChatFormatting.GOLD + " sleep" + ChatFormatting.GREEN + " here."));

                        } else if (this.state == States.SLEEP) {
                            this.state = States.WONDER_AROUND_SPOT;

                            CompoundTag SaveData = new CompoundTag();

                            SaveData.putDouble("PosX", x);
                            SaveData.putDouble("PosY", y);
                            SaveData.putDouble("PosZ", z);

                            this.getPersistentData().put(HixlePodsOrigins.MODID + "_StayPosition", SaveData);

                            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You commanded Ten to" + ChatFormatting.GOLD + " wander" + ChatFormatting.GREEN + " here."));

                        } else if (this.state == States.WONDER_AROUND_SPOT) {
                            this.state = States.FOLLOW;

                            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You commanded Ten to" + ChatFormatting.GOLD + " follow" + ChatFormatting.GREEN + " you."));

                        } else {
                            this.state = States.SLEEP;

                            player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "You commanded Ten to" + ChatFormatting.GOLD + " sleep" + ChatFormatting.GREEN + " here."));
                        }
                    }
                }
            } else if (itemstack.is(ItemInit.DARK_ENERGON_CUBE.get()) && !this.isAngry()) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(5) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    this.level().broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte)6);
                }
                return InteractionResult.SUCCESS;
            }
            return super.mobInteract(player, interactionHand);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob p_146744_) {
        return EntityInit.TEN.get().create(serverLevel);
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
