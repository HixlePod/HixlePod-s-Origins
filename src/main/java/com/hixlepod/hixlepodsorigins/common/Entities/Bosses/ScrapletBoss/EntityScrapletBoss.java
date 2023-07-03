package com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss;

import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EntityScrapletBoss extends Spider {

    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(0);

    public EntityScrapletBoss(EntityType<? extends EntityScrapletBoss> p_33786_, Level p_33787_) {
        super(p_33786_, p_33787_);
        this.bossEvent.setName(Component.literal(ChatFormatting.AQUA + "Airachnid Prime"));
    }

    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemInit.CUSTOM_IRON_INGOT.get());
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS));


    private int SCRAPLET_DUPLICATION_COOLDOWN = 20 * 5; //5 second cooldown
    private int SCRAPLET_DUPLICATION_TIMER = 20 * 5;

    private int ABILITY_COOLDOWN = 20 * 8;
    private int ABILITY_TIMER = 20 * 8;

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new EntityScrapletBoss.SpiderAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new EntityScrapletBoss.SpiderTargetGoal<>(this, Player.class));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.12F)
                .add(Attributes.MAX_HEALTH, 1000.0)
                .add(Attributes.ATTACK_DAMAGE, 25.0D)
                .add(Attributes.ARMOR, 20.0)
                .add(Attributes.ARMOR_TOUGHNESS, 20.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.ATTACK_KNOCKBACK, 2);
    }

    private double LEAPING_HEIGHT = 0.8d;
    private double LEAPING_LENGTH = 1d;

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {
            if (this.SCRAPLET_DUPLICATION_TIMER != 0) {
                this.SCRAPLET_DUPLICATION_TIMER = this.SCRAPLET_DUPLICATION_TIMER - 1;
            } else {
                for (int i = 0; i < 4; i++) {
                    SpawnBetaScaplet();
                }

                this.SCRAPLET_DUPLICATION_TIMER = this.SCRAPLET_DUPLICATION_COOLDOWN;
            }


            if (this.ABILITY_TIMER != 0) {
                this.ABILITY_TIMER = this.ABILITY_TIMER - 1;
            } else {

                switch (OriginsUtil.randomInt(1, 4)) {
                    case 1: YeetScraplet(this, this.getTarget()); break;
                    case 2:

                        for (int i = 0; i < OriginsUtil.randomInt(14, 21); i++) {

                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    SpawnBetaScaplet();
                                }

                            }, 85 * i, TimeUnit.MILLISECONDS);
                        }
                        break;

                    case 3:
                    case 4:

                        for (int i = 0; i < 3; i++) {
                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    getLevel().playSound(null, blockPosition(), SoundEvents.ZOMBIE_VILLAGER_CONVERTED, SoundSource.HOSTILE, 1, 2);
                                }
                            }, i * 1, TimeUnit.SECONDS);
                        }

                        for (int i = 0; i < 5; i++) {
                            scheduler.schedule(new Runnable() {
                                public void run() {
                                    BoomAbility();
                                }
                            }, (i * 400) + (1000 * 3), TimeUnit.MILLISECONDS);
                        }
                        break;

                }

                this.ABILITY_TIMER = this.ABILITY_COOLDOWN;
            }
        }
    }

    public void BoomAbility() {
        playSound(SoundEvents.GENERIC_EXPLODE, 1, 2);
        SpawnBetaScaplet();

        for (Entity entity : this.getServer().getLevel(this.getLevel().dimension()).getAllEntities()) {
            if (entity instanceof Player) {
                if (entity.position().distanceTo(this.position()) < 10) {
                    Vec3 difference = this.position().subtract(entity.position());
                    Vec3 normalizedDifference = difference.normalize();
                    entity.setDeltaMovement(normalizedDifference.multiply(-5, -2, -5));

                    entity.hurt(DamageSource.explosion(this), 35);
                }
            }
        }
    }

    public void SpawnBetaScaplet() {
        EntityBetaScraplet entity = EntityInit.BETA_SCRAPLET.get().create(this.getLevel());

        entity.moveTo(this.position());

        YeetScraplet(entity, this.getTarget(), LEAPING_HEIGHT + 0.3, LEAPING_LENGTH - 0.3, OriginsUtil.randomDouble(-12, 12), OriginsUtil.randomDouble(-12, 12));

        this.getLevel().addFreshEntity(entity);
    }

    public void YeetScraplet(Entity entity, Entity target) {
        YeetScraplet(entity, target, LEAPING_HEIGHT, LEAPING_LENGTH, 0, 0);
    }

    public void YeetScraplet(Entity entity, Entity target, double leap_height, double leap_length, double XOffset, double ZOffset) {
        Vec3 vec3 = entity.getDeltaMovement();
        Vec3 vec31 = new Vec3((target.getX() - entity.getX()) + XOffset, 0.0D, (target.getZ() - entity.getZ() + ZOffset));
        if (vec31.lengthSqr() > 1.0E-7D) {
            vec31 = vec31.normalize().scale(leap_length).add(vec3.scale(leap_height));
        }

        entity.setDeltaMovement(vec31.x, (double) 1f, vec31.z);

        OriginsUtil.sendParticle(entity.getServer().getLevel(entity.getLevel().dimension()), ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.position(), new Vec3(0.2, 0.2, 0.2), 0.5, 10);
    }

    @Override
    public int getExperienceReward() {
        return 100;
    }

    @Override
    protected void customServerAiStep() {
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public boolean causeFallDamage(float p_148859_, float p_148860_, DamageSource p_148861_) {
        return false;
    }

    public void startSeenByPlayer(ServerPlayer p_31483_) {
        super.startSeenByPlayer(p_31483_);
        this.bossEvent.addPlayer(p_31483_);
    }

    public void stopSeenByPlayer(ServerPlayer p_31488_) {
        super.stopSeenByPlayer(p_31488_);
        this.bossEvent.removePlayer(p_31488_);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_32265_, EntityDimensions p_32266_) {
        return 0.4F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_33814_) {
        return SoundEvents.SPIDER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }


    static class SpiderAttackGoal extends MeleeAttackGoal {
        public SpiderAttackGoal(Spider p_33822_) {
            super(p_33822_, 1.0D, true);
        }

        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        public boolean canContinueToUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                //this.mob.setTarget((LivingEntity)null);
                return true;
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity p_33825_) {
            return (double)(4.0F + p_33825_.getBbWidth());
        }
    }

    static class SpiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public SpiderTargetGoal(Spider p_33832_, Class<T> p_33833_) {
            super(p_33832_, p_33833_, true);
        }

        public boolean canUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            return super.canUse();
        }
    }
}
