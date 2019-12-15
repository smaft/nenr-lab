package hr.fer.nenr.lab5.neural.algorithm;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.neural.NeuralNetwork;

public class MiniBatchBackpropagation extends AbstractBackpropagation {

    public static final MiniBatchBackpropagation PROTOTYPE = new MiniBatchBackpropagation();
    private static final int BATCH_SIZE = 2;

    private int samplesPerClass;

    private MiniBatchBackpropagation() {
    }

    public MiniBatchBackpropagation(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        super(dataset, network, learningRate);
        calculateSamplesPerClass();
    }

    @Override
    public NeuralNetworkAlgorithm create(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        return new MiniBatchBackpropagation(dataset, network, learningRate);
    }

    @Override
    public void iteration(int currentIteration) {
        clearHelperLayers();

        int firstClassOffset = (BATCH_SIZE * currentIteration) % samplesPerClass;
        for (int offset = firstClassOffset; offset < dataset.sampleCount(); offset += samplesPerClass) {
            for (int i = offset; i < offset + BATCH_SIZE; i++) {
                network.calculateOutput(dataset.inputAt(i));

                updateLastLayerErrors(dataset.outputAt(i));
                updateHiddenLayerErrors();
                updateWeightChanges();
                updateBiasChanges();
            }
        }
        updateWeightsAndBiases();
    }

    private void calculateSamplesPerClass() {
        for (samplesPerClass = 0; samplesPerClass < dataset.sampleCount(); samplesPerClass++) {
            if (dataset.outputAt(samplesPerClass)[0] != 1.0) break;
        }
    }
}
