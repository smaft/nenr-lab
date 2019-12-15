package hr.fer.nenr.lab5;

import hr.fer.nenr.lab5.drawing.DatasetModel;
import hr.fer.nenr.lab5.javafx.drawing.DrawingBoard;
import hr.fer.nenr.lab5.javafx.drawing.dataset.DrawnDatasetOverview;
import hr.fer.nenr.lab5.javafx.menu.DatasetMenuBar;
import hr.fer.nenr.lab5.preprocessing.DrawingModelProcessor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DatasetModel datasetModel = new DatasetModel();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white");
        Scene scene = new Scene(root, 800, 600);

        root.setTop(new DatasetMenuBar(primaryStage, datasetModel));
        root.setCenter(new DrawingBoard(new DrawingModelProcessor(datasetModel)));
        root.setRight(new DrawnDatasetOverview(primaryStage, scene, datasetModel));

        primaryStage.setTitle("Lab 5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
