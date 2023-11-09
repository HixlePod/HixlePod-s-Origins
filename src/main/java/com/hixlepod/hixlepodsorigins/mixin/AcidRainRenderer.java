package com.hixlepod.hixlepodsorigins.mixin;

import com.hixlepod.hixlepodsorigins.HixlePodsOrigins;
import com.hixlepod.hixlepodsorigins.core.init.DimensionInit;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.renderer.LevelRenderer.getLightColor;

@Mixin(LevelRenderer.class)
public abstract class AcidRainRenderer {

    @Shadow
    private ClientLevel level;

    @Shadow
    private final Minecraft minecraft;

    @Shadow
    private int ticks;

    @Shadow
    private final float[] rainSizeX;

    @Shadow
    private final float[] rainSizeZ;

    private static final ResourceLocation RAIN_LOCATION = new ResourceLocation("textures/environment/rain.png");
    private static final ResourceLocation SNOW_LOCATION = new ResourceLocation("textures/environment/snow.png");
    private static final ResourceLocation ACID_RAIN_LOCATION = new ResourceLocation(HixlePodsOrigins.MODID, "textures/environment/acid_rain.png");


    protected AcidRainRenderer(Minecraft minecraft, float[] rainSizeX, float[] rainSizeZ) {

        this.rainSizeX = new float[1024];
        this.rainSizeZ = new float[1024];
        this.minecraft = minecraft;
    }

