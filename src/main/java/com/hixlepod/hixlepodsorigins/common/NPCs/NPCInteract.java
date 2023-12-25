package com.hixlepod.hixlepodsorigins.common.NPCs;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityBooNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntityNimbusNPC;
import com.hixlepod.hixlepodsorigins.common.Entities.NPC.EntitySmudgeNPC;
import com.hixlepod.hixlepodsorigins.common.NPCs.Dialogues.NimbusDialogue;
import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HixlePodsOrigins.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NPCInteract {

    @SubscribeEvent
    public void onClick(PlayerInteractEvent.EntityInteract event) {

        if (!event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getHand() != InteractionHand.MAIN_HAND) {
            return;
        }

        if (event.getEntity() instanceof Player) {
            Player player = event.getEntity();

            if (event.getTarget() instanceof EntityNimbusNPC) {
                Nimbus(player, (EntityNimbusNPC) event.getTarget());
            }

            if (event.getTarget() instanceof EntitySmudgeNPC) {

            }

            if (event.getTarget() instanceof EntityBooNPC) {

            }
        }
    }

    public void Nimbus(Player player, EntityNimbusNPC nimbusNPC) {
        ItemStack itemStack = player.getMainHandItem();

        if (this.isTicket(itemStack)) {

            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            CompoundTag wantedTag = itemStack.getOrCreateTagElement("Wanted");

            ItemStack wanted = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(HixlePodsOrigins.MODID, wantedTag.getString("Item")
                    .toLowerCase()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "_"))), wantedTag.getInt("Count"));


            if (player.getInventory().contains(wanted)) {

                CompoundTag rewardTag = itemStack.getOrCreateTagElement("Reward");

                ItemStack reward = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(HixlePodsOrigins.MODID, rewardTag.getString("Item")
                        .toLowerCase()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "_"))),

                        rewardTag.getInt("Count"));

                player.getInventory().add(reward);

            } else {
                //TODO: Tell em u gotta do it

            }
        } else {
            NimbusDialogue.HelloThere(player);
        }
    }

    public boolean isTicket(ItemStack itemStack) {
        return itemStack.is(ItemInit.QUEST_TICKET.get());
    }
}
