package com.github.mixu78.mixulib.helpers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public class RenderHelper {
    public static void translateToPlayerCenter(@Nonnull EntityPlayer player, @Nonnull EntityPlayer viewingPlayer, @Nonnull float partialRenderTick) {
        if (viewingPlayer != player) {
            double translationXLT = player.prevPosX - viewingPlayer.prevPosX;
            double translationYLT = player.prevPosY - viewingPlayer.prevPosY;
            double translationZLT = player.prevPosZ - viewingPlayer.prevPosZ;

            double translationX = translationXLT + (((player.posX - viewingPlayer.posX) - translationXLT) * partialRenderTick);
            double translationY = translationYLT + (((player.posY - viewingPlayer.posY) - translationYLT) * partialRenderTick);
            double translationZ = translationZLT + (((player.posZ - viewingPlayer.posZ) - translationZLT) * partialRenderTick);

            GlStateManager.translate(translationX, translationY + 1.1, translationZ);
        }
        else GlStateManager.translate(0 , 1.15, 0);
    }
}
