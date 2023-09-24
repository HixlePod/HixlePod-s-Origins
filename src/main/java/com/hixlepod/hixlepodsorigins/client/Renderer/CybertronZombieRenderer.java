package com.hixlepod.hixlepodsorigins.client.Renderer;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CybertronHostileCowModel;
import com.hixlepod.hixlepodsorigins.client.Renderer.Model.CybertronZombieModel;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronHostileCow;
import com.hixlepod.hixlepodsorigins.common.Entities.EntityCybertronZombie;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CybertronZombieRenderer extends MobRenderer<EntityCybertronZombie, CybertronZombieModel<EntityCybertronZombie>> {
    private static final ResourceLocation CYBER_ZOMBIE_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/cybertron_entities/hostiles/cybertron_zombie.png");

    public CybertronZombieRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new CybertronZombieModel<>(p_173956_.bakeLayer(ModelLayers.ZOMBIE)), 0.7F);
    }

    public ResourceLocation getTextureLocation(EntityCybertronZombie p_114029_) {
        return CYBER_ZOMBIE_LOCATION;
    }
}