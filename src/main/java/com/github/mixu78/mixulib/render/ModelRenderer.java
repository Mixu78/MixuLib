package com.github.mixu78.mixulib.render;

import com.google.common.collect.Lists;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3i;

import java.util.List;

public class ModelRenderer {
    //Credits for most of this stuff goes to brandon3055

    public static void renderBakedQuads(List<BakedQuad> quads) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        for (BakedQuad quad : quads) {
            buffer.begin(7, DefaultVertexFormats.ITEM);

            buffer.addVertexData(quad.getVertexData());
            buffer.putColorRGB_F4(1,1,1);

            Vec3i quadVec = quad.getFace().getDirectionVec();
            buffer.putNormal(quadVec.getX(), quadVec.getY(), quadVec.getZ());
        }
    }

    public static void renderBakedQuadsRGB(List<BakedQuad> quads, float r, float g, float b) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        for (BakedQuad quad : quads) {
            buffer.begin(7, DefaultVertexFormats.ITEM);

            buffer.addVertexData(quad.getVertexData());
            buffer.putColorRGB_F4(r,g,b);

            Vec3i quadVec = quad.getFace().getDirectionVec();
            buffer.putNormal(quadVec.getX(), quadVec.getY(), quadVec.getZ());
        }
    }

    public static void renderBakedQuadsARGB(List<BakedQuad> quads, int argb) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        for (BakedQuad quad : quads) {
            buffer.begin(7, DefaultVertexFormats.ITEM);

            buffer.addVertexData(quad.getVertexData());
            buffer.putColor4(argb);

            Vec3i quadVec = quad.getFace().getDirectionVec();
            buffer.putNormal(quadVec.getX(), quadVec.getY(), quadVec.getZ());
        }
    }
}
