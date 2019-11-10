package hr.fer.nenr.lab3.inference;

public class ProductInferenceEngine implements InferenceEngine {

    @Override
    public double infer(double[] muValues) {
        double product = 1.0;
        for (double value : muValues) {
            product *= value;
        }
        return product;
    }
}
