package com.hixlepod.hixlepodsorigins.common.items;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import com.mojang.math.Vector3f;
import net.minecraft.core.Position;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.function.Predicate;

public class TheStringlessBow extends ProjectileWeaponItem {

    public static final int MAX_DRAW_DURATION = 5;
    public static final int DEFAULT_RANGE = 15;

    public TheStringlessBow(Item.Properties properties) {
        super(properties);
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int p_40670_) {
        if (livingEntity instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                BowAbility(player, itemStack);
            }
        }
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {

        if (entity instanceof Player) {
            Player player = (Player) entity;

            ItemStack itemstack1 = player.getProjectile(itemstack);

            if (!player.getCooldowns().isOnCooldown(itemstack.getItem())) {

                if (!itemstack1.isEmpty() || player.getAbilities().instabuild) {
                    if (itemstack1.isEmpty()) {
                        itemstack1 = new ItemStack(Items.ARROW);
                    }

                    int i = this.getUseDuration(itemstack) - 1;
                    float powerTime = getPowerForTime(i);
                    boolean flag1 = player.getAbilities().instabuild || (itemstack1.getItem() instanceof ArrowItem && ((ArrowItem) itemstack1.getItem()).isInfinite(itemstack1, itemstack, player));


                    if (!((double) powerTime < 0.1D)) {

                        ArrowItem arrowitem = (ArrowItem) (itemstack1.getItem() instanceof ArrowItem ? itemstack1.getItem() : Items.ARROW);
                        AbstractArrow abstractarrow = arrowitem.createArrow(player.getLevel(), itemstack1, player);
                        abstractarrow = customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, powerTime * 2.0F, 0.0F);

                        abstractarrow.setKnockback(3);

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, itemstack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.4D + 0.4D);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemstack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        if (flag1 || player.getAbilities().instabuild && (itemstack1.is(Items.SPECTRAL_ARROW) || itemstack1.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        player.getLevel().addFreshEntity(abstractarrow);

                        player.getLevel().playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (player.getRandom().nextFloat() * 0.4F + 1.2F) + powerTime * 0.5F);
                        if (!flag1 && !player.getAbilities().instabuild) {
                            itemstack1.shrink(1);
                            if (itemstack1.isEmpty()) {
                                player.getInventory().removeItem(itemstack1);
                            }
                        }

                        player.getCooldowns().addCooldown(itemstack.getItem(), 6);
                        player.awardStat(Stats.ITEM_USED.get(this));
                    }
                }
            }
        }

        return super.onEntitySwing(itemstack, entity);
    }

    private void BowAbility(Player player, ItemStack bow) {
        Level Unknownlevel = player.getLevel();

        if (!Unknownlevel.isClientSide()) {

            player.getCooldowns().addCooldown(bow.getItem(), 20 * 20);

            CompoundTag SaveData = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_VentiBlackhole");
            SaveData.putInt("Ticks", 20 * 15);
            SaveData.putString("Level", player.getLevel().dimension().location().toString());
            SaveData.putDouble("PosX", player.position().x());
            SaveData.putDouble("PosY", player.position().y() + 2);
            SaveData.putDouble("PosZ", player.position().z());

            player.getPersistentData().put(HixlePodsOrigins.MODID + "_VentiBlackhole", SaveData);
        }
    }

    public static void AnemoVortexTick(Player player, Vec3 position, ServerLevel level) {

        //Main stuff
        OriginsUtil.sendParticle(level, ParticleTypes.EXPLOSION, position, new Vec3(0, 0, 0), 3, 1);
        OriginsUtil.sendParticle(level, ParticleTypes.PORTAL, position, new Vec3(0, 0, 0), 10, 25);
        OriginsUtil.sendParticle(level, new DustParticleOptions(new Vector3f(0.3f, 0.9f, 0.8f), 1), position, new Vec3(3, 3, 3), 1, 10);

        for (Entity entity : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
            if (entity.position().distanceTo(position) < 25 && (entity != null) && (player != null)) {
                if (!entity.equals(player) && !(entity.getTeam() == player.getTeam())) {
                    OriginsDamageSource.hurt(entity, OriginsUtil.damageScale(1, player), OriginsDamageSource.ANEMO_VORTEXT);

                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        boolean flag = !player.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, player, interactionHand, flag);
        if (ret != null) return ret;


        if (!player.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(interactionHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    public AbstractArrow customArrow(AbstractArrow arrow) {
        return arrow;
    }

    public int getDefaultProjectileRange() {
        return 15;
    }
}
