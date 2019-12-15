package hr.fer.nenr.lab5.javafx.settings;

import javafx.scene.control.Spinner;

public class AcceptableErrorSetting extends AbstractSetting {

    private Spinner<Double> spinner = new Spinner<>(0.0, 10.0, 0.0, 0.01);

    public AcceptableErrorSetting() {
        super("Acceptable error:");

        getChildren().add(spinner);
    }

    public double getAcceptableError() {
        return spinner.getValue();
    }
}
