package application.project.Menu;

import application.entities.ent.TuristaEntity;
import application.entities.ent.TuristaRepository;
//import application.entities.ent.ClientesEntity;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import application.entities.ClienteManager;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class MenuController{

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private ClienteManager clientMgr;

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
            TuristaEntity cliente = turistaRepository.findOneByUsuario(user);
            if (cliente==null){
                showAlert(
                        "Usuario no existe.",
                        "El usuario ingresado no existe."
                );
            }
            else{
                if(!cliente.getPw().equals(password)){
                    showAlert(
                            "Contraseña incorrecta",
                            "La contraseña ingresada es incorrecta, inténtelo de nuevo."
                    );
                }
                else{
                    showAlert(
                            "Login exitoso",
                            "Login exitoso."
                    );
                }
            }

            //Esto esta para probar correrlo o falta algo?

            //Caused by: java.lang.reflect.InvocationTargetException
            //Caused by: java.lang.NullPointerException: Cannot invoke "application.entities.ent.ClienteRepository.findOneByUsuario(String)" because "this.clienteRepository" is null
            //seguimos luchando con el is null
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
                    clientMgr.addClient(email,user,name,birth,password);
                    showAlert("Usuario registrado.","Se registro el usuario con exito.");
                }
                catch (InvalidInformation e0){ //Informacion invalida
                    showAlert(
                            "Informacion invalida.",
                            "Se encontro un error al ingresar los datos."
                    );
                }
                catch (UserAlreadyInUse e1){ //Nombre de usuario no disponible
                    showAlert(
                            "Usuario no disponible.",
                            "El nombre de usuario ingresado ya esta en uso."
                    );
                }

                catch (Exception e){
                    e.printStackTrace();
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