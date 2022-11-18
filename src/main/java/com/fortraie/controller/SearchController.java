package com.fortraie.controller;

import com.fortraie.data.Entry;
import com.fortraie.data.SourceFile;
import com.fortraie.main.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.fortraie.data.Category;

import java.util.stream.Collectors;

public class SearchController {
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private Button categorySearchButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchTextField;
    private SourceFile sourceFile;
    private TableView<Entry> tableView;
    private ObservableList<Entry> originalEntries;


    @FXML
    private void initialize() {
        tableView = Connection.getTableView();
        sourceFile = Connection.getSourceFile();
        originalEntries = tableView.getItems();

        initializeSearchButton();
        initializeCategoryChoiceBox();
        initializeSearchCategoryButton();
    }


    private void initializeSearchButton() {
        searchButton.setOnAction(event -> {
            String searchText = searchTextField.getText();
            ObservableList<Entry> entries = FXCollections.observableArrayList(
                    sourceFile.getEntries().stream()
                            .filter(entry -> entry.getName().contains(searchText))
                            .collect(Collectors.toList())
            );
            tableView.setItems(entries);
        });
    }
    private void initializeCategoryChoiceBox() {
        Category categories = new Category();
        for (String category : categories) {
            categoryChoiceBox.getItems().add(category);
        }
    }
    private void initializeSearchCategoryButton() {
        categorySearchButton.setOnAction(event -> {
            ObservableList<Entry> entries = sourceFile.getEntries();
            String category = categoryChoiceBox.getValue();
            ObservableList<Entry> selectedEntries = entries.stream()
                    .filter(e -> e.getCategory().equals(category))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            tableView.setItems(selectedEntries);
        });
    }

    public void restoreTableView() {
        tableView.setItems(originalEntries);
    }
}
