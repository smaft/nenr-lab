package hr.fer.nenr.lab5.javafx.menu.items;

import hr.fer.nenr.lab5.drawing.DatasetModel;
import javafx.scene.control.MenuItem;

public class NewMenuItem extends MenuItem {

    private static final String TEXT = "New";

    public NewMenuItem(DatasetModel datasetModel) {
        super(TEXT);

        setOnAction(e -> datasetModel.reset());
    }
}
