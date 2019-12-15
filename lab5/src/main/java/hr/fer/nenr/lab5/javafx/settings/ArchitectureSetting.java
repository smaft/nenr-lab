package hr.fer.nenr.lab5.javafx.settings;

import hr.fer.nenr.lab5.neural.NeuralNetwork;
import javafx.scene.control.TextField;

public class ArchitectureSetting extends AbstractSetting {

    private TextField textField = new TextField();

    public ArchitectureSetting() {
        super("Hidden-layer architecture:");

        textField.setPromptText("e.g. 10x8x6");
        getChildren().add(textField);
    }

    public NeuralNetwork getNetwork(int inputDimension, int outputDimension) {
        String[] parts = textField.getText().split("\\D+");
        int[] layerSizes = new int[parts.length + 2];
        layerSizes[0] = inputDimension;
        for (int i = 0; i < parts.length; i++) {
            layerSizes[i + 1] = Integer.parseInt(parts[i]);
        }
        layerSizes[layerSizes.length - 1] = outputDimension;
        return new NeuralNetwork(layerSizes);
    }
}
