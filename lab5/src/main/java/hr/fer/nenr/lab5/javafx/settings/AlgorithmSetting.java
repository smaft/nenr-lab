package hr.fer.nenr.lab5.javafx.settings;

import hr.fer.nenr.lab5.dataset.Dataset;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class AlgorithmSetting extends AbstractSetting {

    private ChoiceBox<AlgorithmOption> choiceBox;

    public AlgorithmSetting(Dataset dataset) {
        super("Algorithm:");

        choiceBox = new ChoiceBox<>(
                FXCollections.observableArrayList(AlgorithmOption.getValidOptions(dataset))
        );
        choiceBox.setConverter(AlgorithmOption.STRING_CONVERTER);
        choiceBox.getSelectionModel().selectFirst();

        getChildren().addAll(choiceBox);
    }

    public AlgorithmOption getSelectedItem() {
        return choiceBox.getSelectionModel().getSelectedItem();
    }
}
