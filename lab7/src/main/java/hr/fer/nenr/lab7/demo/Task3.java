package hr.fer.nenr.lab7.demo;

import hr.fer.nenr.lab7.dataset.DatasetLoader;
import hr.fer.nenr.lab7.neural.NeuralNetwork;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Task3 {

    private static final String DATASET_PATH = "dataset.txt";

    private static final double s1 = 0.05;
    private static final double s2 = 0.1;
    private static final double bias = 0.0;
    private static final double t = 1.0;
    private static final double f = -1.0;

    private static final double[] parameters = new double[]{
            // Similarity neuron weights
            // w_1k, s_1k, w_2k, s_2k
            0.175, s1, 0.75, s2,
            0.375, s1, 0.75, s2,
            0.625, s1, 0.75, s2,
            0.875, s1, 0.75, s2,
            0.175, s1, 0.25, s2,
            0.375, s1, 0.25, s2,
            0.625, s1, 0.25, s2,
            0.875, s1, 0.25, s2,
            // Output neuron weights
            // bias, 8 * w_kl
            bias, f, f, t, f, t, f, f, t,
            bias, t, f, f, t, f, t, f, f,
            bias, f, t, f, f, f, f, t, f,
    };

    public static void main(String[] args) {
        try {
            double accuracy = new NeuralNetwork(2, 8, 3).classificationAccuracy(
                    DatasetLoader.loadFromFile(Paths.get(DATASET_PATH), 2, 3),
                    parameters
            );

            System.out.println("Parameters:");
            System.out.println(Arrays.toString(parameters));

            System.out.println("Accuracy = " + accuracy);
        } catch (IOException e) {
            System.err.println(DATASET_PATH + " not found");
            System.exit(1);
        }
    }
}
