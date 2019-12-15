package hr.fer.nenr.lab5.dataset;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public final class DatasetSaver {

    private DatasetSaver() {
    }

    public static void saveToFile(Dataset dataset, Path path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                Files.newOutputStream(path)))) {
            int inputDimension = dataset.inputDimension();
            int outputDimension = dataset.outputDimension();
            writeDimensions(writer, inputDimension, outputDimension);
            writeDataset(writer, dataset);
        }
    }

    private static void writeDimensions(BufferedWriter writer,
            int inputDimension, int outputDimension) throws IOException {
        writer.write("" + inputDimension + " " + outputDimension + "\n");
    }

    private static void writeDataset(BufferedWriter writer, Dataset dataset)
            throws IOException {
        for (int i = 0; i < dataset.sampleCount(); i++) {
            writeValues(writer, dataset.inputAt(i));
            writeValues(writer, dataset.outputAt(i));
            writer.write('\n');
        }
    }

    private static void writeValues(BufferedWriter writer, double[] values)
            throws IOException {
        for (double value : values) {
            writer.write(String.valueOf(value));
            writer.write(' ');
        }
    }
}
