package hr.fer.nenr.lab5.rng;

import java.util.Random;

public class RandomUtil {

    private RandomUtil() {
    }

    public static double[] randomArray(int size) {
        Random random = RandomSingleton.getInstance().getRandom();

        double[] array = new double[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextDouble() * 2 - 1;
        }
        return array;
    }

    public static double[][] randomMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = randomArray(cols);
        }
        return matrix;
    }
}