    @Inject(method = "renderSnowAndRain", at = @At(value = "HEAD"), cancellable = true)
    private void renderSnowAndRain(LightTexture p_109704_, float p_109705_, double p_109706_, double p_109707_, double p_109708_, final CallbackInfo info) {
        info.cancel();

        if (!this.level.effects().renderSnowAndRain(this.level, this.ticks, p_109705_, p_109704_, p_109706_, p_109707_, p_109708_)) {
            float f = this.minecraft.level.getRainLevel(p_109705_);
            if (!(f <= 0.0F)) {
                p_109704_.turnOnLightLayer();
                Level level = this.minecraft.level;
                int i = Mth.floor(p_109706_);
                int j = Mth.floor(p_109707_);
                int k = Mth.floor(p_109708_);
                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder bufferbuilder = tesselator.getBuilder();
                RenderSystem.disableCull();
                RenderSystem.enableBlend();
                RenderSystem.enableDepthTest();
                int l = 5;
                if (Minecraft.useFancyGraphics()) {
                    l = 10;
                }

                RenderSystem.depthMask(Minecraft.useShaderTransparency());
                int i1 = -1;
                float f1 = (float)this.ticks + p_109705_;
                RenderSystem.setShader(GameRenderer::getParticleShader);
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for(int j1 = k - l; j1 <= k + l; ++j1) {
                    for(int k1 = i - l; k1 <= i + l; ++k1) {
                        int l1 = (j1 - k + 16) * 32 + k1 - i + 16;
                        double d0 = (double)this.rainSizeX[l1] * 0.5;
                        double d1 = (double)this.rainSizeZ[l1] * 0.5;
                        blockpos$mutableblockpos.set((double)k1, p_109707_, (double)j1);
                        Biome biome = (Biome)level.getBiome(blockpos$mutableblockpos).value();
                        if (biome.hasPrecipitation()) {
                            int i2 = level.getHeight(Heightmap.Types.MOTION_BLOCKING, k1, j1);
                            int j2 = j - l;
                            int k2 = j + l;
                            if (j2 < i2) {
                                j2 = i2;
                            }

                            if (k2 < i2) {
                                k2 = i2;
                            }

                            int l2 = i2;
                            if (i2 < j) {
                                l2 = j;
                            }

                            if (j2 != k2) {
                                RandomSource randomsource = RandomSource.create((long)(k1 * k1 * 3121 + k1 * 45238971 ^ j1 * j1 * 418711 + j1 * 13761));
                                blockpos$mutableblockpos.set(k1, j2, j1);
                                Biome.Precipitation biome$precipitation = biome.getPrecipitationAt(blockpos$mutableblockpos);
                                float f6;
                                float f8;
                                if (biome$precipitation == Biome.Precipitation.RAIN) {
                                    if (i1 != 0) {
                                        if (i1 >= 0) {
                                            tesselator.end();
                                        }

                                        i1 = 0;
                                        if (this.minecraft.level.dimension() == DimensionInit.CYBERTRON_KEY) {
                                            RenderSystem.setShaderTexture(0, ACID_RAIN_LOCATION);
                                        } else {
                                            RenderSystem.setShaderTexture(0, RAIN_LOCATION);
                                        }

                                        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                                    }

                                    int i3 = this.ticks + k1 * k1 * 3121 + k1 * 45238971 + j1 * j1 * 418711 + j1 * 13761 & 31;
                                    f6 = -((float)i3 + p_109705_) / 32.0F * (3.0F + randomsource.nextFloat());
                                    double d2 = (double)k1 + 0.5 - p_109706_;
                                    double d4 = (double)j1 + 0.5 - p_109708_;
                                    float f3 = (float)Math.sqrt(d2 * d2 + d4 * d4) / (float)l;
                                    f8 = ((1.0F - f3 * f3) * 0.5F + 0.5F) * f;
                                    blockpos$mutableblockpos.set(k1, l2, j1);
                                    int j3 = getLightColor(level, blockpos$mutableblockpos);
                                    bufferbuilder.vertex((double)k1 - p_109706_ - d0 + 0.5, (double)k2 - p_109707_, (double)j1 - p_109708_ - d1 + 0.5).uv(0.0F, (float)j2 * 0.25F + f6).color(1.0F, 1.0F, 1.0F, f8).uv2(j3).endVertex();
                                    bufferbuilder.vertex((double)k1 - p_109706_ + d0 + 0.5, (double)k2 - p_109707_, (double)j1 - p_109708_ + d1 + 0.5).uv(1.0F, (float)j2 * 0.25F + f6).color(1.0F, 1.0F, 1.0F, f8).uv2(j3).endVertex();
                                    bufferbuilder.vertex((double)k1 - p_109706_ + d0 + 0.5, (double)j2 - p_109707_, (double)j1 - p_109708_ + d1 + 0.5).uv(1.0F, (float)k2 * 0.25F + f6).color(1.0F, 1.0F, 1.0F, f8).uv2(j3).endVertex();
                                    bufferbuilder.vertex((double)k1 - p_109706_ - d0 + 0.5, (double)j2 - p_109707_, (double)j1 - p_109708_ - d1 + 0.5).uv(0.0F, (float)k2 * 0.25F + f6).color(1.0F, 1.0F, 1.0F, f8).uv2(j3).endVertex();
                                } else if (biome$precipitation == Biome.Precipitation.SNOW) {
                                    if (i1 != 1) {
                                        if (i1 >= 0) {
                                            tesselator.end();
                                        }

                                        i1 = 1;
                                        RenderSystem.setShaderTexture(0, SNOW_LOCATION);
                                        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                                    }

                                    float f5 = -((float)(this.ticks & 511) + p_109705_) / 512.0F;
                                    f6 = (float)(randomsource.nextDouble() + (double)f1 * 0.01 * (double)((float)randomsource.nextGaussian()));
                                    float f7 = (float)(randomsource.nextDouble() + (double)(f1 * (float)randomsource.nextGaussian()) * 0.001);
                                    double d3 = (double)k1 + 0.5 - p_109706_;
                                    double d5 = (double)j1 + 0.5 - p_109708_;
                                    f8 = (float)Math.sqrt(d3 * d3 + d5 * d5) / (float)l;
                                    float f9 = ((1.0F - f8 * f8) * 0.3F + 0.5F) * f;
                                    blockpos$mutableblockpos.set(k1, l2, j1);
                                    int k3 = getLightColor(level, blockpos$mutableblockpos);
                                    int l3 = k3 >> 16 & '\uffff';
                                    int i4 = k3 & '\uffff';
                                    int j4 = (l3 * 3 + 240) / 4;
                                    int k4 = (i4 * 3 + 240) / 4;
                                    bufferbuilder.vertex((double)k1 - p_109706_ - d0 + 0.5, (double)k2 - p_109707_, (double)j1 - p_109708_ - d1 + 0.5).uv(0.0F + f6, (float)j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                    bufferbuilder.vertex((double)k1 - p_109706_ + d0 + 0.5, (double)k2 - p_109707_, (double)j1 - p_109708_ + d1 + 0.5).uv(1.0F + f6, (float)j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                    bufferbuilder.vertex((double)k1 - p_109706_ + d0 + 0.5, (double)j2 - p_109707_, (double)j1 - p_109708_ + d1 + 0.5).uv(1.0F + f6, (float)k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                    bufferbuilder.vertex((double)k1 - p_109706_ - d0 + 0.5, (double)j2 - p_109707_, (double)j1 - p_109708_ - d1 + 0.5).uv(0.0F + f6, (float)k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                }
                            }
                        }
                    }
                }

                if (i1 >= 0) {
                    tesselator.end();
                }

                RenderSystem.enableCull();
                RenderSystem.disableBlend();
                p_109704_.turnOffLightLayer();
            }

        }
    }
}
