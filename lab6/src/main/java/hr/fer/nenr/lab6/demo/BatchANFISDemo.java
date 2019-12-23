package hr.fer.nenr.lab6.demo;

import hr.fer.nenr.lab6.algorithm.BatchANFISOptimization;
import hr.fer.nenr.lab6.dataset.Dataset;
import hr.fer.nenr.lab6.dataset.DatasetGenerator;
import hr.fer.nenr.lab6.neurofuzzy.ANFIS;

import static hr.fer.nenr.lab6.math.Functions.DEFAULT_FUNCTION;

public class BatchANFISDemo {

    public static void main(String[] args) {
        ANFIS anfis = new ANFIS(5);
        Dataset dataset = new DatasetGenerator(-4, 4, 1, DEFAULT_FUNCTION).generate();
        new BatchANFISOptimization(anfis, dataset, 10_000, 1e-5, 1e-3).optimize();

        DemoUtil.printStatistics(dataset, anfis);
    }
}
