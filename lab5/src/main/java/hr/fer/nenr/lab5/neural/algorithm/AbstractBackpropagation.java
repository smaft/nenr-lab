package hr.fer.nenr.lab5.neural.algorithm;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.neural.Layer;
import hr.fer.nenr.lab5.neural.NeuralNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hr.fer.nenr.lab5.math.MathUtil.etaAdd;

public abstract class AbstractBackpropagation extends NeuralNetworkAlgorithm {

    private List<Layer> layers;
    private List<HelperLayer> helpers;

    public AbstractBackpropagation() {
    }

    public AbstractBackpropagation(Dataset dataset,
            NeuralNetwork network, double learningRate) {
        super(dataset, network, learningRate);
        layers = network.getLayers();
        helpers = createHelperLayers(layers);
    }

    protected void clearHelperLayers() {
        clearOutputErrors();
        clearWeightAndBiasChanges();
    }

    protected void updateLastLayerErrors(double[] expected) {
        double[] actual = layers.get(layers.size() - 1).outputs;
        double[] errors = helpers.get(helpers.size() - 1).outputErrors;

        for (int i = 0; i < expected.length; i++) {
            double y = actual[i];
            double t = expected[i];
            errors[i] = y * (1.0 - y) * (t - y);
        }
    }

    protected void updateHiddenLayerErrors() {
        for (int i = layers.size() - 2; i >= 0; i--) {
            double[] actual = layers.get(i).outputs;
            double[][] weights = layers.get(i).weights;
            double[] errors = helpers.get(i).outputErrors;
            double[] nextErrors = helpers.get(i + 1).outputErrors;

            for (int j = 0; j < errors.length; j++) {
                double weightedErrorSum = 0.0;
                for (int k = 0; k < nextErrors.length; k++) {
                    double delta = nextErrors[k];
                    double w = weights[j][k];
                    weightedErrorSum += delta * w;
                }

                double y = actual[j];
                errors[j] = y * (1.0 - y) * weightedErrorSum;
            }
        }
    }

    protected void updateWeightChanges() {
        for (int i = 0; i < helpers.size() - 1; i++) {
            double[][] weightChanges = helpers.get(i).weightChanges;
            double[] outputErrors = helpers.get(i + 1).outputErrors;
            double[] outputs = layers.get(i).outputs;

            for (int j = 0; j < weightChanges.length; j++) {
                for (int l = 0; l < weightChanges[0].length; l++) {
                    double y = outputs[j];
                    double delta = outputErrors[l];
                    weightChanges[j][l] += y * delta;
                }
            }
        }
    }

    protected void updateBiasChanges() {
        for (int i = 0; i < helpers.size() - 1; i++) {
            double[] biasChanges = helpers.get(i).biasChanges;
            double[] outputErrors = helpers.get(i + 1).outputErrors;

            for (int j = 0; j < biasChanges.length; j++) {
                biasChanges[j] += outputErrors[j];
            }
        }
    }

    protected void updateWeightsAndBiases() {
        for (int i = 0; i < layers.size() - 1; i++) {
            etaAdd(layers.get(i).weights, helpers.get(i).weightChanges, learningRate);
            etaAdd(layers.get(i).biases, helpers.get(i).biasChanges, learningRate);
        }
    }

    private List<HelperLayer> createHelperLayers(List<Layer> layers) {
        List<HelperLayer> helperLayers = new ArrayList<>(layers.size());
        for (Layer layer : layers) {
            helperLayers.add(HelperLayer.fromLayer(layer));
        }
        return helperLayers;
    }

    private void clearOutputErrors() {
        for (HelperLayer helper : helpers) {
            Arrays.fill(helper.outputErrors, 0.0);
        }
    }

    private void clearWeightAndBiasChanges() {
        for (int i = 0; i < helpers.size() - 1; i++) {
            HelperLayer helper = helpers.get(i);
            for (double[] neuronWeightChanges : helper.weightChanges) {
                Arrays.fill(neuronWeightChanges, 0.0);
            }
            Arrays.fill(helper.biasChanges, 0.0);
        }
    }

    private static class HelperLayer {

        double[] outputErrors;
        double[][] weightChanges;
        double[] biasChanges;

        public HelperLayer(int size, int nextSize) {
            outputErrors = new double[size];
            weightChanges = nextSize > 0 ? new double[size][nextSize] : null;
            biasChanges = nextSize > 0 ? new double[nextSize] : null;
        }

        public static HelperLayer fromLayer(Layer layer) {
            return new HelperLayer(
                    layer.outputs.length,
                    layer.weights == null ? 0 : layer.weights[0].length
            );
        }
    }
}
