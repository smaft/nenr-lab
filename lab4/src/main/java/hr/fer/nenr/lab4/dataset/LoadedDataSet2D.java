package hr.fer.nenr.lab4.dataset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LoadedDataSet2D implements ReadOnlyDataset2D {

    private double[] xInputs;

    private double[] yInputs;

    private double[] outputs;

    public LoadedDataSet2D(double[] xInputs, double[] yInputs, double[] outputs) {
        this.xInputs = xInputs;
        this.yInputs = yInputs;
        this.outputs = outputs;
    }

    public static LoadedDataSet2D loadFromFile(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(path)))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                line = line.trim();
                if (line.isEmpty()) continue;

                lines.add(line);
            }
        }

        double[] xInputs = new double[lines.size()];
        double[] yInputs = new double[lines.size()];
        double[] outputs = new double[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid input line: " + line);
            }

            xInputs[i] = Double.parseDouble(parts[0]);
            yInputs[i] = Double.parseDouble(parts[1]);
            outputs[i] = Double.parseDouble(parts[2]);
        }

        return new LoadedDataSet2D(xInputs, yInputs, outputs);
    }

    @Override
    public int getSampleSize() {
        return outputs.length;
    }

    @Override
    public double xInputAt(int sampleIndex) {
        return xInputs[sampleIndex];
    }

    @Override
    public double yInputAt(int sampleIndex) {
        return yInputs[sampleIndex];
    }

    @Override
    public double outputAt(int sampleIndex) {
        return outputs[sampleIndex];
    }
}
