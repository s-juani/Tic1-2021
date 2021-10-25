package application.project.main;

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
        Stage stage = (Stage) this.btnImgSiguiente.getScene().getWindow();
        experiencia = (ExperienciaEntity) stage.getUserData();
        txtExperienceName.setText(experiencia.getNombre());
        txtExperienceDescription.setText(experiencia.getDescripcion());
    }


}
