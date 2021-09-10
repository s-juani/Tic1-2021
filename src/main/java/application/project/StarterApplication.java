package application.project;


import application.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StarterApplication extends Application {

    private Parent root;

    @Override
    public void init() throws Exception{}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        root = fxmlLoader.load(application.project.StarterApplication.class.getResource("menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}