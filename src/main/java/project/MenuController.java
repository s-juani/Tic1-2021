package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;


@Component
public class MenuController{

    // ---- SUB-MENU LOGIN (variables) ---- //

    @FXML
    private Button btnIngresar;

    @FXML
    private TextField txtLoginUser;

    @FXML
    private PasswordField txtLoginPassword;

    @FXML
    void loginUser(ActionEvent event){
        /***
         * La funcion loginUser() debe checkear si los datos ingresados son validos y si existe un usuario al que
         * correspondan los mismos. De ser asi en un principio enviara una alerta diciendo que se logueo con exito,
         * en un futuro dara ingreso al sistema.
         */
    }


    // ---- SUB-MENU REGISTER ---- //

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private DatePicker dateFechaNacimieto;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void registerUser(ActionEvent event){
        /***
         * La funcion registerUser() debe checkear si los datos ingresados son validos y si ya no existe un usuario
         * con los mismos datos. De cumplirse ambas condiciones debe llamar a la entidad User para crear un usuario
         * nuevo y persistirlo en el sistema.
         *
         * Para cada posible resultado debera enviar una alerta, en caso de registrar el usuario con exito debe
         * cambiar de escena.
         */
    }

    // ---- UTILITIES ---- //

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}