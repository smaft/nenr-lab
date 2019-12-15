package hr.fer.nenr.lab5.javafx.drawing.testing;

import hr.fer.nenr.lab5.javafx.drawing.DrawingBoard;
import hr.fer.nenr.lab5.neural.Layer;
import hr.fer.nenr.lab5.neural.NeuralNetwork;
import hr.fer.nenr.lab5.preprocessing.ClassificationTestingProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class ClassifierTestingPane extends BorderPane {

    public ClassifierTestingPane(Stage stage, Scene settingsScene,
            NeuralNetwork network) {
        ClassificationOverview classificationOverview = new ClassificationOverview(
                classCount(network)
        );
        DrawingBoard drawingBoard = new DrawingBoard(
                new ClassificationTestingProcessor(
                        network,
                        classificationOverview.getSeries()
                )
        );

        Button backButton = new Button("Back...");
        backButton.setOnAction(e -> {
            stage.close();
            stage.setScene(settingsScene);
            stage.show();
        });

        setTop(classificationOverview);
        setCenter(drawingBoard);
        setBottom(backButton);
        setAlignment(backButton, Pos.CENTER);
        setStyle("-fx-background-color: white");
        setPadding(new Insets(0.0, 0.0, 10.0, 0.0));
    }

    private int classCount(NeuralNetwork network) {
        List<Layer> layers = network.getLayers();
        return layers.get(layers.size() - 1).outputs.length;
    }
}
