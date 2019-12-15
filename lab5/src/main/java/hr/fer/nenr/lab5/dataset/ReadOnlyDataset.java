package hr.fer.nenr.lab5.dataset;

import java.util.List;

public class ReadOnlyDataset implements Dataset {

    private List<double[]> inputs;
    private List<double[]> outputs;

    public ReadOnlyDataset(List<double[]> inputs, List<double[]> outputs) {
        if (inputs.size() != outputs.size() || inputs.size() < 1) {
            throw new IllegalArgumentException("Expected at least 1 sample");
        }

        this.inputs = inputs;
        this.outputs = outputs;
    }

    @Override
    public int inputDimension() {
        return inputs.get(0).length;
    }

    @Override
    public int outputDimension() {
        return outputs.get(0).length;
    }

    @Override
    public double[] inputAt(int index) {
        return inputs.get(index);
    }

    @Override
    public double[] outputAt(int index) {
        return outputs.get(index);
    }

    @Override
    public int sampleCount() {
        return inputs.size();
    }
}
