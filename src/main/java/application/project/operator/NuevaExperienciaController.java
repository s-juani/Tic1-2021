package application.project.operator;
import application.Main;
import application.entities.ExperienciaManager;
import application.entities.ent.*;
import application.entities.exceptions.ExperienceAlreadyInUse;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import application.project.main.MainController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
public class NuevaExperienciaController {

    @FXML
    public TextField txtAforo;

    @FXML
    public CheckBox cbAdmiteReserva;

    @FXML
    public TextArea txtDireccion;

    @FXML
    public CheckBox cbRequiereVacunacion;

    @FXML
    private TextField txtNombreExp;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Button btnImagenes;

    @FXML
    private CheckComboBox<InteresEntity> ccbIntereses;

    @FXML
    private Button btnCargarExp;

    private Set<ImagenEntity> imagenes = new HashSet<>();

    private final FileChooser fc = new FileChooser();

    @Autowired
    private ExperienciaManager experienciaMgr;

    @Autowired
    private InteresRepository interesRep;

    private ExperienciaEntity nuevaExperiencia=new ExperienciaEntity();

    @FXML
    private void initialize(){
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Imagen", "*.jpg", "*.png");
        fc.getExtensionFilters().add(imageFilter);
        addInterest();
        nuevaExperiencia.setOperador(((OperadorEntity) Main.getCurrentSession().getActiveUser()).getMail());
    }

    public void addInterest(){
        Collection<InteresEntity> interesEntities = interesRep.findAll();
        for (InteresEntity interes: interesEntities){
            ccbIntereses.getItems().add(interes);
        }
    }

    @FXML
    void AgregarImagenes(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnImagenes.getScene().getWindow();
        File file = fc.showOpenDialog(stage);
        if (file.exists()){
            byte[] imagen = Files.readAllBytes(file.toPath());
            if (imagen.length <= 16777215) {
                if (imagenes.add(new ImagenEntity(imagen, nuevaExperiencia))){
                showAlert(
                        "Imagen subida.",
                        "La imagen fue cargada exitosamente."
                );
                }else{
                    showAlert(
                            "Imagen repetida.",
                            "La imagen ya está en el conjunto de imagenes de esta experiencia."
                    );
                }
            }else{
                showAlert(
                        "Imagen demasiado pesada.",
                        "Solo se soporta agregar imágenes de hasta 14MB."
                );
            }
        }
    }

    @FXML
    void CrearExperiencia(ActionEvent event) {
        if (txtDescripcion.getText() == null || txtDireccion.getText() == null || txtNombreExp.getText() == null
                || ccbIntereses.getCheckModel().getCheckedItems().size() == 0 || imagenes.size()==0){
            showAlert(
                    "Datos faltantes.",
                    "Se deben completar todos los campos obligatorios."
            );
        } else {
            try{
                String nombre = txtNombreExp.getText();
                String direccion = txtDireccion.getText();
                String descripcion = txtDescripcion.getText();
                Integer aforo = (txtAforo.getText().equals("")) ? null : Integer.parseInt(txtAforo.getText());
                boolean conReserva = cbAdmiteReserva.isSelected();
                boolean vacunacion = cbRequiereVacunacion.isSelected();
                Set<InteresEntity> intereses = new HashSet<>(ccbIntereses.getCheckModel().getCheckedItems());

                try{
                    experienciaMgr.addExperiencia(nuevaExperiencia,nombre,direccion,descripcion,aforo,conReserva,vacunacion,intereses,imagenes);
                    showAlert("Experiencia registrada.","Experiencia registrada exitosamente.");

                    Stage stage = (Stage) this.btnImagenes.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
                    stage.setScene(new Scene(root));
                    stage.show();

                }
                catch (ExperienceAlreadyInUse experienceAlreadyInUse) {
                    showAlert(
                            "Experiencia no disponible.",
                            "El nombre de experiencia ingresado ya esta en uso."
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

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public void volver(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnImagenes.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}


