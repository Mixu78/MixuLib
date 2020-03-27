package com.github.mixu78.mixulib.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class LineRenderer {

    public static class LaserConfig {

        public static final int MAX_TIMER = 100;

        public LaserConfig(BlockPos first, BlockPos second,
                           double rotationTime, float alpha, double beamWidth, float[] color) {
            this.first = first;
            this.second = second;
            this.rotationTime = rotationTime;
            this.alpha = alpha;
            this.beamWidth = beamWidth;
            this.color = color;
        }

        BlockPos first;
        BlockPos second;
        double rotationTime;
        float alpha;
        double beamWidth;
        float[] color;
        public int timer = LaserConfig.MAX_TIMER;


        @Override
        public String toString() {
            return second + " : " + first;
        }
    }

    @SideOnly(Side.CLIENT)
    public static void renderLaser(LaserConfig conf) {
        if (conf.first == null || conf.second == null) {
            return;
        }
        double offsetX = 0;
        double offsetY = 0;
        double offsetZ = 0;
        LineRenderer.renderLaser(
                conf.first.getX() + offsetX, conf.first.getY() + offsetY, conf.first.getZ() + offsetZ,
                conf.second.getX() + offsetX, conf.second.getY() + offsetY, conf.second.getZ() + offsetZ,
                conf.rotationTime, conf.alpha, conf.beamWidth, conf.color, conf.timer);
    }

        //I got this code from Cyclic by Lothrazar, who in turn got it from actually additions, see below:

        //I got this function from ActuallyAdditions by Ellpeck
        // source https://github.com/Ellpeck/ActuallyAdditions/blob/08d0e8b7fb463054e3f392ddbb2a2ca2e2877000/src/main/java/de/ellpeck/actuallyadditions/mod/util/AssetUtil.java#L257
        // who in turn left their source where they got it, copied verabitm:
        //Thanks to feldim2425 for this.
        //I can't do rendering code. Ever.
        public static final int MAX_LIGHT_X = 0xF000F0;
        public static final int MAX_LIGHT_Y = MAX_LIGHT_X;

        @SideOnly(Side.CLIENT)
        public static void renderLaser(double firstX, double firstY, double firstZ,
                                       double secondX, double secondY, double secondZ,
                                       double rotationTime, float alpha, double beamWidth, float[] color, double timer) {
            Tessellator tessy = Tessellator.getInstance();
            BufferBuilder buffer = tessy.getBuffer();
            World world = Minecraft.getMinecraft().world;
            float r = color[0];
            float g = color[1];
            float b = color[2];
            Vec3d vecFirst = new Vec3d(firstX, firstY, firstZ);
            Vec3d vecSecond = new Vec3d(secondX, secondY, secondZ);
            Vec3d combinedVec = vecSecond.subtract(vecFirst);
            double rot = rotationTime > 0 ? (360D * ((world.getTotalWorldTime() % rotationTime) / rotationTime)) : 0;
            double pitch = Math.atan2(combinedVec.y, Math.sqrt(combinedVec.x * combinedVec.x + combinedVec.z * combinedVec.z));
            double yaw = Math.atan2(-combinedVec.z, combinedVec.x);
            double length = combinedVec.lengthVector();
            length = length * (timer / (LaserConfig.MAX_TIMER * 1.0));
            GlStateManager.pushMatrix();
            GlStateManager.translate(firstX - TileEntityRendererDispatcher.staticPlayerX, firstY - TileEntityRendererDispatcher.staticPlayerY, firstZ - TileEntityRendererDispatcher.staticPlayerZ);
            GlStateManager.rotate((float) (180 * yaw / Math.PI), 0, 1, 0);
            GlStateManager.rotate((float) (180 * pitch / Math.PI), 0, 0, 1);
            GlStateManager.rotate((float) rot, 1, 0, 0);
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
            for (double i = 0; i < 4; i++) {//four corners of the quad
                double width = beamWidth * (i / 4.0);
                buffer.pos(length, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(0, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
                buffer.pos(length, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            }
            tessy.draw();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }
    }

