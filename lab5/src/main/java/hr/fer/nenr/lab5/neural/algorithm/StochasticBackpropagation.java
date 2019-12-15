package hr.fer.nenr.lab5.neural.algorithm;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.neural.NeuralNetwork;

public class StochasticBackpropagation extends AbstractBackpropagation {

    public static final StochasticBackpropagation PROTOTYPE = new StochasticBackpropagation();

    private StochasticBackpropagation() {
    }

    public StochasticBackpropagation(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        super(dataset, network, learningRate);
    }

    @Override
    public NeuralNetworkAlgorithm create(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        return new StochasticBackpropagation(dataset, network, learningRate);
    }

    @Override
    public void iteration(int currentIteration) {
        clearHelperLayers();

        int i = currentIteration % dataset.sampleCount();
        network.calculateOutput(dataset.inputAt(i));

        updateLastLayerErrors(dataset.outputAt(i));
        updateHiddenLayerErrors();
        updateWeightChanges();
        updateBiasChanges();
        updateWeightsAndBiases();
    }
}
