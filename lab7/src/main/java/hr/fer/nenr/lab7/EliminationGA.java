package hr.fer.nenr.lab7;

import hr.fer.nenr.lab7.algorithm.EliminationGeneticAlgorithm;
import hr.fer.nenr.lab7.dataset.Dataset;
import hr.fer.nenr.lab7.dataset.DatasetLoader;
import hr.fer.nenr.lab7.neural.NeuralNetwork;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;
import hr.fer.nenr.lab7.util.Util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class EliminationGA {

    private static final String DATASET_PATH = "dataset.txt";

    public static void main(String[] args) {
        NeuralNetwork network = new NeuralNetwork(2, 8, 3);
//        NeuralNetwork network = new NeuralNetwork(2, 8, 4, 3);
//        NeuralNetwork network = new NeuralNetwork(2, 6, 4, 3);
        Dataset dataset = null;
        try {
            dataset = DatasetLoader.loadFromFile(Paths.get(DATASET_PATH), 2, 3);
        } catch (IOException e) {
            System.err.println(DATASET_PATH + " not found");
            System.exit(1);
        }

        DoubleArraySolution solution = new EliminationGeneticAlgorithm(
                network, dataset,
                7, 500_000, 1e-7
        ).optimize();

        System.out.println();
        System.out.println("Parameters:");
        System.out.println(Arrays.toString(solution.chromosome));

        System.out.println("Rounded parameters:");
        System.out.println(Arrays.toString(Util.round(solution.chromosome)));

        double accuracy = network.classificationAccuracy(dataset, solution.chromosome);
        System.out.println();
        System.out.println("Accuracy = " + accuracy);
    }
}
