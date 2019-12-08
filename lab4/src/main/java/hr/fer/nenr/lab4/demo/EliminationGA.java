package hr.fer.nenr.lab4.demo;

import hr.fer.nenr.lab4.algorithm.EliminationGeneticAlgorithm;
import hr.fer.nenr.lab4.algorithm.OptimizationAlgorithm;
import hr.fer.nenr.lab4.dataset.LoadedDataSet2D;
import hr.fer.nenr.lab4.dataset.ReadOnlyDataset2D;
import hr.fer.nenr.lab4.solution.BetaVectorSolution;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class EliminationGA {

    public static void main(String[] args) {
        ReadOnlyDataset2D dataset = loadArgDataset(args);
        if (dataset == null) return;

        OptimizationAlgorithm<BetaVectorSolution> algorithm = new EliminationGeneticAlgorithm(
                50, 10_000, dataset
        );
        BetaVectorSolution solution = algorithm.solve();
        System.out.println(Arrays.toString(solution.getBeta()));
        System.out.println("Error = " + solution.error);
    }

    private static ReadOnlyDataset2D loadArgDataset(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected 1 argument - path to the dataset file");
            return null;
        }

        try {
            return LoadedDataSet2D.loadFromFile(Paths.get(args[0]));
        } catch (IOException e) {
            System.err.println("Invalid dataset file: " + args[0]);
            return null;
        }
    }
}
