package hr.fer.nenr.lab5.javafx.menu.items;

import hr.fer.nenr.lab5.drawing.DatasetModel;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SampleCountMenuItem extends MenuItem {

    private static final String TEXT = "Sample Count...";

    public SampleCountMenuItem(DatasetModel datasetModel) {
        super(TEXT);

        setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(String.valueOf(datasetModel.getM()));
            dialog.setGraphic(null);
            dialog.setHeaderText(null);
            dialog.setTitle("Set Sample Count");
            dialog.setContentText("Sample count:");

            boolean disabled = !datasetModel.isEmpty();
            dialog.getEditor().setDisable(disabled);

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(s -> {
                if (disabled) return;

                String errorMessage;
                try {
                    int m = Integer.parseInt(s);
                    if (m > 0) {
                        datasetModel.setM(m);
                        return;
                    }

                    errorMessage = "Sample count must be positive, but was: " + s;
                } catch (Exception ex) {
                    errorMessage = ex.getMessage();
                }

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(errorMessage);
                alert.showAndWait();
            });
        });
    }
}
