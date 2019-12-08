package hr.fer.nenr.lab4.dataset;

public interface ReadOnlyDataset2D {

    int getSampleSize();

    double xInputAt(int sampleIndex);

    double yInputAt(int sampleIndex);

    double outputAt(int sampleIndex);
}
