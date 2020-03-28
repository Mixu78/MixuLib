package com.github.mixu78.mixulib.image;

import com.github.mixu78.mixulib.MixuLib;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageUtils {
    public void replacePixels(File imageFile, Color targetColor, Color newColor) {
        BufferedImage image = null;
        File f = null;

        try {
            f = imageFile;
            image = ImageIO.read(f);
        } catch(IOException e){
            MixuLib.logger.error(e);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixelData = raster.getPixel(x, y, (int[]) null);
                if (pixelData[0] == targetColor.getRed() && pixelData[1] == targetColor.getGreen() && pixelData[2] == targetColor.getBlue()) {
                    pixelData[0] = newColor.getRed();
                    pixelData[1] = newColor.getGreen();
                    pixelData[2] = newColor.getBlue();
                    raster.setPixel(x, y, pixelData);
                }
            }
        }
    }
}
