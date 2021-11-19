package application.project.main;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.OperadorEntity;
import application.entities.ent.ReservaEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Controller;

import java.sql.Date;

@Controller
public class ReservasController{

    @FXML
    private TableView<ReservaEntity> TablaReservas;

    @FXML
    private TableColumn<ReservaEntity,String > CExperiencia;

    @FXML
    private TableColumn<ReservaEntity,String> COperador;

    @FXML
    private TableColumn<ReservaEntity, Date> CFechaInicio;

    @FXML
    private TableColumn<ReservaEntity, Date> CFechaF;

    @FXML
    private TableColumn<ReservaEntity, Integer> CPersonas;

    @FXML
    private TableColumn<ReservaEntity, Button> CVerExp;

    @FXML
    private Button btnExperiencia;

    @FXML
    void gotoExperiencia(ActionEvent event) {


    }

}
