package com.hixlepod.hixlepodsorigins.core.init.CreateAPI;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.common.blocks.CybertronPortalForcer;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import com.simibubi.create.foundation.utility.BlockFace;
import com.simibubi.create.foundation.utility.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class PortalTracksSupport {

    public static Pair<ServerLevel, BlockFace> cybertron(Pair<ServerLevel, BlockFace> inbound) {
        ResourceKey<Level> aetherLevelKey =
                ResourceKey.create(Registries.DIMENSION, new ResourceLocation(HixlePodsOrigins.MODID, "cybertron"));
        return standardPortalProvider(inbound, Level.OVERWORLD, aetherLevelKey, level -> {
            try {
                return new CybertronPortalForcer(level);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return level.getPortalForcer();
        });
    }

    public static Pair<ServerLevel, BlockFace> standardPortalProvider(Pair<ServerLevel, BlockFace> inbound,
                                                                      ResourceKey<Level> firstDimension, ResourceKey<Level> secondDimension,
                                                                      Function<ServerLevel, ITeleporter> customPortalForcer) {
        ServerLevel level = inbound.getFirst();
        ResourceKey<Level> resourcekey = level.dimension() == secondDimension ? firstDimension : secondDimension;
        MinecraftServer minecraftserver = level.getServer();
        ServerLevel otherLevel = minecraftserver.getLevel(resourcekey);

        if (otherLevel == null || !minecraftserver.isNetherEnabled())
            return null;

        BlockFace inboundTrack = inbound.getSecond();
        BlockPos portalPos = inboundTrack.getConnectedPos();
        BlockState portalState = level.getBlockState(portalPos);
        ITeleporter teleporter = customPortalForcer.apply(otherLevel);

        SuperGlueEntity probe = new SuperGlueEntity(level, new AABB(portalPos));
        probe.setYRot(inboundTrack.getFace()
                .toYRot());
        probe.setPortalEntrancePos();

        PortalInfo portalinfo = teleporter.getPortalInfo(probe, otherLevel, probe::findDimensionEntryPoint);
        if (portalinfo == null)
            return null;

        BlockPos otherPortalPos = BlockPos.containing(portalinfo.pos);
        BlockState otherPortalState = otherLevel.getBlockState(otherPortalPos);
        if (otherPortalState.getBlock() != portalState.getBlock())
            return null;

        Direction targetDirection = inboundTrack.getFace();
        if (targetDirection.getAxis() == otherPortalState.getValue(BlockStateProperties.HORIZONTAL_AXIS))
            targetDirection = targetDirection.getClockWise();
        BlockPos otherPos = otherPortalPos.relative(targetDirection);
        return Pair.of(otherLevel, new BlockFace(otherPos, targetDirection.getOpposite()));
    }

}
