package com.hixlepod.hixlepodsorigins.client.NPC.Model;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;

public class NPCModel<T extends PathfinderMob> extends HumanoidModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID, "npcmodel"), "main");
    public static final ModelLayerLocation INNER_ARMOUR = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID, "npcmodel"), "inner_armor");
    public static final ModelLayerLocation OUTER_ARMOUR = new ModelLayerLocation(new ResourceLocation(HixlePodsOrigins.MODID, "npcmodel"), "outer_armor");

    public NPCModel(ModelPart p_170677_) {
        super(p_170677_);
    }
}
