package hr.fer.nenr.lab5.javafx.settings;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.dataset.Util;
import hr.fer.nenr.lab5.neural.algorithm.BatchBackpropagation;
import hr.fer.nenr.lab5.neural.algorithm.MiniBatchBackpropagation;
import hr.fer.nenr.lab5.neural.algorithm.NeuralNetworkAlgorithm;
import hr.fer.nenr.lab5.neural.algorithm.StochasticBackpropagation;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmOption {

    public static final Map<String, AlgorithmOption> OPTIONS = new HashMap<>();
    public static final AlgorithmOption BATCH = new AlgorithmOption(
            "Batch Backpropagation", BatchBackpropagation.PROTOTYPE);
    public static final AlgorithmOption STOCHASTIC = new AlgorithmOption(
            "Stochastic Backpropagation", StochasticBackpropagation.PROTOTYPE);
    public static final AlgorithmOption MINI_BATCH = new AlgorithmOption(
            "Mini-batch Backpropagation", MiniBatchBackpropagation.PROTOTYPE);

    public static final StringConverter<AlgorithmOption> STRING_CONVERTER = new StringConverter<AlgorithmOption>() {
        @Override
        public String toString(AlgorithmOption object) {
            return object.name;
        }

        @Override
        public AlgorithmOption fromString(String string) {
            return OPTIONS.get(string);
        }
    };

    String name;
    NeuralNetworkAlgorithm prototype;

    public AlgorithmOption(String name, NeuralNetworkAlgorithm prototype) {
        this.name = name;
        this.prototype = prototype;
        OPTIONS.put(name, this);
    }

    public static List<AlgorithmOption> getValidOptions(Dataset dataset) {
        List<AlgorithmOption> options = new ArrayList<>(OPTIONS.values());
        if (!Util.miniBatchesPossible(dataset)) {
            options.remove(MINI_BATCH);
        }
        return options;
    }
}
