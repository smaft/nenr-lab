package hr.fer.nenr.lab5.javafx.settings;

import hr.fer.nenr.lab5.dataset.Dataset;
import hr.fer.nenr.lab5.javafx.drawing.testing.ClassifierTestingPane;
import hr.fer.nenr.lab5.neural.NeuralNetwork;
import hr.fer.nenr.lab5.neural.algorithm.NeuralNetworkAlgorithm;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrainSettings extends BorderPane {

    private static final double SPACING = 10.0;

    private AlgorithmSetting algorithmSetting;
    private ArchitectureSetting architectureSetting = new ArchitectureSetting();
    private LearningRateSetting learningRateSetting = new LearningRateSetting();
    private IterationLimitSetting iterationLimitSetting = new IterationLimitSetting();
    private AcceptableErrorSetting acceptableErrorSetting = new AcceptableErrorSetting();

    private NeuralNetwork network;

    private Button backButton = new Button("Back...");
    private Button trainButton = new Button("Train");
    private Button stopButton = new Button("Stop");
    private Button tryOutButton = new Button("Try out...");

    private ProgressBar progressBar = new ProgressBar();
    private Label errorProgress = new Label("No error available.");

    public TrainSettings(Stage stage, Scene datasetScene, Dataset dataset) {
        initSettings(dataset);
        initControl(stage, datasetScene, dataset);
        setStyle("-fx-background-color: white");
        setPadding(new Insets(0.0, 0.0, 10.0, 0.0));
    }

    private void initSettings(Dataset dataset) {
        algorithmSetting = new AlgorithmSetting(dataset);
        VBox vBox = new VBox(
                SPACING,
                algorithmSetting,
                architectureSetting,
                learningRateSetting,
                iterationLimitSetting,
                acceptableErrorSetting
        );
        vBox.setAlignment(Pos.CENTER);
        setCenter(vBox);
    }

    private void initControl(Stage stage, Scene datasetScene, Dataset dataset) {
        trainingStopped();

        backButton.setOnAction(e -> {
            stage.close();
            stage.setScene(datasetScene);
            stage.show();
        });

        final NeuralAlgorithmTask[] task = new NeuralAlgorithmTask[1];
        trainButton.setOnAction(e -> {
            try {
                task[0] = new NeuralAlgorithmTask(
                        algorithmSetting.getSelectedItem().prototype,
                        dataset,
                        architectureSetting.getNetwork(
                                dataset.inputDimension(),
                                dataset.outputDimension()
                        ),
                        learningRateSetting.getLearningRate(),
                        iterationLimitSetting.getIterationLimit(),
                        acceptableErrorSetting.getAcceptableError()
                );
                progressBar.progressProperty().bind(task[0].progressProperty());
                errorProgress.textProperty().bind(task[0].messageProperty());
                network = task[0].network;
                new Thread(task[0]).start();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });

        stopButton.setOnAction(e -> task[0].cancel());
        HBox buttonBox = new HBox(SPACING, backButton, trainButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);

        tryOutButton.setDisable(true);
        tryOutButton.setOnAction(e -> {
            stage.close();
            stage.setScene(new Scene(new ClassifierTestingPane(stage, getScene(), network), 800, 600));
            stage.show();
        });
        progressBar.setMinWidth(500.0);
        VBox controlBox = new VBox(SPACING, buttonBox, tryOutButton, progressBar, errorProgress);
        controlBox.setAlignment(Pos.CENTER);
        setBottom(controlBox);
    }

    private void trainingStarted() {
        trainButton.setDisable(true);
        stopButton.setDisable(false);
        backButton.setDisable(true);
        tryOutButton.setDisable(true);
        progressBar.setDisable(false);
        errorProgress.setDisable(false);
    }

    private void trainingStopped() {
        trainButton.setDisable(false);
        stopButton.setDisable(true);
        backButton.setDisable(false);
        tryOutButton.setDisable(false);
        progressBar.setDisable(true);
        errorProgress.setDisable(true);

        progressBar.progressProperty().unbind();
        progressBar.setProgress(0.0);
        errorProgress.textProperty().unbind();
    }

    private class NeuralAlgorithmTask extends Task<Void> {

        NeuralNetwork network;
        int iterationLimit;
        double acceptableError;
        NeuralNetworkAlgorithm algorithm;

        public NeuralAlgorithmTask(NeuralNetworkAlgorithm prototype,
                Dataset dataset, NeuralNetwork network, double learningRate,
                int iterationLimit, double acceptableError) {
            this.network = network;
            this.iterationLimit = iterationLimit;
            this.acceptableError = acceptableError;
            this.algorithm = prototype.create(dataset, network, learningRate);
        }

        @Override
        protected Void call() {
            for (int i = 0; i < iterationLimit; i++) {
                algorithm.iteration(i);
                double error = algorithm.meanSquareError();
                updateMessage(String.valueOf(error));
                if (error <= acceptableError) Platform.runLater(this::cancel);

                if (isCancelled()) break;
                updateProgress(i, iterationLimit);
            }

            return null;
        }

        @Override
        protected void scheduled() {
            trainingStarted();
        }

        @Override
        protected void done() {
            Platform.runLater(TrainSettings.this::trainingStopped);
        }
    }
}
