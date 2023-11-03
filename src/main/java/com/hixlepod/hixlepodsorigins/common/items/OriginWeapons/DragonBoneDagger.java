package com.hixlepod.hixlepodsorigins.common.items.OriginWeapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DragonBoneDagger extends Item  {

    private Multimap<Attribute, AttributeModifier> defaultModifiers;

    double ATTACK_DAMAGE = 0;
    double ATTACK_SPEED = OriginsUtil.returnAttackSpeed(0);

    public DragonBoneDagger(Properties p_41383_) {
        super(p_41383_);
    }

    private void BuildNewAttributes() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", ATTACK_SPEED, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {

        if (entity instanceof Player) {
            Player player = (Player) entity;

            ItemStack MainHand = player.getMainHandItem();
            ItemStack Offhand = player.getOffhandItem();

            if (MainHand.getItem().equals(ItemInit.DRAGON_BONE_DAGGER.get()) && Offhand.getItem().equals(ItemInit.DRAGON_BONE_DAGGER.get())) {
                ATTACK_DAMAGE = 4;
                ATTACK_SPEED = OriginsUtil.returnAttackSpeed(4);
                BuildNewAttributes();
            } else {
                ATTACK_DAMAGE = 2;
                ATTACK_SPEED = OriginsUtil.returnAttackSpeed(2);
                BuildNewAttributes();
            }
        }
        super.inventoryTick(itemStack, level, entity, p_41407_, p_41408_);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }
}
