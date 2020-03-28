package com.github.mixu78.mixulib.util;

import codechicken.lib.colour.ColourRGBA;

import java.awt.*;
import java.util.UUID;

import static com.github.mixu78.mixulib.util.UUIDUtils.UUID2Byte;

public class Colors {

    public static Color UUID2RGB(UUID uuid) {
        byte[] bytes = UUID2Byte(uuid);

        int r = Math.abs(bytes[0]);
        int g = Math.abs(bytes[1]);
        int b = Math.abs(bytes[2]);

        return new Color(r, g, b);
    }

    public static int UUID2ARGB(UUID uuid) {
        byte[] bytes = UUID2Byte(uuid);

        int r = Math.abs(bytes[0]);
        int g = Math.abs(bytes[1]);
        int b = Math.abs(bytes[2]);
        int a = Math.abs(bytes[3]);

        return new ColourRGBA(r, g, b, a).argb();
    }

    public static int[] getARGB(int ARGB) {
        int a = (ARGB>>24) & 0xff;
        int r = (ARGB>>16) & 0xff;
        int g = (ARGB>>8) & 0xff;
        int b = ARGB & 0xff;

        return new int[]{a, r, g, b};
    }

    public static int toARGB(int a, int r, int g, int b) {
        int ARGBHex = (a<<24) | (r<<16) | (g<<8) | b;
        return ARGBHex;
    }
}
