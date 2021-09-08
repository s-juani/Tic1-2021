module com.example.sprint0_ {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;

    opens com.example.sprint0 to javafx.fxml;
    exports com.example.sprint0;
    exports project;
    opens project to javafx.fxml;
}