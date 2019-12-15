package hr.fer.nenr.lab5.javafx.menu;

import hr.fer.nenr.lab5.drawing.DatasetModel;
import hr.fer.nenr.lab5.javafx.menu.items.*;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DatasetMenuBar extends MenuBar {

    private static final String DATASETS = "./datasets";

    private FileChooser chooser = new FileChooser();

    public DatasetMenuBar(Stage stage, DatasetModel datasetModel) {
        initFileChooser();

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(
                new NewMenuItem(datasetModel),
                new SaveAsMenuItem(stage, chooser, datasetModel),
                new LoadMenuItem(stage, chooser, datasetModel),
                new SeparatorMenuItem(),
                new QuitMenuItem()
        );

        Menu settingsMenu = new Menu("Settings");
        settingsMenu.getItems().addAll(
                new SampleCountMenuItem(datasetModel)
        );

        getMenus().addAll(fileMenu, settingsMenu);
    }

    private void initFileChooser() {
        File datasetFolder = new File(DATASETS);
        if (!datasetFolder.isDirectory()) {
            try {
                Files.createDirectory(datasetFolder.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                Platform.exit();
            }
        }

        chooser.setInitialDirectory(datasetFolder);
    }
}
