package com.fortraie.data;

import com.fortraie.main.Connection;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TimeStamp {
    private SourceFile sourceFile = Connection.getSourceFile();
    private final String FILE_PATH = "src/main/resources/timestamp/" + sourceFile.getName() + ".timestamp";

    public void print() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) Files.createFile(file.toPath());

        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = sdf.format(date);
        java.io.PrintWriter writer = new java.io.PrintWriter(file);
        writer.println(timeStamp);
        writer.close();
    }
    public void readAndInform() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) return;

        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = sdf.format(date);
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
        String line = reader.readLine();
        reader.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("You have successfully logged in.");
        alert.setContentText("Last login: " + line);
        alert.showAndWait();
    }
}
