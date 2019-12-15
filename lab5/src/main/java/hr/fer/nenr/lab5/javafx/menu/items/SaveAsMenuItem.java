package hr.fer.nenr.lab5.javafx.menu.items;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.dataset.DatasetSaver;
import hr.fer.nenr.lab5.drawing.DatasetModel;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveAsMenuItem extends MenuItem {

    private static final String TEXT = "Save As...";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd-MM-yyyy-HH:mm:ss");

    public SaveAsMenuItem(Stage stage, FileChooser chooser, DatasetModel datasetModel) {
        super(TEXT);

        setOnAction(e -> {
            chooser.setInitialFileName(LocalDateTime.now().format(FORMATTER) + ".txt");

            File file = chooser.showSaveDialog(stage);
            if (file == null) return;

            try {
                Dataset dataset = datasetModel.toDataset();
                DatasetSaver.saveToFile(dataset, file.toPath());
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
    }
}
