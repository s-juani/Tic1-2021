package application.project.ingresar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterUserController {

    @FXML
    private Button btnRegistrar;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private TextField txtUser;

    @FXML
    private ComboBox<?> cbNacionalidad;

    @FXML
    private Button btnGoBack;

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void registerUser(ActionEvent event) {

    }

    public void volver(ActionEvent actionEvent) {
    }
}

