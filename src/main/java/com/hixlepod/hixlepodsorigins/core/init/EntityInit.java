package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.*;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss.EntityBetaScraplet;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.ScrapletBoss.EntityScrapletBoss;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityBooNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityNimbusNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntitySmudgeNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.Pets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HixlePodsOrigins.MODID);

    //Mobs
    public static final RegistryObject<EntityType<EntityBlueSlime>> BLUE_SLIME = ENTITIES.register("blue_slime",
            () -> EntityType.Builder.of(EntityBlueSlime::new, MobCategory.MONSTER).sized(2.04F, 2.04F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "blue_slime").toString()));

    //Pets
    public static final RegistryObject<EntityType<EntityEcho>> ECHO = ENTITIES.register("echo",
            () -> EntityType.Builder.of(EntityEcho::new, MobCategory.CREATURE).sized(0.6F, 0.85F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "echo").toString()));

    public static final RegistryObject<EntityType<EntityCompass>> COMPASS = ENTITIES.register("compass",
            () -> EntityType.Builder.of(EntityCompass::new, MobCategory.CREATURE).fireImmune().sized(0.5F, 0.9F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "compass").toString()));

    public static final RegistryObject<EntityType<EntityRune>> RUNE = ENTITIES.register("rune",
            () -> EntityType.Builder.of(EntityRune::new, MobCategory.CREATURE).fireImmune().sized(0.6F, 1.8F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "rune").toString()));

    public static final RegistryObject<EntityType<EntityPumkin>> PUMKIN = ENTITIES.register("pumkin",
            () -> EntityType.Builder.of(EntityPumkin::new, MobCategory.CREATURE).sized(1.0F, 1.0F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "pumkin").toString()));

    public static final RegistryObject<EntityType<EntityDragonSlayer>> DRAGON_SLAYER = ENTITIES.register("dragon_slayer",
            () -> EntityType.Builder.of(EntityDragonSlayer::new, MobCategory.CREATURE).fireImmune().sized(0.5F, 3.0F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "dragon_slayer").toString()));

    public static final RegistryObject<EntityType<EntityPossum>> POSSUM = ENTITIES.register("possum",
            () -> EntityType.Builder.of(EntityPossum::new, MobCategory.CREATURE).fireImmune().sized(0.25F, 0.25F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "possum").toString()));


    //CYBERTRON ENTITIES
    public static final RegistryObject<EntityType<EntityCybertronPig>> CYBERTRON_PIG = ENTITIES.register("cybertron_pig",
            () -> EntityType.Builder.of(EntityCybertronPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_pig").toString()));

    public static final RegistryObject<EntityType<EntityCybertronChicken>> CYBERTRON_CHICKEN = ENTITIES.register("cybertron_chicken",
            () -> EntityType.Builder.of(EntityCybertronChicken::new, MobCategory.CREATURE).sized(0.4F, 0.7F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_chicken").toString()));

    public static final RegistryObject<EntityType<EntityCybertronCow>> CYBERTRON_COW = ENTITIES.register("cybertron_cow",
            () -> EntityType.Builder.of(EntityCybertronCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_cow").toString()));

    public static final RegistryObject<EntityType<EntityCybertronHorse>> CYBERTRON_HORSE = ENTITIES.register("cybertron_horse",
            () -> EntityType.Builder.of(EntityCybertronHorse::new, MobCategory.CREATURE).sized(2.0947266F, 2.4F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_horse").toString()));

    public static final RegistryObject<EntityType<EntityTen>> TEN = ENTITIES.register("ten",
            () -> EntityType.Builder.of(EntityTen::new, MobCategory.CREATURE).sized(2.8F, 5.4F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "ten").toString()));


    //Hostile
    public static final RegistryObject<EntityType<EntityScraplet>> SCRAPLET = ENTITIES.register("scraplet",
            () -> EntityType.Builder.of(EntityScraplet::new, MobCategory.MONSTER).sized(0.6F, 0.4F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "scraplet").toString()));

    public static final RegistryObject<EntityType<EntityCybertronCreeper>> CYBERTRON_CREEPER = ENTITIES.register("cybertron_creeper",
            () -> EntityType.Builder.of(EntityCybertronCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_creeper").toString()));

    public static final RegistryObject<EntityType<EntityCybertronHostileCow>> CYBERTRON_HOSTILE_COW = ENTITIES.register("cybertron_hostile_cow",
            () -> EntityType.Builder.of(EntityCybertronHostileCow::new, MobCategory.CREATURE).sized(0.9F, 1.4F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_hostile_cow").toString()));

    public static final RegistryObject<EntityType<EntityCybertronZombie>> CYBERTRON_ZOMBIE = ENTITIES.register("cybertron_zombie",
            () -> EntityType.Builder.of(EntityCybertronZombie::new, MobCategory.MONSTER).sized(0.75F, 2.4375F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "cybertron_zombie").toString()));

    //Bosses

     //Scraplet boss & Co
    public static final RegistryObject<EntityType<EntityScrapletBoss>> SCRAPLET_BOSS = ENTITIES.register("scraplet_boss",
            () -> EntityType.Builder.of(EntityScrapletBoss::new, MobCategory.MONSTER).sized(2.8F, 1.8F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "scraplet_boss").toString()));

    public static final RegistryObject<EntityType<EntityBetaScraplet>> BETA_SCRAPLET = ENTITIES.register("beta_scraplet",
            () -> EntityType.Builder.of(EntityBetaScraplet::new, MobCategory.MONSTER).sized(0.3F, 0.2F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "beta_scraplet").toString()));

     //Corruptling


    //NPC
    public static final RegistryObject<EntityType<EntityNimbusNPC>> NPC_NIMBUS = ENTITIES.register("npc_nimbus",
            () -> EntityType.Builder.of(EntityNimbusNPC::new, MobCategory.CREATURE).sized(0.6F, 1.8F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "npc_nimbus").toString()));

    public static final RegistryObject<EntityType<EntityBooNPC>> NPC_BOO = ENTITIES.register("npc_boo",
            () -> EntityType.Builder.of(EntityBooNPC::new, MobCategory.CREATURE).sized(0.6F, 1.8F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "npc_boo").toString()));

    public static final RegistryObject<EntityType<EntitySmudgeNPC>> NPC_SMUDGE = ENTITIES.register("npc_smudge",
            () -> EntityType.Builder.of(EntitySmudgeNPC::new, MobCategory.CREATURE).sized(0.6F, 1.8F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "npc_smudge").toString()));

}
