package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid.CrispyChordioid;
import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid.SpawnBlastParticleS2CPacket;
import com.hixlepod.hixlepodsorigins.common.origins.Electrum_Star.Electrum_Star;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys.GodOfFurrys;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.ChunkEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

import static com.hixlepod.hixlepodsorigins.common.events.ClientModEvents.ClientForgeEvents.*;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ServerModEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = event.getEntity();

            if (player.getServer().isDedicatedServer()) {

                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown1", OriginsManager.returnAbilityMaxCooldown1(player));

                player.getPersistentData().putString(HixlePodsOrigins.MODID + "_origin", "exdee");

                if (player.getName().equals(Component.literal("PixlePod"))) {
                    for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
                        serverPlayer.sendSystemMessage(Component.translatable("If you believe in a God, prey for their mercy.").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD));
                        serverPlayer.getLevel().playSound(null, serverPlayer.position().x, serverPlayer.position().y, serverPlayer.position().z, SoundEvents.WITHER_SPAWN, SoundSource.AMBIENT, 1, 1);
                    }
                }

                if(player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_firstTimeLogin") == true) {
                    OriginsManager.setOriginStats(player);
                } else {
                    player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_firstTimeLogin", false);
                }

                OriginsManager.setAbilityData(player);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        Player player = event.getEntity();

        if (player.getServer().isDedicatedServer()) {
            player.getPersistentData().putString(HixlePodsOrigins.MODID + "_origin",
                    event.getOriginal().getPersistentData().getString(HixlePodsOrigins.MODID + "_origin"));

            player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown1",
                    event.getOriginal().getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1"));

            player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown2",
                    event.getOriginal().getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2"));

            OriginsManager.setOriginStats(player);

        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();

        if (player.getServer().isDedicatedServer()) {
            OriginsManager.setAbilityData(player);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        Player player = event.player;

        int ability1 = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown1");

        if (player.getServer().isDedicatedServer()) {
            if (ability1 != 0) {
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown1", ability1 - 1);
            }
        }

        int ability2 = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_AbilityCooldown2");

        if (player.getServer().isDedicatedServer()) {
            if (ability2 != 0) {
                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown2", ability2 - 1);
            }
        }

        //CrispyChordioid.catAllergy(player);
        Fudge105.tick(player);
        AllyIsAngy.tick(player);
        undramaticc.tick(player);
        J_Curve.tick();

        GodOfFurrys.tick(player);

        Maxwell.tick(player);

        matt4tea.tick(player);

        Electrum_Star.tick(player);
        CatGirlSeeka.tick(player);
        ArtificalMemes.tick(player);

        Aniriai.tick(player);
        gh0stlure.tick(player);
        KyoWing3809.tick(player);

        HixlePod.tick(player);
        Blakpaw2244.tick(player);
        AmbrosiaElf.tick(player);

        OriginsUtil.returnAbilityMessage(player);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDigEvent(final PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        float f = event.getOriginalSpeed();
        if (!player.isCreative()) {

            if (player.getName().equals(Component.literal(Fudge105.NAME))) {
                if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) && !EnchantmentHelper.hasAquaAffinity(player)) {

                    f *= 5.0F;
                    event.setNewSpeed(f);
                }

                if (!player.isOnGround()) {
                    f *= 5.0F;
                    event.setNewSpeed(f);
                }
            }

            if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
                if (!player.isOnGround()) {
                    f *= 5.0F;
                    event.setNewSpeed(f);
                }
            }

            if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
                if (!player.isOnGround()) {
                    f *= 5.0F;
                    event.setNewSpeed(f);
                }
            }

            if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
                if (!player.isOnGround()) {
                    f *= 5.0F;
                    event.setNewSpeed(f);
                }
            }

            if (player.getName().equals(Component.literal(ArtificalMemes.NAME))) {
                if (!player.isOnGround()) {
                    f *= 5.0F;
                    event.setNewSpeed(f);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTargetEvent(final LivingSetAttackTargetEvent event) {
        LivingEntity target = event.getTarget();
        LivingEntity attacker = event.getEntity();

        if (target instanceof Player) {
            if (attacker instanceof Monster) {
                if (!(attacker instanceof WitherBoss) || !(attacker instanceof Warden) || !(attacker instanceof IronGolem)) {
                    if (target.getName().equals(Component.literal(Fudge105.NAME))) {
                        ((Monster) attacker).setTarget(null);
                        event.setCanceled(true);
                    }
                }

                if (attacker.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_AttackAthena") == true) {
                    if (target.getName().equals(Component.literal(undramaticc.NAME))) {
                        ((Monster) attacker).setTarget(null);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDrop(LivingDropsEvent event) {
        if (event.getEntity().getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_DropItems") == true) {
            event.getDrops().clear();
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onFallDamage(LivingFallEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getEntity().getName().equals(Component.literal(CrispyChordioid.NAME))) {
                if (event.getEntity().getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_CrispyChordioid_Smackdown") == true) {
                    event.getEntity().getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_CrispyChordioid_Smackdown", false);

                    for (Entity e : event.getEntity().getServer().overworld().getAllEntities()) {
                        if (e.position().distanceTo(event.getEntity().position()) < 5) {
                            if (!e.equals(event.getEntity()) && !(e.getTeam() == player.getTeam())) {
                                e.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(5, player));
                            }
                        }
                    }

                    for (ServerPlayer serverPlayer : player.getServer().getPlayerList().getPlayers()) {
                        NetworkManager.sendToPlayer(new SpawnBlastParticleS2CPacket(), serverPlayer);
                    }

                    player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1, 1);

                    event.setDistance(0);
                    event.setDamageMultiplier(0);
                }
            }

            if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                event.setDistance(event.getDistance() / 2);
            }
        }

        if (event.getEntity().isAlive()) {
            if (event.getEntity().getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_ZA_WARUDO") == true) {
                event.setDistance(0);
                event.setDamageMultiplier(0);
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEatEventFinish(LivingEntityUseItemEvent.Finish event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getName().equals(Component.literal(HixlePod.NAME))
                    || player.getName().equals(Component.literal(AmbrosiaElf.NAME))
                    || player.getName().equals(Component.literal(Blakpaw2244.NAME))) {

                if (event.getItem().getItem().equals(ItemInit.ENERGON_CUBE.get())) {
                    int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 60000);

                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 3, true, false));
                }

                if (event.getItem().getItem().equals(ItemInit.SYNTH_EN_CUBE.get())) {
                    int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 65000);

                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 3, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 2, true, false));
                }

                if (event.getItem().getItem().equals(ItemInit.DARK_ENERGON_CUBE.get())) {
                    int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 70000);

                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 4, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 30 * 20, 4, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300 * 20, 1, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300 * 20, 1, true, false));
                }



                if (event.getItem().getItem().equals(ItemInit.REFINED_ENERGON.get())) {
                    int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 8500);
                }

                if (event.getItem().getItem().equals(ItemInit.REFINED_SYNTH_EN.get())) {
                    int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 9000);
                }

                if (event.getItem().getItem().equals(ItemInit.REFINED_DARK_ENERGON.get())) {
                    int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 9500);
                }
            }
        }
    }

    static EntityType[] Passive_mobs = {
        EntityType.ALLAY, EntityType.AXOLOTL, EntityType.BAT, EntityType.CAT, EntityType.CHICKEN, EntityType.COD,
            EntityType.COW, EntityType.DONKEY, EntityType.FOX, EntityType.FROG, EntityType.GLOW_SQUID, EntityType.HORSE,
            EntityType.MULE, EntityType.OCELOT, EntityType.PARROT, EntityType.PIG, EntityType.RABBIT, EntityType.SALMON,
            EntityType.SHEEP, EntityType.SQUID, EntityType.STRIDER, EntityType.TADPOLE, EntityType.TROPICAL_FISH,
            EntityType.TURTLE, EntityType.MOOSHROOM
    };

    @SubscribeEvent
    public void attackEntity(AttackEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
                if (Arrays.asList(Passive_mobs).contains(event.getTarget().getType())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerSleep(final PlayerSleepInBedEvent event) {
        Player player = event.getEntity();

        if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
            if (event.getPos().getY() <= 100) {
                player.sendSystemMessage(Component.translatable("Its too low for you to sleep!").withStyle(ChatFormatting.RED));
                event.setResult(Player.BedSleepingProblem.NOT_POSSIBLE_HERE);
                event.setCanceled(true);
            }
        }
    }

    /*
    @SubscribeEvent
    public static void onArmourEquipt(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getName().equals(Component.literal(Electrum_Star.NAME))) {
                if (event.getSlot().getType() == EquipmentSlot.Type.ARMOR) {
                    if (Arrays.asList(Electrum_Star_Banned_Armour).contains(event.getSlot().getIndex(0))) {
                        player.getSlot(0).set(new ItemStack(Items.AIR));
                    }
                }
            }
        }
    }
     */

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerDamage(LivingHurtEvent event) {
        if (GodOfFurrys.shield == true) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                if (player.getName().equals(Component.literal(GodOfFurrys.NAME))) {
                    event.getSource().getDirectEntity().hurt(DamageSource.playerAttack(player),(event.getAmount() / 2));

                    if (event.getSource().isProjectile()) {
                        event.getSource().getDirectEntity().hurt(DamageSource.playerAttack(player), (event.getAmount() / 2));
                    }
                }
            }
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            float damage = event.getAmount();

            /*
            if (player.getName().equals(Component.literal(HixlePod.NAME))) {
                event.setAmount(damage * (HixlePod.DEFENCE / 100.0F));

            } else if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
                event.setAmount(damage * (70.0F / 100.0F));

            } else if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
                event.setAmount(damage * (90.0F / 100.0F));

            }
            */
            if (event.getSource().isExplosion()) {
                if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
                    event.setAmount(0);
                }
            }

            if (player.getName().equals(Component.literal(Electrum_Star.NAME)) ||
                    player.getName().equals(Component.literal(CatGirlSeeka.NAME)) ||
                        player.getName().equals(Component.literal(ArtificalMemes.NAME))) {

                int chance = OriginsUtil.randomInt(1, 100);

                if (chance <= 20) {

                    ItemEntity item = new ItemEntity(player.getLevel(), player.position().x(), player.position().y(), player.position().z(),
                            new ItemStack(ItemInit.DRAGON_SCALE.get(), 1));

                    item.position().equals(player.position());

                    player.getLevel().addFreshEntity(item);
                }
            }

            if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {

                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.PARROT_HURT, SoundSource.PLAYERS, 1, 1);

                if (event.getSource().equals(DamageSource.FLY_INTO_WALL)) {
                    event.setAmount(event.getAmount() * 2);
                }
            }

            if (player.getName().equals(Component.literal(KyoWing3809.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.HORSE_HURT, SoundSource.PLAYERS, 1, 1);
            }

            if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.GOAT_HURT, SoundSource.PLAYERS, 1, 2);
            }

            if (player.getName().equals(Component.literal(gh0stlure.NAME))) {

                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.BAT_HURT, SoundSource.PLAYERS, 1, 1);

                if (event.getSource().equals(DamageSource.FLY_INTO_WALL)) {
                    event.setAmount(event.getAmount() * 2);
                }
            }
        }
    }

    /*
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.getName().equals(Flo_Plays_.NAME)) {
                ItemEntity item = new ItemEntity(player.getLevel(), player.position().x(), player.position().y(), player.position().z(), new ItemStack(Items.CHICKEN, 1));

                item.position().equals(player.position());

                player.getLevel().addFreshEntity(item);
            }

            if (player.getName().equals(gh0stlure.NAME)) {
                ItemEntity item = new ItemEntity(player.getLevel(), player.position().x(), player.position().y(), player.position().z(), new ItemStack(Items.BONE, 1));

                item.position().equals(player.position());

                player.getLevel().addFreshEntity(item);
            }
        }
    }
     */
}
