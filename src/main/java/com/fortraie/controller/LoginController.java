package com.fortraie.controller;

import com.fortraie.data.Entry;
import com.fortraie.data.SourceFile;
import com.fortraie.main.Connection;
import com.fortraie.main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LoginController {
    @FXML
    private Button loadButton;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button sourceButton;
    @FXML
    private Label createNewLabel;
    private SourceFile sourceFile;


    @FXML
    private void initialize() {
        initializeFileChooser();
        initializeMainPanel();
        initializeCreateNewLabel();
    }


    private void initializeFileChooser() {
        sourceButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select source file");

            sourceFile = new SourceFile(fileChooser.showOpenDialog(sourceButton.getScene().getWindow()));

            if (sourceFile.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No source file selected.");
                alert.setContentText("Please select a source file.");
                alert.showAndWait();
                return;
            }

            sourceButton.setText(sourceFile.getName());
        });
    }
    private void initializeMainPanel() {
        loadButton.setOnAction(event -> {
            if (sourceFile == null || passwordPasswordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No source file selected or password provided.");
                alert.setContentText("Please select a source file and provide a password.");
                alert.showAndWait();
                return;
            }

            Stage stage = (Stage) loadButton.getScene().getWindow();
            stage.close();

            try {
                Connection.startMainPane(new Stage(), this.passwordPasswordField.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void initializeCreateNewLabel() {
        createNewLabel.setOnMouseClicked(event -> {
            File outputFile = new File("src/main/resources/data/dataset" + (int) ((Math.random() * 100) + 1) + ".csv");
            try {
                if (outputFile.exists()) {
                    outputFile = new File("src/main/resources/data/dataset" + (int) ((Math.random() * 100) + 1) + ".csv");
                }
                if (passwordPasswordField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No password provided.");
                    alert.setContentText("Please provide a password.");
                    alert.showAndWait();
                    return;
                }
                outputFile.createNewFile();
                java.io.FileWriter fw = new java.io.FileWriter(outputFile);
                Entry mainKey = new Entry(0, "Main Key", passwordPasswordField.getText(), "Not Applicable");
                fw.write(mainKey.getId() + "," + mainKey.getName() + "," + mainKey.encrypt(mainKey.getPassword()) + "," + mainKey.getCategory() + "\n");
                fw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("New dataset created.");
                alert.setContentText("The new dataset has been created and saved to " + outputFile.getAbsolutePath() + ".");
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
