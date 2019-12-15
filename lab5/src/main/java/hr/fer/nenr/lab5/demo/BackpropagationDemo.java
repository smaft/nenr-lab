package hr.fer.nenr.lab5.demo;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.dataset.ReadOnlyDataset;
import hr.fer.nenr.lab5.neural.NeuralNetwork;
import hr.fer.nenr.lab5.neural.algorithm.BatchBackpropagation;
import hr.fer.nenr.lab5.neural.algorithm.NeuralNetworkAlgorithm;

import java.util.Arrays;

public class BackpropagationDemo {

    private static final int ITERATIONS = 50_000;

    public static void main(String[] args) {
        Dataset dataset = new ReadOnlyDataset(
                Arrays.asList(
                        new double[]{-1.0},
                        new double[]{-0.8},
                        new double[]{-0.6},
                        new double[]{-0.4},
                        new double[]{-0.2},
                        new double[]{-0.0},
                        new double[]{-0.2},
                        new double[]{-0.4},
                        new double[]{-0.6},
                        new double[]{-0.8},
                        new double[]{-1.0}
                ),
                Arrays.asList(
                        new double[]{1.00},
                        new double[]{0.64},
                        new double[]{0.36},
                        new double[]{0.16},
                        new double[]{0.04},
                        new double[]{0.00},
                        new double[]{0.04},
                        new double[]{0.16},
                        new double[]{0.36},
                        new double[]{0.64},
                        new double[]{1.00}
                )
        );

        NeuralNetworkAlgorithm algorithm = new BatchBackpropagation(
                dataset, new NeuralNetwork(1, 6, 1), 2.5
        );

        for (int i = 0; i < ITERATIONS; i++) {
            algorithm.iteration(i);
        }
        System.out.println(algorithm.meanSquareError());

        algorithm = new BatchBackpropagation(
                dataset, new NeuralNetwork(1, 6, 6, 1), 2.5
        );

        for (int i = 0; i < ITERATIONS; i++) {
            algorithm.iteration(i);
        }
        System.out.println(algorithm.meanSquareError());
    }
}
