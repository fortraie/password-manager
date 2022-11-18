package com.fortraie.controller;

import com.fortraie.data.Category;
import com.fortraie.data.Entry;
import com.fortraie.data.SourceFile;
import com.fortraie.main.Connection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ManageController {
    @FXML
    private ListView<String> categoryListView;
    @FXML
    private Button doneButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    private SourceFile sourceFile;


    @FXML
    private void initialize() {
        sourceFile = Connection.getSourceFile();

        initializeAddEntryAction();
        initializeCategoryListView();
    }


    private void initializeAddEntryAction() {
        doneButton.setOnAction(event -> {
            sourceFile.getEntries().add(new Entry(sourceFile.getEntries().get(sourceFile.getEntries().size() - 1).getId() + 1, nameTextField.getText(), passwordPasswordField.getText(), categoryListView.getSelectionModel().getSelectedItem()));
            try {
                sourceFile.archive();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            doneButton.getScene().getWindow().hide();
        });
    }
    private void initializeCategoryListView() {
        // Populate the categoryListView with the categories from the Category class.
        categoryListView.getItems().addAll(new Category().stream().toList());
    }


    public void focusOnEntry(Entry entry) {
        nameTextField.setText(entry.getName());

        // Focus element on the list view that's equal to the category of the entry.
        ObservableList<String> categories = categoryListView.getItems();
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals(entry.getCategory())) {
                categoryListView.getSelectionModel().select(i);
                break;
            }
        }
    }
}
