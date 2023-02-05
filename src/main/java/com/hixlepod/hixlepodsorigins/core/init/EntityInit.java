package com.hixlepod.hixlepodsorigins.core.init;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.EntityOverrides.WanderingTraderOverride;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityCompass;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityEcho;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityPumkin;
import com.hixlepod.hixlepodsorigins.common.Pets.EntityRune;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HixlePodsOrigins.MODID);

    public static final RegistryObject<EntityType<EntityEcho>> ECHO = ENTITIES.register("echo",
            () -> EntityType.Builder.of(EntityEcho::new, MobCategory.CREATURE).sized(0.6F, 0.85F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "echo").toString()));

    public static final RegistryObject<EntityType<EntityCompass>> COMPASS = ENTITIES.register("compass",
            () -> EntityType.Builder.of(EntityCompass::new, MobCategory.CREATURE).sized(0.5F, 0.9F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "compass").toString()));

    public static final RegistryObject<EntityType<EntityRune>> RUNE = ENTITIES.register("rune",
            () -> EntityType.Builder.of(EntityRune::new, MobCategory.CREATURE).fireImmune().sized(0.6F, 1.8F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "rune").toString()));

    public static final RegistryObject<EntityType<EntityPumkin>> PUMKIN = ENTITIES.register("pumkin",
            () -> EntityType.Builder.of(EntityPumkin::new, MobCategory.CREATURE).fireImmune().sized(1.0F, 1.0F).
                    build(new ResourceLocation(HixlePodsOrigins.MODID, "pumkin").toString()));

}
