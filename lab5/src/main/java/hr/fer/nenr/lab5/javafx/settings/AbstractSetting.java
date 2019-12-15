package hr.fer.nenr.lab5.javafx.settings;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class AbstractSetting extends HBox {

    private static final double SPACING = 5.0;

    public AbstractSetting(String text) {
        super(SPACING);
        setAlignment(Pos.CENTER);
        getChildren().add(new Label(text));
    }
}
