package com.hixlepod.hixlepodsorigins.common.Entities.Bosses.EnderDragonKing;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.end.EndDragonFight;

import javax.annotation.Nullable;

public class EnderDragonKing extends Mob {

    @Nullable
    private final EndDragonFight dragonFight;
    private final EnderKingTaskManager taskManager;

    public EnderDragonKing(EntityType<? extends EnderDragonKing> entityType, Level level) {
        super(entityType, level);

        this.noPhysics = true;
        this.noCulling = true;

        this.setHealth(3000f);

        if (level instanceof ServerLevel) {
            this.dragonFight = ((ServerLevel) level).dragonFight();
        } else {
            this.dragonFight = null;
        }

        this.taskManager = new EnderKingTaskManager(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3000.0);
    }
}
