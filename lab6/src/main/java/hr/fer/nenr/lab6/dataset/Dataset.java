package hr.fer.nenr.lab6.dataset;

public interface Dataset {

    double xAt(int index);

    double yAt(int index);

    double outputAt(int index);

    int sampleCount();
}
