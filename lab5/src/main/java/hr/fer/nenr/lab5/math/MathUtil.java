package hr.fer.nenr.lab5.math;

public class MathUtil {

    private MathUtil() {
    }

    public static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static void etaAdd(double[][] matrix, double[][] delta, double eta) {
        for (int i = 0; i < matrix.length; i++) {
            etaAdd(matrix[i], delta[i], eta);
        }
    }

    public static void etaAdd(double[] array, double[] delta, double eta) {
        for (int i = 0; i < array.length; i++) {
            array[i] += delta[i] * eta;
        }
    }

    public static double sum(double[] array) {
        double sum = 0.0;
        for (double value : array) {
            sum += value;
        }
        return sum;
    }

    public static double distance(Point first, Point second) {
        return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
    }
}
