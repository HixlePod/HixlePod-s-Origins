package com.hixlepod.hixlepodsorigins.common.items.OriginWeapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.hixlepod.hixlepodsorigins.core.utils.OriginsUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;

public class BetsyBattleAxeItem extends Item {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    double ATTACK_DAMAGE = 14;
    double ATTACK_SPEED = OriginsUtil.returnAttackSpeed(0.4);

    public BetsyBattleAxeItem(Properties p_41383_) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", ATTACK_SPEED, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }
}
