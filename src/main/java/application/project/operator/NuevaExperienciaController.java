package application.project.operator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.controlsfx.control.CheckComboBox;
import org.springframework.stereotype.Controller;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.Collection;

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
    private CheckComboBox<?> ccbIntereses;

    @FXML
    private Button btnCargarExp;

    private Collection<byte[]> imagenes = new ArrayList<>();

    private final FileChooser fc = new FileChooser();

    @FXML
    private void initialize(){
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Imagen", "*.jpg", "*.png");
        fc.getExtensionFilters().add(imageFilter);
    }

    @FXML
    void AgregarImagenes(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnImagenes.getScene().getWindow();
        File file = fc.showOpenDialog(stage);
        if (file.exists()){
            byte[] imagen = Files.readAllBytes(file.toPath());
            imagenes.add(imagen);
        };
    }

    @FXML
    void CrearExperiencia(ActionEvent event) {


    }

}


