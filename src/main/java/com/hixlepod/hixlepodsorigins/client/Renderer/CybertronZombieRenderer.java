package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.CybertronZombieModel;
import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityCybertronZombie;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CybertronZombieRenderer extends MobRenderer<EntityCybertronZombie, CybertronZombieModel<EntityCybertronZombie>> {
    private static final ResourceLocation CYBER_ZOMBIE_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/hostiles/cybertron_zombie.png");

    public CybertronZombieRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new CybertronZombieModel<>(p_173956_.bakeLayer(ModelLayers.ZOMBIE)), 0.7F);
    }

    @Override
    protected void scale(EntityCybertronZombie p_113974_, PoseStack p_113975_, float p_113976_) {
        p_113975_.scale(1.25F, 1.25F, 1.25F);
    }

    public ResourceLocation getTextureLocation(EntityCybertronZombie p_114029_) {
        return CYBER_ZOMBIE_LOCATION;
    }
}
