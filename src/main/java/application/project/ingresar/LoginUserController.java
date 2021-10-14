package application.project.ingresar;

import application.Main;
import application.entities.ent.TuristaEntity;
import application.entities.ent.TuristaRepository;
import application.project.main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class LoginUserController {

    @Autowired
    private TuristaRepository turistaRepository;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Button btnIngresar;

    @FXML
    private PasswordField txtConstrasena;

    @FXML
    private Button btnVolver;

    @FXML
    void ingresar(ActionEvent event) throws IOException {
        if (txtUsuario.getText() == null || txtConstrasena.getText() == null){
            showAlert(
                    "Datos faltantes!",
                    "Se deben completar todos los campos."
            );
        } else {
            String user = txtUsuario.getText();
            String pw = txtConstrasena.getText();
            TuristaEntity turista = turistaRepository.findOneByUsuario(user);
            if (turista == null){
                showAlert(
                        "Usuario no existe",
                        "El usuario ingresado no existe."
                );
            } else {
                if (!turista.getPw().equals(pw)){
                    showAlert(
                            "Contraseña incorrecta!",
                            "La contraseña ingresada es incorrecta, intentelo de nuevo."
                    );
                } else {
                    gotoMain();
                }
            }


        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    private void gotoMain() throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainController.class.getResourceAsStream("main.fxml"));
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