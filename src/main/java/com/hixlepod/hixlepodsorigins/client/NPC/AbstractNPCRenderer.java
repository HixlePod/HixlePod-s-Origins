package com.hixlepod.hixlepodsorigins.client.NPC;

import com.hixlepod.hixlepodsorigins.client.NPC.Model.NPCModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.PathfinderMob;

public abstract class AbstractNPCRenderer<T extends PathfinderMob, M extends NPCModel<T>> extends HumanoidMobRenderer<T, M> {

    public AbstractNPCRenderer(EntityRendererProvider.Context context, M p_173911_, M p_173912_, M p_173913_) {
        super(context, p_173911_, 0.5F);
        //this.addLayer(new HumanoidArmorLayer<>(this, p_173912_, p_173913_));
    }

}
