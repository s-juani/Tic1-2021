package application.project.ingresar;

import application.Main;
import application.entities.ent.*;
import application.entities.session.currentSession;
import application.project.main.MainController;
import application.project.operator.NuevaExperienciaController;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;


import java.io.IOException;

@Controller
public class LoginUserController {

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

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
                OperadorEntity operador = operadorRepository.findOneByUsuario(user);
                if (operador == null){
                    AdministradorEntity admin = administradorRepository.findOneByUsuario(user);
                    if (admin == null){
                        showAlert(
                                "Usuario no existe",
                                "El usuario ingresado no existe."
                        );
                    } else {
                        String hashedPw = admin.getPw();
                        if (!hashedPw.matches("^\\$2[ayb]\\$.{56}$")){
                            showAlert(
                                    "Contraseña corrompida!",
                                    "La contraseña no está en el formato correcto en la base de datos."
                            );
                        } else if (!BCrypt.checkpw(pw, admin.getPw())){
                            showAlert(
                                    "Contraseña incorrecta!",
                                    "La contraseña ingresada es incorrecta, intentelo de nuevo."
                            );
                        } else {
                            Main.setCurrentSession(new currentSession(admin));
                            gotoMainAdmin();
                        }
                    }
                } else {
                    String hashedPw = operador.getPw();
                    if (!hashedPw.matches("^\\$2[ayb]\\$.{56}$")) {
                        showAlert(
                                "Contraseña corrompida!",
                                "La contraseña no está en el formato correcto en la base de datos."
                        );
                    } else if(!BCrypt.checkpw(pw,operador.getPw())){
                        showAlert(
                                "Contraseña incorrecta!",
                                "La contraseña ingresada es incorrecta, intentelo de nuevo."
                        );
                    } else {
                        Main.setCurrentSession(new currentSession(operador));
                        gotoMainOperador();
                    }
                }
            } else {
                String hashedPw = turista.getPw();
                if (!hashedPw.matches("^\\$2[ayb]\\$.{56}$")) {
                    showAlert(
                            "Contraseña corrompida!",
                            "La contraseña no está en el formato correcto en la base de datos."
                    );
                } else if(!BCrypt.checkpw(pw,turista.getPw())){
                    showAlert(
                            "Contraseña incorrecta!",
                            "La contraseña ingresada es incorrecta, intentelo de nuevo."
                    );
                } else {
                    Main.setCurrentSession(new currentSession(turista));
                    gotoMain();
                }
            }
        }
    }

    private void gotoMainAdmin() {
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

    private void gotoMainOperador () throws IOException {
        // TODO agregar ir al main operador cuando este pronto
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(NuevaExperienciaController.class.getResourceAsStream("NuevaExperiencia.fxml"));
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