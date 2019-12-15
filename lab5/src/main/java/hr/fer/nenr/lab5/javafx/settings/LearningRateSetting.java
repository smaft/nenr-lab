package hr.fer.nenr.lab5.javafx.settings;

import javafx.scene.control.Spinner;

public class LearningRateSetting extends AbstractSetting {

    private Spinner<Double> spinner = new Spinner<>(0.0, 10.0, 0.2, 0.01);

    public LearningRateSetting() {
        super("Learning rate (eta):");

        getChildren().add(spinner);
    }

    public double getLearningRate() {
        return spinner.getValue();
    }
}
