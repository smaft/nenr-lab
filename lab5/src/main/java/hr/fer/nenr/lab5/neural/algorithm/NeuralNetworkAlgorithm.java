package hr.fer.nenr.lab5.neural.algorithm;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.neural.NeuralNetwork;

public abstract class NeuralNetworkAlgorithm implements ClassifierAlgorithm {

    protected Dataset dataset;
    protected NeuralNetwork network;
    protected double learningRate;

    protected NeuralNetworkAlgorithm() {
    }

    protected NeuralNetworkAlgorithm(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        this.dataset = dataset;
        this.network = network;
        this.learningRate = learningRate;
    }

    public double meanSquareError() {
        int n = dataset.sampleCount();
        double sum = 0;

        for (int i = 0; i < n; i++) {
            double[] actual = network.calculateOutput(dataset.inputAt(i));
            double[] expected = dataset.outputAt(i);

            for (int j = 0; j < actual.length; j++) {
                double delta = actual[j] - expected[j];
                sum += delta * delta;
            }
        }

        return sum / n;
    }

    public abstract NeuralNetworkAlgorithm create(Dataset dataset,
            NeuralNetwork network, double learningRate);
}
