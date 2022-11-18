package com.fortraie.main;

import com.fortraie.data.Entry;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.fortraie.controller.CategoriesController;
import com.fortraie.controller.MainController;
import com.fortraie.controller.ManageController;
import com.fortraie.controller.SearchController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Connection.startLoginPane(stage);
    }
}
