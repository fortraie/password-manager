package com.fortraie.data;

import com.fortraie.controller.MainController;
import com.fortraie.main.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SourceFile {
    private File sourceFile;
    private ObservableList<Entry> entries = FXCollections.observableArrayList();


    public SourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
        Connection.setSourceFile(this);
    }

    public void archive() throws IOException {
        FileWriter writer = new FileWriter(sourceFile);
        for (Entry entry : entries) {
            writer.write(entry.getId() + "," + entry.getName() + "," + entry.encrypt(entry.getPassword()) + "," + entry.getCategory() + "\n");
        }
        writer.close();
    }
    public void populate() throws IOException {
        String line;
        Scanner scanner = new Scanner(sourceFile);
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] parts = line.split(",");
            entries.add(new Entry(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]));
        }
        scanner.close();
        // Decrypt passwords.
        for (Entry entry : entries) {
            entry.setPassword(entry.decrypt(entry.getPassword()));
        }
    }


    public boolean validatePassword(String password) throws IOException {
        populate();
        return entries.get(0).getPassword().equals(password);
    }

    public void add(Entry entry) {
        entries.add(entry);
    }
    public void remove(Entry entry) {
        if (entry != null) entries.remove(entry);
    }


    public String getName() {
        return sourceFile.getName();
    }
    public ObservableList<Entry> getEntries() {
        return entries;
    }
    public boolean isEmpty() {
        return sourceFile == null;
    }
}
