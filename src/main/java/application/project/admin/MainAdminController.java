package application.project.admin;

import application.Main;
import application.entities.UsuarioManager;
import application.entities.exceptions.UserAlreadyInUse;
import application.project.operator.NuevaExperienciaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainAdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder encoder2() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UsuarioManager userMgr;

    @FXML
    private Button btnCrearOperador;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextArea txtContactos;

    @FXML
    private TextField txtNombreInteres;

    @FXML
    private ChoiceBox<?> cbSubinteresDe;

    @FXML
    private Button btnCrearInteres;

    @FXML
    private TableView<?> tvSolicitudes;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcOperador;

    @FXML
    private TableColumn<?, ?> tcDetalles;

    @FXML
    private TableColumn<?, ?> tcVerExperiencia;

    @FXML
    void crearInteres(ActionEvent event) {

    }

    @FXML
    void crearOperador(ActionEvent event) {

        if (txtUser.getText() == null || txtPw.getText() == null || txtMail.getText() == null || txtContactos.getText() == null){
            showAlert(
                    "Datos faltantes.",
                    "Se deben completar todos los campos."
            );
        } else {
            try{
                String user = txtUser.getText();
                String pw = passwordEncoder.encode(txtPw.getText());
                String mail = txtMail.getText();
                String contactos = txtContactos.getText();

                try{
                    userMgr.addOperator(mail, user, pw, contactos);
                    showAlert(
                            "Operador registrado con exito!",
                            "Se actualizara la pagina.");
                    refreshPage(new ActionEvent());

                } catch (UserAlreadyInUse userAlreadyInUse) {
                    showAlert(
                            "Usuario no disponible.",
                            "El nombre de usuario ingresado ya esta en uso."
                    );
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public void refreshPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnCrearInteres.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainAdminController.class.getResourceAsStream("MainAdmin.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
