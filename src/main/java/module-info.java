module com.example.sprint0_ {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.sprint0 to javafx.fxml;
    exports com.example.sprint0;
}