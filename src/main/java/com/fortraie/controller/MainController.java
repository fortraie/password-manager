package com.fortraie.controller;

import com.fortraie.data.Entry;
import com.fortraie.data.SourceFile;
import com.fortraie.main.Connection;
import com.fortraie.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainController {
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private TableColumn<Entry, String> categoryTableColumn;
    @FXML
    private MenuItem addMenuItem;
    @FXML
    private MenuItem changeMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem searchMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private TableColumn<Entry, Integer> idTableColumn;
    @FXML
    private TableColumn<Entry, String> nameTableColumn;
    @FXML
    private TableColumn<Entry, String> passwordTableColumn;
    @FXML
    private TableView<Entry> tableView;
    @FXML
    private MenuItem categoriesMenuItem;
    private SourceFile sourceFile;


    @FXML
    private void initialize() {
        sourceFile = Connection.getSourceFile();

        initializeTableView();
        initializeManagePane();
        initializeDeleteMenuItem();
        initializeCategoriesMenuItem();
        initializeManagePaneWithData();
        initializeSearchMenuItem();
        initializeAboutMenuItem();
    }


    private void initializeTableView() {
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        categoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        tableView.setItems(sourceFile.getEntries());
    }
    private void initializeManagePane() {
        // Once addMenuItem is clicked, open a new window with a managePane.fxml file
        addMenuItem.setOnAction(event -> {
            try {
                Connection.startManagePane(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void initializeManagePaneWithData() {
        changeMenuItem.setOnAction(event -> {
            Entry entry = tableView.getSelectionModel().getSelectedItem();

            if (entry.getId() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Main Key cannot be modified.");
                alert.setContentText("Please select a valid entry to modify.");
                alert.showAndWait();
                return;
            }

            try {
                Connection.startManagePane(new Stage(), entry);

                sourceFile.remove(entry);
                sourceFile.archive();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Entry entry = tableView.getSelectionModel().getSelectedItem();

                if (entry.getId() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Main Key cannot be modified.");
                    alert.setContentText("Please select a valid entry to modify.");
                    alert.showAndWait();
                    return;
                }

                try {
                    Connection.startManagePane(new Stage(), entry);

                    sourceFile.remove(entry);
                    sourceFile.archive();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void initializeDeleteMenuItem(){
        deleteMenuItem.setOnAction(event -> {
            Entry entry = tableView.getSelectionModel().getSelectedItem();
            if (entry != null && entry.getId() != 0) {
                sourceFile.remove(entry);
            } else if (entry.getId() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Main Key cannot be modified.");
                alert.setContentText("Please select a valid entry to modify.");
                alert.showAndWait();
            }
            try {
                sourceFile.archive();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void initializeCategoriesMenuItem() {
        categoriesMenuItem.setOnAction(event -> {
            try {
                Connection.startCategoriesPane(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void initializeSearchMenuItem() {
            searchMenuItem.setOnAction(event -> {
            try {
                Connection.setTableView(tableView);
                Connection.startSearchPane(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void initializeAboutMenuItem() {
        aboutMenuItem.setOnAction(event -> {
            try {
                Connection.startAboutPane(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
