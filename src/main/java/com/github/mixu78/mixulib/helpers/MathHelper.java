package com.github.mixu78.mixulib.helpers;

public class MathHelper {

    public static int clamp(int input, int min, int max) {
        return input <= min ? min : input >= max ? max : input;
    }

    public static float clamp(float input, float min, float max) {
        return input <= min ? min : input >= max ? max : input;
    }

    public static double clamp(double input, double min, double max) {
        return input <= min ? min : input >= max ? max : input;
    }
}
