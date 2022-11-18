package com.fortraie.data;

import java.io.FileWriter;
import java.util.ArrayList;

public class Category extends ArrayList<String> {
    public Category() {
        populate();
    }


    public void archive() {
        // Saves categories as csv file to /data/categories.csv.
        try {
            FileWriter writer = new FileWriter("src/main/resources/data/categories.csv");
            for (String category : this) {
                writer.write(category + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populate() {
        // Read the categories.csv file and populate the list
        try {
            String line;
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File("src/main/resources/data/categories.csv"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] parts = line.split(",");
                add(parts[0]);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
