package com.hixlepod.hixlepodsorigins.common.Entities.Pets.CompassOreTracking;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;

import java.util.ArrayList;

public class BlockStoreBuilder {
    public static ArrayList<BlockData> list = new ArrayList<BlockData>();

    public static void init() {


        list.add(new BlockData("CoalOre", Ores.COAL.toString(), new OutlineColor(32, 32, 32), false, 0));
        list.add(new BlockData("IronOre", Ores.IRON.toString(), new OutlineColor(228, 192, 170), false, 0));
        list.add(new BlockData("CopperOre", Ores.COPPER.toString(), new OutlineColor(183, 112, 58), false, 0));
        list.add(new BlockData("RedstoneOre", Ores.REDSTONE.toString(), new OutlineColor(255, 0, 0), false, 0));
        list.add(new BlockData("LapisOre", Ores.LAPIS.toString(), new OutlineColor(10, 10, 255), false, 0));
        list.add(new BlockData("GoldOre", Ores.GOLD.toString(), new OutlineColor(212, 175, 55), false, 0));
        list.add(new BlockData("DiamondOre", Ores.DIAMOND.toString(), new OutlineColor(61, 219, 227), false, 0));
        list.add(new BlockData("EmeraldOre", Ores.EMERALD.toString(), new OutlineColor(0, 255, 0), false, 0));
        list.add(new BlockData("NetherQuartzOre", Ores.QUARTZ.toString(), new OutlineColor(255, 255, 255), false, 0));
        list.add(new BlockData("NetheriteOre", Ores.NETHERITE.toString(), new OutlineColor(255, 165, 0), false, 0));


        HixlePodsOrigins.blockStore.setStore(list);

    }
}
