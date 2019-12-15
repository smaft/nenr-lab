package hr.fer.nenr.lab5.javafx.menu.items;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.dataset.DatasetLoader;
import hr.fer.nenr.lab5.drawing.DatasetModel;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LoadMenuItem extends MenuItem {

    private static final String TEXT = "Load...";

    public LoadMenuItem(Stage stage, FileChooser chooser, DatasetModel datasetModel) {
        super(TEXT);

        setOnAction(e -> {
            File file = chooser.showOpenDialog(stage);
            if (file == null) return;

            try {
                Dataset dataset = DatasetLoader.loadFromFile(file.toPath());
                datasetModel.reset(dataset);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
    }
}
