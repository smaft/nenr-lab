package hr.fer.nenr.lab5.javafx.settings;

import javafx.scene.control.Spinner;

public class IterationLimitSetting extends AbstractSetting {

    private Spinner<Integer> spinner = new Spinner<>(1, 10_000_000, 10_000, 5_000);

    public IterationLimitSetting() {
        super("Iteration limit:");

        getChildren().add(spinner);
    }

    public int getIterationLimit() {
        return spinner.getValue();
    }
}
