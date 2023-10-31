package com.hixlepod.hixlepodsorigins.client.Renderer.Bosses.Corruptling;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.client.Model.Bosses.Corruptling.CorruptlingModel;
import com.hixlepod.hixlepodsorigins.common.Entities.Bosses.Corruptling.EntityCorruptling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CorruptlingRenderer<T extends EntityCorruptling> extends MobRenderer<T, CorruptlingModel<T>> {

    private static final ResourceLocation CORRUPTLING_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/entities/bosses/corruptling.png");

    public CorruptlingRenderer(EntityRendererProvider.Context context) {
        super(context, new CorruptlingModel<>(context.bakeLayer(CorruptlingModel.LAYER_LOCATION)), 0.8F);
    }

    public ResourceLocation getTextureLocation(EntityCorruptling p_113972_) {
        return CORRUPTLING_LOCATION;
    }
}
