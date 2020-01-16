package hr.fer.nenr.lab7.algorithm.evaluator;

import hr.fer.nenr.lab7.dataset.Dataset;
import hr.fer.nenr.lab7.neural.NeuralNetwork;
import hr.fer.nenr.lab7.solution.DoubleArraySolution;

public class NeuralNetworkDatasetEvaluator implements Evaluator<DoubleArraySolution> {

    private NeuralNetwork network;
    private Dataset dataset;

    private double[] outputCache;

    public NeuralNetworkDatasetEvaluator(NeuralNetwork network, Dataset dataset) {
        this.network = network;
        this.dataset = dataset;
        this.outputCache = new double[network.getOutputLength()];
    }

    @Override
    public void evaluate(DoubleArraySolution solution) {
        solution.error = network.meanSquareError(dataset, solution.chromosome, outputCache);
    }
}
