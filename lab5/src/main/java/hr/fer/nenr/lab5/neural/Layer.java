package hr.fer.nenr.lab5.neural;

import static hr.fer.nenr.lab5.rng.RandomUtil.randomArray;
import static hr.fer.nenr.lab5.rng.RandomUtil.randomMatrix;

public class Layer {

    public double[] outputs;
    public double[][] weights;
    public double[] biases;

    public Layer(int size, int nextSize) {
        outputs = new double[size];
        weights = nextSize > 0 ? randomMatrix(size, nextSize) : null;
        biases = nextSize > 0 ? randomArray(nextSize) : null;
    }

    public Layer(int size) {
        this(size, 0);
    }
}
