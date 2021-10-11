package application.project.ingresar;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class InitialController {

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    private Stage stage;
    private Scene scene;

    @FXML
    void close(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void gotoLogin(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.btnIngresar.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(LoginUserController.class.getResourceAsStream("login.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

    }

    @FXML
    void gotoRegister(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.btnIngresar.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(LoginUserController.class.getResourceAsStream("register.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
