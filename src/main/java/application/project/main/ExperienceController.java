package application.project.main;

import application.entities.ent.ExperienciaEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class ExperienceController {

    ExperienciaEntity experiencia;

    @FXML
    private Text txtExperienceName;

    @FXML
    private Text txtExperienceDescription;

    @FXML
    private Button btnImgSiguiente;

    public void initialize(){
        experiencia = getCurrentExperience();
        txtExperienceName.setText(experiencia.getNombre());
        txtExperienceDescription.setText(experiencia.getDescripcion());
    }

    private ExperienciaEntity getCurrentExperience(){
        Stage stage = (Stage) this.btnImgSiguiente.getScene().getWindow();
        return (ExperienciaEntity) stage.getUserData();
    }


}
