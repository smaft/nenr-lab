package hr.fer.nenr.lab5.javafx.menu.items;

import javafx.application.Platform;
import javafx.scene.control.MenuItem;

public class QuitMenuItem extends MenuItem {

    private static final String TEXT = "Quit";

    public QuitMenuItem() {
        super(TEXT);

        setOnAction(e -> Platform.exit());
    }
}
