package com.fortraie.main;

import com.fortraie.controller.CategoriesController;
import com.fortraie.controller.MainController;
import com.fortraie.controller.ManageController;
import com.fortraie.controller.SearchController;
import com.fortraie.data.Entry;
import com.fortraie.data.SourceFile;
import com.fortraie.data.TimeStamp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Connection {
    private static SourceFile sourceFile;
    private static TableView<Entry> tableView;

    public static void startLoginPane(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/loginPane.fxml"));

        stage.setTitle("NotSoSecure™");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
    public static void startMainPane(Stage stage, String password) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/mainPane.fxml"));

        if (sourceFile.validatePassword(password)) {
            TimeStamp timeStamp = new TimeStamp();
            timeStamp.readAndInform();
            timeStamp.print();

            stage.setTitle("NotSoSecure™");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect password provided.");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    public static void startManagePane(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/managePane.fxml"));

        stage.setTitle("Modify entry");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
    public static void startManagePane(Stage stage, Entry entry) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/managePane.fxml"));
        Pane managePane = fxmlLoader.load();

        // Applies necessary parameters to the MainController.
        ManageController manageController = fxmlLoader.getController();
        manageController.focusOnEntry(entry);

        stage.setTitle("Modify entry");
        stage.setScene(new Scene(managePane));
        stage.show();
    }

    public static void startCategoriesPane(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/categoriesPane.fxml"));
        Pane categoriesPane = fxmlLoader.load();

        // Applies necessary parameters to the MainController.
        CategoriesController categoriesController = fxmlLoader.getController();
        categoriesController.setSourceFile(sourceFile);

        stage.setTitle("Manage categories");
        stage.setScene(new Scene(categoriesPane));
        stage.show();
    }
    public static void startSearchPane(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/searchPane.fxml"));

        stage.setTitle("Find");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setOnCloseRequest(event -> {
            SearchController searchController = fxmlLoader.getController();
            searchController.restoreTableView();
        });
        stage.show();
    }
    public static void startAboutPane(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Connection.class.getResource("/fxml/aboutPane.fxml"));

        stage.setTitle("About");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }


    public static SourceFile getSourceFile() {
        return sourceFile;
    }
    public static void setSourceFile(SourceFile sourceFile) {
        Connection.sourceFile = sourceFile;
    }
    public static TableView<Entry> getTableView() {
        return tableView;
    }
    public static void setTableView(TableView<Entry> tableView) {
        Connection.tableView = tableView;
    }
}
