package application.project.admin;

import application.Main;
import application.entities.InteresManager;
import application.entities.UsuarioManager;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ExperienciaRepository;
import application.entities.ent.InteresEntity;
import application.entities.ent.InteresRepository;
import application.entities.exceptions.InteresAlreadyExists;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import application.project.ingresar.InitialController;
import application.project.main.ExperienceController;
import application.project.operator.NuevaExperienciaController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Collection;

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
    private ChoiceBox<InteresEntity> cbSubinteresDe;

    @FXML
    private Button btnCrearInteres;

    @FXML
    private TableView<ExperienciaEntity> tvSolicitudes;

    @FXML
    private TableColumn<ExperienciaEntity, String> tcNombre;

    @FXML
    private TableColumn<ExperienciaEntity, String> tcOperador;

    @FXML
    private TableColumn<ExperienciaEntity, String> tcDetalles;

    @FXML
    private TableColumn<ExperienciaEntity, TableCell<ExperienciaEntity, Void>> tcVerExperiencia;

    @Autowired
    private InteresRepository interesRep;

    @Autowired
    private ExperienciaRepository experienciaRep;

    private Collection<ExperienciaEntity> experienciaEntities;

    @FXML
    void initialize(){
        Collection<InteresEntity> interesEntities = interesRep.findAll();
        for (InteresEntity interes: interesEntities){
            cbSubinteresDe.getItems().add(interes);
        }

        // TODO pasar datos a tv
        tcNombre.setCellValueFactory(
                new PropertyValueFactory<ExperienciaEntity, String>("nombre")
        );
        tcOperador.setCellValueFactory(
                new PropertyValueFactory<ExperienciaEntity, String>("operador")
        );
        tcDetalles.setCellValueFactory(
                new PropertyValueFactory<ExperienciaEntity, String>("descripcion")
        );

        experienciaEntities = experienciaRep.findByAprovada(false);
        tvSolicitudes.getItems().addAll(experienciaEntities);

        Label placeholder = new Label();
        placeholder.setText("No hay solicitudes de aprovacion.");
        tvSolicitudes.setPlaceholder(placeholder);
    }

    private void irAExperiencia(ExperienciaEntity experiencia) throws IOException{
        Stage stage = (Stage) this.btnCrearInteres.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("Experiencia.fxml"));
        stage.setUserData(experiencia);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Autowired
    private InteresManager interesMgr;

    @FXML
    void crearInteres(ActionEvent event) {

        if (txtNombreInteres.getText() == null){
            showAlert(
                    "Datos faltantes.",
                    "Se deben completar todos los campos."
            );
        } else {
            try {
                String nombre = txtNombreInteres.getText();
                InteresEntity subInteres = cbSubinteresDe.getValue();

                try {
                    interesMgr.addInteres(nombre, subInteres);
                    showAlert(
                            "Interes registrado con exito!",
                            "Se actualizara la pagina.");
                    refreshPage(new ActionEvent());
                } catch (InteresAlreadyExists e){
                    showAlert(
                            "Error!",
                            "El interes que intenta registrar ya existe."
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidInformation invalidInformation) {
                    showAlert(
                            "Error!",
                            "Hay un error con los datos ingresados."
                    );
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
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

    @FXML
    public void verExp(ActionEvent actionEvent) throws IOException {
        ExperienciaEntity expSeleccionada = tvSolicitudes.getSelectionModel().getSelectedItem();
        irAExperiencia(expSeleccionada);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnCrearOperador.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}
