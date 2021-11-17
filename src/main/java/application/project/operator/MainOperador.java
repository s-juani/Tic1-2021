package application.project.operator;

import application.Main;
import application.entities.ent.OperadorEntity;
import application.entities.ent.ReservaEntity;
import application.entities.ent.ReservaRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainOperador {

    @FXML
    private TableView tvReservas;

    @FXML
    private TableColumn<Map, String> tcFecha;

    @FXML
    private TableColumn<Map, String> tcExperiencia;

    @FXML
    private TableColumn<Map, String> tcCantPersonas;

    @FXML
    private Button btnCrearExperiencia;

    @FXML
    private Button btnVerExperiencias;

    @FXML
    private Button btnReservas;

    @FXML
    private Text nombreExperiencia0;

    @FXML
    private ImageView imagenExperiencia0;

    @FXML
    private Text nombreExperiencia2;

    @FXML
    private ImageView imagenExperiencia2;

    @FXML
    private Text nombreExperiencia1;

    @FXML
    private ImageView imagenExperiencia1;

    private OperadorEntity operador;

    private List<ReservaEntity> reservas;

    @Autowired
    private ReservaRepository reservaRepository;

    @FXML
    void initialize(){

        operador = Main.getCurrentSession().getActiveUser();

        tcFecha.setCellValueFactory(new MapValueFactory<>("fecha"));
        tcExperiencia.setCellValueFactory(new MapValueFactory<>("nombre"));
        tcCantPersonas.setCellValueFactory(new MapValueFactory<>("cantidad"));

        reservas = reservaRepository.findByOperadorExperiencia(operador.getMail());

        ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

        for (ReservaEntity res : reservas){
            Map<String, Object> item = new HashMap<>();
            item.put("fecha", res.getFechaInicio());
            item.put("nombre", res.getNombreExperiencia());
            item.put("cantidad", res.getCantidad());
            items.add(item);
        }
        tvReservas.getItems().addAll(items);

        Label placeholder = new Label();
        placeholder.setText("No hay reservas proximas");
        tvReservas.setPlaceholder(placeholder);
    }

    @FXML
    void goToCrearExperencia(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnReservas.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(NuevaExperienciaController.class.getResourceAsStream("Nuevaexperiencia.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void goToExperiencias(ActionEvent event) {

    }

    @FXML
    void goToReservas(ActionEvent event) {

    }

    @FXML
    void irAExperiencia0(MouseEvent event) {

    }

    @FXML
    void irAExperiencia1(MouseEvent event) {

    }

    @FXML
    void irAExperiencia2(MouseEvent event) {

    }

}