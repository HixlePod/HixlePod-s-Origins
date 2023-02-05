package com.hixlepod.hixlepodsorigins.common.EntityOverrides;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

public class WanderingTraderOverride extends WanderingTrader {

    public WanderingTraderOverride(EntityType<? extends WanderingTrader> p_35843_, Level p_35844_) {
        super(p_35843_, p_35844_);
    }

    @Override
    protected void updateTrades() {

        VillagerTrades.ItemListing[] avillagertradesitemlisting = VillagerTrades.WANDERING_TRADER_TRADES.get(1);
        VillagerTrades.ItemListing[] avillagertradesitemlisting1 = VillagerTrades.WANDERING_TRADER_TRADES.get(2);
        if (avillagertradesitemlisting != null && avillagertradesitemlisting1 != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, avillagertradesitemlisting, 7);
            int i = this.random.nextInt(avillagertradesitemlisting1.length);
            VillagerTrades.ItemListing villagertradesitemlisting = avillagertradesitemlisting1[i];
            MerchantOffer merchantoffer = villagertradesitemlisting.getOffer(this, this.random);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }

        }

        super.updateTrades();
    }
}
