package com.fortraie.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Entry{
    private final int KEY = 3;

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty password;
    private SimpleStringProperty category;


    public Entry(int id, String name, String password, String category) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.category = new SimpleStringProperty(category);
    }


    public String encrypt(String password) {
        // Encrypts password using Caesar Cipher
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = (char) (password.charAt(i) + KEY);
            sb.append(c);
        }
        return sb.toString();
    }
    public String decrypt(String password) {
        // Decrypts password using Caesar Cipher
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = (char) (password.charAt(i) - KEY);
            sb.append(c);
        }
        return sb.toString();
    }


    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public String getPassword() {
        return password.get();
    }
    public String getCategory() {
        return category.get();
    }
    public void setPassword(String password) {
        this.password.set(password);
    }
}
