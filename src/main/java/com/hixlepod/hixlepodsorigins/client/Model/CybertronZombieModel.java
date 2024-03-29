package com.hixlepod.hixlepodsorigins.client.Model;

import com.hixlepod.hixlepodsorigins.common.Entities.cybertron_entities.hostiles.EntityCybertronZombie;
import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelPart;

public class CybertronZombieModel<T extends EntityCybertronZombie> extends AbstractZombieModel<T> {
    public CybertronZombieModel(ModelPart p_171090_) {
        super(p_171090_);
    }

    public boolean isAggressive(T p_104155_) {
        return p_104155_.isAggressive();
    }
}
