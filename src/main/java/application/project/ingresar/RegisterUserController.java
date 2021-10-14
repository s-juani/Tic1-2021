package application.project.ingresar;

import application.Main;
import application.entities.UsuarioManager;
import application.entities.ent.InteresEntity;
import application.entities.ent.InteresRepository;
import application.entities.ent.PaisEntity;
import application.entities.ent.PaisRepository;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

@Controller
public class RegisterUserController {

    @Autowired
    private UsuarioManager userMgr;

    @Autowired
    private PaisRepository paisRep;

    @Autowired
    private InteresRepository interesRep;

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
    private ComboBox<PaisEntity> cbNacionalidad;

    @FXML
    private Button btnVolver;

    @FXML
    private CheckComboBox<InteresEntity> ccbIntereses;


    public void initialize(){
        addCountries();
        addInterest();
    }

    public void addCountries(){
        Collection<PaisEntity> paisEntities = paisRep.findAll();
        for (PaisEntity pais: paisEntities){
            cbNacionalidad.getItems().add(pais);
        }
    }

    public void addInterest(){
        Collection<InteresEntity> interesEntities = interesRep.findAll();
        for (InteresEntity interes: interesEntities){
            ccbIntereses.getItems().add(interes);
        }
    }

    @FXML
    void registerUser(ActionEvent event) {
        if (txtPw.getText() == null || dateNacimiento.getValue() == null || txtMail.getText() == null
                || txtUser.getText() == null || txtNombreCompleto.getText() == null || cbNacionalidad.getValue() == null){
            showAlert(
                    "Datos faltantes.",
                    "Se deben completar todos los campos."
            );
        } else {
            try{
                String name = txtNombreCompleto.getText();
                String user = txtUser.getText();
                String pw = txtPw.getText();
                String mail = txtMail.getText();
                LocalDate birth = dateNacimiento.getValue();
                PaisEntity country = cbNacionalidad.getValue();
                ObservableList<InteresEntity> intereses = ccbIntereses.getCheckModel().getCheckedItems();

                try{
                    userMgr.addTurist(name,user,pw,mail,birth,country,intereses);
                    showAlert("Usuario registrado.","Inicie sesion para continuar.");
                    gotoLogin();

                }
                catch (UserAlreadyInUse userAlreadyInUse) {
                    showAlert(
                            "Usuario no disponible.",
                            "El nombre de usuario ingresado ya esta en uso."
                    );
                }
                catch (InvalidInformation invalidInformation) {
                    showAlert(
                            "Informacion invalida.",
                            "Se encontro un error al ingresar los datos."
                    );
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void volver(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
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

    private void gotoLogin() throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(LoginUserController.class.getResourceAsStream("login.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

}

