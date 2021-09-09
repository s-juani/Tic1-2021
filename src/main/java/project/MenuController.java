package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import entities.ClienteManager;

import java.time.LocalDate;

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
        if (txtLoginUser.getText() == null || txtLoginPassword.getText() == null){
            showAlert(
                    "Datos faltantes.",
                    "Se deben completar todos los campos.");
        } else {
            String user = txtLoginUser.getText();
            String password = txtLoginPassword.getText();

            //FIXME añadir query si existe user al que corresponda la password

        }


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
        if (txtNombreCompleto.getText() == null || dateFechaNacimieto.getValue() == null || txtCorreoElectronico.getText() == null
                || txtUser.getText() == null || txtPassword.getText() == null){

            showAlert(
                    "Datos faltantes.",
                    "Se deben completar todos los campos.");

        } else{
            try{

                String name = txtNombreCompleto.getText();
                LocalDate birth = dateFechaNacimieto.getValue();
                String email = txtCorreoElectronico.getText();
                String user = txtUser.getText();
                String password = txtPassword.getText();

                try{
                    ClienteManager.addClient(email,user,name,birth,password);               //FIXME error al llamar la funcion addClient()
                    showAlert("Usuario registrado.","Se registro el usuario con exito.");
                }
                catch (Exception e){ //Informacion invalida                                 //FIXME añadir exceptions correspondientes
                    showAlert(
                            "Informacion invalida.",
                            "Se encontro un error al ingresar los datos."
                    );
                }
                catch (Exception e){ //Usuario ya existente
                    showAlert(
                            "Usuario ya existente",
                            "El usuario que desea registrar ya existe en el sistema."
                    );
                }
            }
            catch (Exception e){
                showAlert(
                        "Error al ingresar los datos.",
                        "Checkee que los datos ingresados sean correctos e intentelo nuevamente");
            }
        }
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