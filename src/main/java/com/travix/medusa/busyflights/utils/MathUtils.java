package com.travix.medusa.busyflights.utils;

public class MathUtils {

    private MathUtils() {
    }

    public static double roundTo2Decimals(double fare){
            return (double) Math.round(fare * 100) / 100;
    }
}
