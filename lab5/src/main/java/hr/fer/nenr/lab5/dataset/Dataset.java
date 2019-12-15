package hr.fer.nenr.lab5.dataset;

public interface Dataset {

    int inputDimension();

    int outputDimension();

    double[] inputAt(int index);

    double[] outputAt(int index);

    int sampleCount();
}
