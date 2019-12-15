package hr.fer.nenr.lab5.dataset;

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

    public static Dataset loadFromFile(Path path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Files.newInputStream(path)))) {
            String[] parts = firstLineParts(reader);
            int inputDimension = checkDimension(parts[0]);
            int outputDimension = checkDimension(parts[1]);
            return readDataset(reader, inputDimension, outputDimension);
        }
    }

    private static String[] firstLineParts(BufferedReader reader)
            throws IOException {
        String line = reader.readLine();
        if (line == null) {
            throw new IllegalArgumentException("Input file is empty");
        }

        line = line.trim();
        String[] parts = line.split("\\s+");
        checkPartsLength(parts, 2, 1);

        return parts;
    }

    private static Dataset readDataset(BufferedReader reader,
            int inputDimension, int outputDimension) throws IOException {
        List<double[]> inputs = new ArrayList<>();
        List<double[]> outputs = new ArrayList<>();

        for (int i = 0; ; i++) {
            String line = reader.readLine();
            if (line == null) break;

            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            checkPartsLength(parts, inputDimension + outputDimension, i + 1);

            inputs.add(parseInput(parts, inputDimension, i));
            outputs.add(parseOutput(parts, inputDimension, outputDimension, i));
        }

        return new ReadOnlyDataset(inputs, outputs);
    }

    private static double[] parseInput(String[] parts,
            int inputDimension, int line) {
        double[] input = new double[inputDimension];
        for (int i = 0; i < inputDimension; i++) {
            input[i] = checkValue(parts[i], line);
        }
        return input;
    }

    private static double[] parseOutput(String[] parts,
            int inputDimension, int outputDimension, int line) {
        double[] output = new double[outputDimension];
        for (int i = 0; i < outputDimension; i++) {
            output[i] = checkValue(parts[inputDimension + i], line);
        }
        return output;
    }

    private static int checkDimension(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Line 1: expected integer dimension value, but got: " + s
            );
        }
    }

    private static double checkValue(String s, int line) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Line " + line
                    + ": expected a real value, but got: " + s);
        }
    }

    private static void checkPartsLength(String[] parts, int expected, int line) {
        if (parts.length != expected) {
            throw new IllegalArgumentException(
                    "Line " + line + ": expected " + expected
                            + " values, but got: " + parts.length);
        }
    }
}
