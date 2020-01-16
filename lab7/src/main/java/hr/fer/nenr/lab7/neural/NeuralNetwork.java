package hr.fer.nenr.lab7.neural;

import hr.fer.nenr.lab7.dataset.Dataset;

public class NeuralNetwork {

    private static final int MIN_LAYERS = 3;

    private int[] layerSizes;
    private double[][] neuronOutputs;

    private int parameterCount;

    public NeuralNetwork(int... layerSizes) {
        if (layerSizes == null || layerSizes.length < MIN_LAYERS) {
            throw new IllegalArgumentException("Expected " + MIN_LAYERS + " or more layer sizes");
        }

        this.layerSizes = layerSizes;
        this.neuronOutputs = new double[layerSizes.length - 1][];
        for (int i = 0; i < neuronOutputs.length - 1; i++) {
            neuronOutputs[i] = new double[layerSizes[i + 1]];
        }

        calculateParameterCount();
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public int getOutputLength() {
        return layerSizes[layerSizes.length - 1];
    }

    public double meanSquareError(Dataset dataset, double[] parameters, double[] output) {
        double sum = 0.0;
        int sampleCount = dataset.sampleCount();
        for (int i = 0; i < sampleCount; i++) {
            calculateOutput(dataset.inputAt(i), parameters, output);
            double[] expected = dataset.outputAt(i);
            for (int j = 0; j < output.length; j++) {
                sum += Math.pow(expected[j] - output[j], 2.0);
            }
        }

        return sum / sampleCount;
    }

    public double meanSquareError(Dataset dataset, double[] parameters) {
        return meanSquareError(dataset, parameters, new double[dataset.outputDimension()]);
    }

    public double classificationAccuracy(Dataset dataset, double[] parameters, double[] output) {
        int correctCount = 0;
        int sampleCount = dataset.sampleCount();
        for (int i = 0; i < sampleCount; i++) {
            calculateOutput(dataset.inputAt(i), parameters, output);
            int outputClass = 0;
            double max = output[0];
            for (int j = 1; j < output.length; j++) {
                if (output[j] > max) {
                    max = output[j];
                    outputClass = j;
                }
            }

            double[] expected = dataset.outputAt(i);
            int expectedClass = 0;
            while (expected[expectedClass] == 0.0) {
                expectedClass++;
            }

            if (outputClass == expectedClass) correctCount++;
        }

        return (double) correctCount / sampleCount;
    }

    public double classificationAccuracy(Dataset dataset, double[] parameters) {
        return classificationAccuracy(dataset, parameters, new double[dataset.outputDimension()]);
    }

    public void calculateOutput(double[] input, double[] parameters, double[] output) {
        neuronOutputs[neuronOutputs.length - 1] = output;
        calculateSimilarityOutputs(input, parameters);
        calculateOtherOutputs(parameters);
    }

    private void calculateSimilarityOutputs(double[] input, double[] parameters) {
        int weightIndex = 0;
        for (int i = 0; i < layerSizes[1]; i++) {
            double sum = 0.0;
            for (int j = 0; j < layerSizes[0]; j++) {
                double x = input[j];
                double w = parameters[weightIndex++];
                double s = parameters[weightIndex++];

                sum += Math.abs((x - w) / s);
            }
            neuronOutputs[0][i] = 1.0 / (1.0 + sum);
        }
    }

    private void calculateOtherOutputs(double[] parameters) {
        int weightIndex = 2 * layerSizes[0] * layerSizes[1];
        for (int i = 1; i < neuronOutputs.length; i++) {
            for (int layerNeuron = 0; layerNeuron < neuronOutputs[i].length; layerNeuron++) {
                double net = parameters[weightIndex++]; // Neuron's bias value.
                for (int j = 0; j < neuronOutputs[i - 1].length; j++) {
                    net += neuronOutputs[i - 1][j] * parameters[weightIndex++];
                }
                net = sigmoid(net);
                neuronOutputs[i][layerNeuron] = net;
            }
        }
    }

    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private void calculateParameterCount() {
        parameterCount = 2 * layerSizes[0] * layerSizes[1];
        for (int i = 1; i < layerSizes.length - 1; i++) {
            int currentSize = layerSizes[i];
            int nextSize = layerSizes[i + 1];
            parameterCount += (currentSize + 1) * nextSize;
        }
    }
}
