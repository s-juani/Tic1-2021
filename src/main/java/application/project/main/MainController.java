package application.project.main;

import application.Main;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.TuristaEntity;
import application.project.utils.utilities;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MainController {

    @FXML
    private Button btnPgAnterior;

    @FXML
    private Button btnPgAnterior1;

    @FXML
    private ImageView imagenExperiencia0;

    @FXML
    private Text nombreExperiencia0;

    @FXML
    private ImageView imagenExperiencia1;

    @FXML
    private Text nombreExperiencia1;

    @FXML
    private ImageView imagenExperiencia2;

    @FXML
    private Text nombreExperiencia2;

    @FXML
    private ImageView imagenExperiencia3;

    @FXML
    private Text nombreExperiencia3;

    @FXML
    private ImageView imagenExperiencia4;

    @FXML
    private Text nombreExperiencia4;

    @FXML
    private ImageView imagenExperiencia5;

    @FXML
    private Text nombreExperiencia5;

    @FXML
    private ImageView imagenExperiencia6;

    @FXML
    private Text nombreExperiencia6;

    @FXML
    private ImageView imagenExperiencia7;

    @FXML
    private Text nombreExperiencia7;

    @FXML
    private ImageView imagenExperiencia8;

    @FXML
    private Text nombreExperiencia8;

    @FXML
    private ImageView imagenExperiencia9;

    @FXML
    private Text nombreExperiencia9;

    @FXML
    private ImageView imagenExperiencia10;

    @FXML
    private Text nombreExperiencia10;

    // ----------------------//

    private TuristaEntity user;

    private ObservableList<ExperienciaEntity> sugerencias;

    public void initialize() {
        user = Main.getCurrentSession().getActiveUser();
        sugerencias = utilities.getSugerencias(user);


    }





    @FXML
    void irAExperiencia0(MouseEvent event) {

    }

    @FXML
    void irAExperiencia1(MouseEvent event) {

    }

    @FXML
    void irAExperiencia10(MouseEvent event) {

    }

    @FXML
    void irAExperiencia2(MouseEvent event) {

    }

    @FXML
    void irAExperiencia3(MouseEvent event) {

    }

    @FXML
    void irAExperiencia4(MouseEvent event) {

    }

    @FXML
    void irAExperiencia5(MouseEvent event) {

    }

    @FXML
    void irAExperiencia6(MouseEvent event) {

    }

    @FXML
    void irAExperiencia7(MouseEvent event) {

    }

    @FXML
    void irAExperiencia8(MouseEvent event) {

    }

    @FXML
    void irAExperiencia9(MouseEvent event) {

    }

    @FXML
    void irAPgAnterior(ActionEvent event) {

    }

}
