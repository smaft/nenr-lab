package hr.fer.nenr.lab7.dataset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class DatasetLoader {

    private DatasetLoader() {
    }

    public static Dataset loadFromFile(Path path,
            int inputDimension, int outputDimension) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(path)))) {
            List<double[]> inputs = new ArrayList<>();
            List<double[]> outputs = new ArrayList<>();

            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");

                inputs.add(parseDoubleArray(parts, 0, inputDimension));
                outputs.add(parseDoubleArray(parts, inputDimension, inputDimension + outputDimension));
            }

            return new ReadOnlyDataset(inputs, outputs);
        }
    }

    private static double[] parseDoubleArray(String[] parts, int start, int end) {
        double[] input = new double[end - start];
        for (int i = start; i < end; i++) {
            input[i - start] = Double.parseDouble(parts[i]);
        }
        return input;
    }
}
