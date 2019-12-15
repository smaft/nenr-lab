package hr.fer.nenr.lab5.neural.algorithm;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.neural.NeuralNetwork;

public class BatchBackpropagation extends AbstractBackpropagation {

    public static final BatchBackpropagation PROTOTYPE = new BatchBackpropagation();

    private BatchBackpropagation() {
    }

    public BatchBackpropagation(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        super(dataset, network, learningRate);
    }

    @Override
    public NeuralNetworkAlgorithm create(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        return new BatchBackpropagation(dataset, network, learningRate);
    }

    @Override
    public void iteration(int currentIteration) {
        clearHelperLayers();

        for (int i = 0; i < dataset.sampleCount(); i++) {
            network.calculateOutput(dataset.inputAt(i));

            updateLastLayerErrors(dataset.outputAt(i));
            updateHiddenLayerErrors();
            updateWeightChanges();
            updateBiasChanges();
        }
        updateWeightsAndBiases();
    }
}
