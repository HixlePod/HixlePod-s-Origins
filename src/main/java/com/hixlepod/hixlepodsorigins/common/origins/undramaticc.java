package com.hixlepod.hixlepodsorigins.common.origins;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.origins.AllyIsAngy.AllyIsAngy;
import com.hixlepod.hixlepodsorigins.common.origins.Fudge.FudgeInvisibilityONS2CPacket;
import com.hixlepod.hixlepodsorigins.core.networking.NetworkManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class undramaticc {

    public static String NAME = "saod";

    public static void setAbilityData(Player player) {
        player.getAbilities().setFlyingSpeed(0.03F);
        player.getAbilities().mayfly = true;
        player.getServer().getPlayerList().getPlayerByName(undramaticc.NAME).onUpdateAbilities();
    }

    public static void Ability1(ServerPlayer player) {
        //Fudge105.isInvisible = true;

        for (int i = 0; i < 9; i++) {
            Entity entity = EntityType.ZOMBIE.create(player.getLevel());

            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));

            entity.moveTo(player.position());
            entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_DropItems", true);
            entity.getPersistentData().putBoolean(HixlePodsOrigins.MODID + "_AttackAthena", true);

            player.getLevel().addFreshEntity(entity);
        }
    }
    public static double attack_multiplier;

    public static void tick(Player player) {
        if (player.getName().equals(Component.literal(undramaticc.NAME))) {
            int health = (int) player.getHealth();

            //The lower the health the higher the damage
            attack_multiplier = 3.0 + (20 - health) * 0.175;

            player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attack_multiplier);
        }
    }
}
