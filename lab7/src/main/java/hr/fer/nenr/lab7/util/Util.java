package hr.fer.nenr.lab7.util;

public final class Util {

    private Util() {
    }

    public static double round(double value, int decimalPlaces) {
        double factor = Math.pow(10.0, decimalPlaces);
        return Math.round(value * factor) / factor;
    }

    public static double round(double value) {
        return round(value, 2);
    }

    public static double[] round(double[] values, int decimalPlaces) {
        double[] rounded = new double[values.length];
        for (int i = 0; i < rounded.length; i++) {
            rounded[i] = round(values[i], decimalPlaces);
        }
        return rounded;
    }

    public static double[] round(double[] values) {
        return round(values, 3);
    }
}
