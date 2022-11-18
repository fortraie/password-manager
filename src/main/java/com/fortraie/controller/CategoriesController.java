package com.fortraie.controller;

import com.fortraie.data.SourceFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.fortraie.data.Category;

import java.io.IOException;

public class CategoriesController {
    @FXML
    private Button addButton;
    @FXML
    private TextField addTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private ListView<String> listView;
    private Category categories;
    private SourceFile sourceFile;


    @FXML
    public void initialize() {
        categories = new Category();

        initializeListView();
        initializeAddButton();
        initializeDeleteButton();
    }


    private void initializeListView() {
        // Populate the categoryListView with the categories from the Category class.
        listView.getItems().clear();
        listView.getItems().addAll(categories.stream().toList());
    }
    private void initializeAddButton() {
        addButton.setOnAction(event -> {
            categories.add(addTextField.getText());
            categories.archive();
            initializeListView();
        });
    }
    private void initializeDeleteButton() {
        deleteButton.setOnAction(event -> {
            deleteEntries(listView.getSelectionModel().getSelectedItem());
            categories.remove(listView.getSelectionModel().getSelectedItem());
            categories.archive();
            initializeListView();
        });
    }
    private void deleteEntries(String category) {
        sourceFile.getEntries().removeIf(entry -> entry.getCategory().equals(category));
        try {
            sourceFile.archive();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setSourceFile(SourceFile sourceFile) {
        this.sourceFile = sourceFile;
    }
}
