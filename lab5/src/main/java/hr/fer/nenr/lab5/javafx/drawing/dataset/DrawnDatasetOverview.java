package hr.fer.nenr.lab5.javafx.drawing.dataset;

import hr.fer.nenr.lab5.drawing.DatasetModel;
import hr.fer.nenr.lab5.drawing.DatasetModelListener;
import hr.fer.nenr.lab5.javafx.settings.TrainSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DrawnDatasetOverview extends VBox implements DatasetModelListener {

    private DatasetModel model;
    private TableView<ClassSampleCounter> tableView = new TableView<>();
    private ObservableList<ClassSampleCounter> tableList = FXCollections.observableArrayList();
    private Button trainButton;

    public DrawnDatasetOverview(Stage stage, Scene scene, DatasetModel model) {
        this.model = model;
        model.addListener(this);

        setSpacing(20.0);
        initTable();
        initButtons(stage, scene);
    }

    @Override
    public void classAdded() {
        tableList.add(new ClassSampleCounter());
        requestLayout();
    }

    @Override
    public void inputAdded(int classIndex) {
        tableList.get(classIndex).sampleCount++;
        tableView.refresh();
        requestLayout();
    }

    @Override
    public void datasetReset() {
        updateTableList();
        requestLayout();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        int selectedIndex = model.getSelectedIndex();
        tableView.getSelectionModel().clearAndSelect(selectedIndex);
        tableView.getFocusModel().focus(selectedIndex);

        trainButton.setDisable(model.isEmpty());
    }

    private void initTable() {
        updateTableList();

        TableColumn<ClassSampleCounter, Integer> idColumn = new TableColumn<>("Class ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setSortable(false);

        TableColumn<ClassSampleCounter, Integer> sampleCountColumn = new TableColumn<>("Samples");
        sampleCountColumn.setCellValueFactory(new PropertyValueFactory<>("sampleCount"));
        sampleCountColumn.setSortable(false);

        tableView.setItems(tableList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(sampleCountColumn);

        tableView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            model.setSelectedIndex(newValue.intValue());
        });

        getChildren().add(tableView);
    }

    private void initButtons(Stage stage, Scene scene) {
        Button newClassButton = new Button("New class");
        newClassButton.setOnAction(e -> model.addClass());

        trainButton = new Button("Train...");
        trainButton.setOnAction(e -> {
            stage.close();
            TrainSettings trainSettings = new TrainSettings(stage, scene, model.toDataset());
            stage.setScene(new Scene(new BorderPane(trainSettings), 800, 600));
            stage.show();
        });

        HBox buttonBox = new HBox(10.0, newClassButton, trainButton);
        buttonBox.setAlignment(Pos.CENTER);
        getChildren().add(buttonBox);
    }

    private void updateTableList() {
        tableList.clear();
        ClassSampleCounter.reset();
        for (List<double[]> inputs : model) {
            tableList.add(new ClassSampleCounter(inputs.size()));
        }
        model.setSelectedIndex(tableList.size() - 1);
    }
}
