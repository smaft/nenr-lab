package hr.fer.nenr.lab3.inference;

public class MinInferenceEngine implements InferenceEngine {

    @Override
    public double infer(double[] muValues) {
        double min = muValues[0];
        for (int i = 1; i < muValues.length; i++) {
            double value = muValues[i];
            if (value < min) min = value;
        }
        return min;
    }
}
