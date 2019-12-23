package hr.fer.nenr.lab6.demo;

import hr.fer.nenr.lab6.dataset.Dataset;
import hr.fer.nenr.lab6.dataset.DatasetGenerator;
import hr.fer.nenr.lab6.math.Functions;

public class DatasetGeneratorDemo {

    public static void main(String[] args) {
        Dataset dataset = new DatasetGenerator(
                -4, 4, 1, Functions.DEFAULT_FUNCTION
        ).generate();

        DemoUtil.printDataset(dataset);
    }
}
