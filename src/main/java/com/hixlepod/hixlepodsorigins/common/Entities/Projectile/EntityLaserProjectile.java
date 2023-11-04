package com.hixlepod.hixlepodsorigins.common.Entities.Projectile;

import com.hixlepod.hixlepodsorigins.core.init.EntityInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class EntityLaserProjectile extends AbstractHurtingProjectile {

    private float baseDamage;

    public EntityLaserProjectile(EntityType<? extends EntityLaserProjectile> laser, Level level) {
        super(laser, level);
    }

    public EntityLaserProjectile(Level level, LivingEntity livingEntity, double X, double Y, double Z, float baseDamage) {
        super(EntityInit.LASER.get(), livingEntity, X, Y, Z, level);
        this.baseDamage = baseDamage;
    }

    protected void onHit(HitResult p_37218_) {
        super.onHit(p_37218_);
        if (!this.level().isClientSide) {
            //boolean flag = ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
            OriginsUtil.addClientParticle(this.level(), ParticleTypes.LAVA, this.position().add(0, 1, 0), new Vec3(0, 0,0 ), false);
            this.playSound(SoundEvents.GENERIC_EXPLODE, 1, 1);
            this.discard();
        }

    }

    protected void onHitEntity(EntityHitResult p_37216_) {
        super.onHitEntity(p_37216_);
        if (!this.level().isClientSide) {
            LivingEntity target = (LivingEntity) p_37216_.getEntity();
            LivingEntity attacker = (LivingEntity) this.getOwner();

            if (target instanceof LivingEntity && attacker instanceof LivingEntity) {
                target.hurt(this.damageSources().mobAttack(attacker), (float) baseDamage);
            }
        }

    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return new DustParticleOptions(new Vector3f(1, 0, 0), 1);
    }


}
