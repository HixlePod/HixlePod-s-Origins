package com.hixlepod.hixlepodsorigins.common.events;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityScraplet;
import com.hixlepod.hixlepodsorigins.common.items.TheStringlessBow;
import com.hixlepod.hixlepodsorigins.common.origins.*;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.CrispyChordioid;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.Fudge105;
import com.hixlepod.hixlepodsorigins.common.origins.GodOfFurrys;
import com.hixlepod.hixlepodsorigins.core.init.DimensionsInit;
import com.hixlepod.hixlepodsorigins.core.init.EffectsInit;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginSettings;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsDamageSource;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ServerModEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = event.getEntity();

            if (player.getServer().isDedicatedServer()) {

                player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_AbilityCooldown1", OriginsManager.returnAbilityMaxCooldown1(player));

                player.getPersistentData().putString(HixlePodsOrigins.MODID + "_origin", "exdee");

                if(player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_firstTimeLogin") == true) {
                    OriginsManager.setOriginStats(player);
                } else {
                    player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_firstTimeLogin", false);
                }

                OriginsManager.setAbilityData(player);
                OriginsManager.setOriginStats(player);
            }
        }
    }

    @SubscribeEvent
    public static void SmallOriginHeadHop(PlayerInteractEvent.EntityInteract event) {


        if (event.getEntity() instanceof Player) {
            ServerPlayer player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(event.getEntity().getName().getString());

            if (event.getTarget() instanceof Player) {
                ServerPlayer target = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(event.getTarget().getName().getString());

                if (player != null && target != null) {

                    if (OriginSettings.SITTING_ENABLED) {

                        if (player.getName().equals(Component.literal(AmbrosiaElf.NAME)) || player.getName().equals(Component.literal(TricoFan.NAME)) || player.getName().equals(Component.literal(Stamce.NAME))) {

                            if (target.getName().equals(Component.literal(AmbrosiaElf.NAME)) || target.getName().equals(Component.literal(TricoFan.NAME)) || target.getName().equals(Component.literal(Stamce.NAME))) {


                            } else {
                                player.setYRot(target.getYRot());
                                player.setXRot(target.getXRot());
                                player.startRiding(target);
                                player.rideTick();
                                target.rideTick();

                                target.sendSystemMessage(Component.literal(ChatFormatting.GREEN + player.getName().getString() + " is sitting on your head!"));
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void playerShootEvent(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player) {
            Player attacker = (Player) event.getSource().getEntity();

            if (attacker.getName().equals(Component.literal(Aniriai.NAME))) {
                if (event.getEntity() instanceof LivingEntity) {
                    if (event.getSource().isProjectile()) {
                        event.setAmount(event.getAmount() * 2);
                    }
                }
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

            //Ground bridge
            player.getPersistentData().put(HixlePodsOrigins.MODID + "_GroundBridgeLocations",
                    event.getOriginal().getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeLocations"));

            player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_HasGroundBridge",
                    event.getOriginal().getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_HasGroundBridge"));

            player.getPersistentData().put(HixlePodsOrigins.MODID + "_GroundBridgeBlock",
                    event.getOriginal().getPersistentData().getCompound(HixlePodsOrigins.MODID + "_GroundBridgeBlock"));

            OriginsManager.setOriginStats(player);

        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();

        if (player.getServer().isDedicatedServer()) {
            OriginsManager.setAbilityData(player);
            OriginsManager.SetRobotEnergon(player);
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

        //Netrual
        Fudge105.tick(player);
        matt4tea.tick(player);

        //Touch grass
        AllyIsAngy.tick(player);
        J_Curve.tick();
        //CrispyChordioid.tick(player);

        //Oblivian federation
        GodOfFurrys.tick(player);
        Maxwell.tick(player);
        undramaticc.tick(player);
        Stamce.tick(player);

        CatGirlSeeka.tick(player);
        ofcourseidid.tick(player);

        //Satanic Panic
        Aniriai.tick(player);
        gh0stlure.tick(player);
        TricoFan.tick(player);

        //Skyflower
        KyoWing3809.tick(player);

        //The Wreckers
        HixlePod.tick(player);
        Blakpaw2244.tick(player);
        AmbrosiaElf.tick(player);
        Folf_Gaming.tick(player);
        Kira_uwu69.tick(player);

        OriginsUtil.returnAbilityMessage(player);

        if (player.getLevel().dimension() == DimensionsInit.CYBERTRON_KEY && player.isInWaterOrRain() && isInRain(player)) {
            OriginsDamageSource.hurt(player, 0.5f, OriginsDamageSource.ACID_RAIN);
        }

        CompoundTag Data = player.getPersistentData().getCompound(HixlePodsOrigins.MODID + "_VentiBlackhole");
        int ticks = Data.getInt("Ticks");

        if (ticks != 0) {
            Data.putInt("Ticks", ticks - 1);

            Vec3 position = new Vec3(Data.getDouble("PosX"), Data.getDouble("PosY"), Data.getDouble("PosZ"));

            ResourceKey<Level> resourcekey = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Data.getString("Level")));
            ServerLevel serverlevel = player.getServer().getLevel(resourcekey);
            if (serverlevel == null) {}

            TheStringlessBow.AnemoVortexTick(player, position, serverlevel);
            player.getPersistentData().put(HixlePodsOrigins.MODID + "_VentiBlackhole", Data);
        }
    }

    public static boolean isInRain(Player player) {
        BlockPos blockpos = player.blockPosition();
        return player.getLevel().isRainingAt(blockpos) || player.getLevel().isRainingAt(new BlockPos((double)blockpos.getX(), player.getBoundingBox().maxY, (double)blockpos.getZ()));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void attackPlayer(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player) {
            Player player = (Player) event.getSource().getEntity();

            if (player.getName().equals(Component.literal(Flo_Plays_.NAME))) {
                if (player.isFallFlying()) {
                    event.setAmount(event.getAmount() * 2);
                }
            }
            if (event.getEntity() instanceof LivingEntity) {
                if (player.getName().equals(Component.literal(gh0stlure.NAME))) {
                    if (!player.hasEffect(MobEffects.REGENERATION)) {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0, true, false));
                    }
                }

                if (player.getName().equals(Component.literal(matt4tea.NAME))) {
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 5 * 20, 2, true, false));
                }

                if (player.getName().equals(Maxwell.NAME)) {
                    event.getEntity().setRemainingFireTicks(20 * 5);
                }
            }
        }


        if (event.getEntity() instanceof Player && event.getSource().getEntity() instanceof LivingEntity) {
            Player player = (Player) event.getEntity();
            LivingEntity target = (LivingEntity) event.getSource().getEntity();

            if (player.getName().equals(Component.literal(J_Curve.NAME))) {

                if (OriginsUtil.didChance(45)) {
                    ServerLevel level = player.getServer().getLevel(player.getLevel().dimension());

                    SmallFireball smallFireball = new SmallFireball(level, player, (target.getX() - player.getX()), -80.0, (target.getZ() - player.getZ()));
                    smallFireball.setPos(smallFireball.getX(), player.getY(0.5D) + 0.5D, smallFireball.getZ());
                    level.addFreshEntity(smallFireball);
                }
            }
        }
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

            if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {
                if (!player.isOnGround()) {
                    f *= 5.0F;
                    event.setNewSpeed(f);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTargetEvent(final LivingChangeTargetEvent event) {
        LivingEntity target = event.getNewTarget();
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

                if (attacker instanceof Silverfish) {
                    if (target.getName().equals(Component.literal(TricoFan.NAME))) {
                        ((Monster) attacker).setTarget(null);
                        event.setCanceled(true);
                    }
                }

                if (attacker instanceof EntityScraplet) {
                    if (target.getName().equals(Component.literal(HixlePod.NAME))
                            || target.getName().equals(Component.literal(AmbrosiaElf.NAME))
                            || target.getName().equals(Component.literal(Blakpaw2244.NAME))
                            || target.getName().equals(Component.literal(Folf_Gaming.NAME))
                            || target.getName().equals(Component.literal(Kira_uwu69.NAME))) {
                        //Do nothing
                    } else {
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

            if (player.getName().equals(Component.literal(CrispyChordioid.NAME))) {
                if (player.getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_CrispyChordioid_Smackdown")) {
                    player.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_CrispyChordioid_Smackdown", false);

                    for (Entity e : player.getServer().getLevel(player.getLevel().dimension()).getAllEntities()) {
                        if (e.position().distanceTo(player.position()) < 5) {
                            if (!e.equals(player) && !(e.getTeam() == player.getTeam())) {
                                e.hurt(DamageSource.playerAttack(player), OriginsUtil.damageScale(5, player));
                            }
                        }
                    }

                    OriginsUtil.sendParticle(player.getServer().getLevel(player.getLevel().dimension()), ParticleTypes.EXPLOSION, player.position(), new Vec3(2,2,2), 0, 15);

                    player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1, 1);

                    event.setDistance(0);
                    event.setDamageMultiplier(0);
                }
            }

            if (player.getName().equals(Component.literal(Aniriai.NAME))) {
                event.setDistance(event.getDistance() / 2);
            }

            if (player.getName().equals(Component.literal(Stamce.NAME)) || player.getName().equals(Component.literal(matt4tea.NAME))) {
                event.setDistance(event.getDistance() * 0.2f);
            }

            if (player.getName().equals(Component.literal(AmbrosiaElf.NAME)) || player.getName().equals(Component.literal(TricoFan.NAME))) {
                event.setDistance(event.getDistance() * 0.75f);
            }
        }


        if (event.getEntity().isAlive()) {
            if (event.getEntity().getPersistentData().getBoolean(HixlePodsOrigins.MODID + "_ZA_WARUDO")) {
                event.setDistance(0);
                event.setDamageMultiplier(0);
            }
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEatEventFinish(LivingEntityUseItemEvent.Finish event) {

        if (event.getEntity() instanceof Player player) {

            if (player.getName().equals(Component.literal(HixlePod.NAME))
                    || player.getName().equals(Component.literal(AmbrosiaElf.NAME))
                    || player.getName().equals(Component.literal(Blakpaw2244.NAME))
                    || player.getName().equals(Component.literal(Folf_Gaming.NAME))
                    || player.getName().equals(Component.literal(Kira_uwu69.NAME))) {

                int energon = player.getPersistentData().getInt(HixlePodsOrigins.MODID + "_Energon");

                if (event.getItem().getItem().equals(ItemInit.ENERGON_CUBE.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 60000); }
                if (event.getItem().getItem().equals(ItemInit.SYNTH_EN_CUBE.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 65000); }
                if (event.getItem().getItem().equals(ItemInit.DARK_ENERGON_CUBE.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 70000); }
                if (event.getItem().getItem().equals(ItemInit.RED_ENERGON_CUBE.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 20000); }

                if (event.getItem().getItem().equals(ItemInit.REFINED_ENERGON.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 8500); }
                if (event.getItem().getItem().equals(ItemInit.REFINED_SYNTH_EN.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 9000); }
                if (event.getItem().getItem().equals(ItemInit.REFINED_DARK_ENERGON.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 9500); }
                if (event.getItem().getItem().equals(ItemInit.REFINED_RED_ENERGON.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 2200); }

                if (event.getItem().getItem().equals(ItemInit.ENERGON_BITS.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 2000); }
                if (event.getItem().getItem().equals(ItemInit.SYNTH_EN_BITS.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 3000); }
                if (event.getItem().getItem().equals(ItemInit.DARK_ENERGON_BITS.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 4000); }
                if (event.getItem().getItem().equals(ItemInit.RED_ENERGON_BITS.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 1000); }

                if (event.getItem().getItem().equals(ItemInit.ENERJOLLY.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 5000); }
                if (event.getItem().getItem().equals(ItemInit.HYPER_ENERJOLLY.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon - 7000); }
                if (event.getItem().getItem().equals(ItemInit.BATTLE_DONUT.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 25000); }
                if (event.getItem().getItem().equals(ItemInit.ENERGON_PIZZA.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon",  energon + 50000); }

                if (event.getItem().getItem().equals(ItemInit.ENERGON_LOW_GRADE_DRINK.get())) {
                    if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
                        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 55000);
                    } else {
                        player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 5000);
                    }
                }

                if (event.getItem().getItem().equals(ItemInit.ENERGON_MID_GRADE_DRINK.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 14000); }
                if (event.getItem().getItem().equals(ItemInit.SYNTH_EN_MID_GRADE_DRINK.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 14000); }
                if (event.getItem().getItem().equals(ItemInit.DARK_MID_GRADE_DRINK.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 14000); }
                if (event.getItem().getItem().equals(ItemInit.RED_MID_GRADE_DRINK.get())) { player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 14000); }

                if (event.getItem().getItem().equals(ItemInit.ENERGON_HIGH_GRADE_DRINK.get())) {
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 12000);
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 20, 0, true, false, true));
                }

                if (event.getItem().getItem().equals(ItemInit.DARK_HIGH_GRADE_DRINK.get())) {
                    player.getPersistentData().putInt(HixlePodsOrigins.MODID + "_Energon", energon + 12000);
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60 * 20, 0, true, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60 * 20, 0, true, false, true));
                    player.addEffect(new MobEffectInstance(EffectsInit.MALFUNCTION.get(), 60 * 20, 0, true, false, true));
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

            if (player.getName().equals(Component.literal(CatGirlSeeka.NAME))) {

                int chance = OriginsUtil.randomInt(1, 100);

                if (chance <= 20) {

                    ItemEntity item = new ItemEntity(player.getLevel(), player.position().x(), player.position().y(), player.position().z(),
                            new ItemStack(ItemInit.DRAGON_SCALE.get(), 1));

                    item.position().equals(player.position());

                    player.getLevel().addFreshEntity(item);
                }
            }

            if (player.getName().equals(Component.literal(TricoFan.NAME))) {

                if (OriginsUtil.randomInt(1, 100) <= 40) {

                    Entity entity = EntityType.SILVERFISH.create(player.getLevel());

                    entity.moveTo(player.position());

                    //if (event.getSource().getEntity() instanceof ) {
                        //entity
                    //}

                    player.getLevel().addFreshEntity(entity);
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

            if (player.getName().equals(Component.literal(TricoFan.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.SILVERFISH_HURT, SoundSource.PLAYERS, 1, 2);
            }

            if (player.getName().equals(Component.literal(gh0stlure.NAME))) {

                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.BAT_HURT, SoundSource.PLAYERS, 1, 1);

                if (event.getSource().equals(DamageSource.FLY_INTO_WALL)) {
                    event.setAmount(event.getAmount() * 2);
                }
            }

            if (player.getName().equals(Component.literal(AmbrosiaElf.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.BELL_BLOCK, SoundSource.PLAYERS, 1, 2);
            }
            if (player.getName().equals(Component.literal(Blakpaw2244.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.BELL_BLOCK, SoundSource.PLAYERS, 1, 0);
            }
            if (player.getName().equals(Component.literal(HixlePod.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1, 2);
            }
            if (player.getName().equals(Component.literal(Folf_Gaming.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1, 1.5f);
            }
            if (player.getName().equals(Component.literal(Kira_uwu69.NAME))) {
                player.getLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1, 0.5f);
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
