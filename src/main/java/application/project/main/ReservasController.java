package application.project.main;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.OperadorEntity;
import application.entities.ent.ReservaEntity;
import application.entities.ent.ReservaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReservasController{
/*
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

    @Autowired
    private ReservaRepository reservaRepository;

    @FXML
    void initialize(){
        CFechaInicio.setCellValueFactory(new MapValueFactory<>("fechaini"));
        tcExperiencia.setCellValueFactory(new MapValueFactory<>("nombre"));
        tcCantPersonas.setCellValueFactory(new MapValueFactory<>("cantidad"));

        reservas = reservaRepository.findByOperadorExperiencia(operador.getMail());

        ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

        for (ReservaEntity res : reservas){
            Map<String, Object> item = new HashMap<>();
            item.put("fechaini", res.getFechaInicio());
            item.put("nombre", res.getNombreExperiencia());
            item.put("cantidad", res.getCantidad());
            items.add(item);
        }
        TablaReservas.getItems().addAll(items);

        Label placeholder = new Label();
        placeholder.setText("No hay reservas proximas");
        tvReservas.setPlaceholder(placeholder);
    }

    @FXML
    void gotoExperiencia(ActionEvent event) {


    }
*/
}
