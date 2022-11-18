module password.manager {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    exports com.fortraie.main to javafx.graphics;

    opens com.fortraie.controller to javafx.fxml;
    opens com.fortraie.data to javafx.base;
}