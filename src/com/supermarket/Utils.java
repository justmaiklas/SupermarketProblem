package com.supermarket;

public class Utils {
    public static double RoundDouble(double value, int precision) {
        precision = (int)Math.pow(10,precision);
        value = value * precision;
        value = Math.round(value);
        return value / precision * 1.0;

    }
}
