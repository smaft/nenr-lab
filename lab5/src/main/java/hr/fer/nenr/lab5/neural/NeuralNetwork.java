package hr.fer.nenr.lab5.neural;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.nenr.lab5.math.MathUtil.sigmoid;

public class NeuralNetwork implements Classifier {

    private List<Layer> layers = new ArrayList<>();

    public NeuralNetwork(int... layerSizes) {
        if (layerSizes == null || layerSizes.length < 2) {
            throw new IllegalArgumentException("Expected 2 or more layer sizes");
        }

        for (int i = 0; i < layerSizes.length - 1; i++) {
            layers.add(new Layer(layerSizes[i], layerSizes[i + 1]));
        }
        layers.add(new Layer(layerSizes[layerSizes.length - 1]));
    }

    public List<Layer> getLayers() {
        return layers;
    }

    @Override
    public double[] calculateOutput(double[] input) {
        Layer first = layers.get(0);
        first.outputs = input;

        for (int i = 1; i < layers.size(); i++) {
            propagate(layers.get(i), layers.get(i - 1));
        }

        return layers.get(layers.size() - 1).outputs;
    }

    private void propagate(Layer current, Layer previous) {
        for (int c = 0; c < current.outputs.length; c++) {
            double net = previous.biases[c];
            for (int p = 0; p < previous.outputs.length; p++) {
                net += previous.weights[p][c] * previous.outputs[p];
            }
            current.outputs[c] = sigmoid(net);
        }
    }
}
