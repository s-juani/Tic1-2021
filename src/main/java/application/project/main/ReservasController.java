package application.project.main;

import application.Main;
import application.entities.ent.*;
import application.project.admin.AproveExperienceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReservasController{

    @FXML
    public Button btnVolver;

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
    private Button btnVerExp;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ExperienciaRepository experienciaRepository;

    List<ReservaEntity> reservas;

    private TuristaEntity turista;


    @FXML
    void initialize(){

        CExperiencia.setCellValueFactory(new PropertyValueFactory<ReservaEntity,String>("nombreExperiencia"));
        COperador.setCellValueFactory(new PropertyValueFactory<ReservaEntity,String>("operadorExperiencia"));
        CFechaInicio.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Date>("fechaInicio"));
        CFechaF.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Date>("fechaFin"));
        CPersonas.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Integer>("cantidad"));

        turista = Main.getCurrentSession().getActiveUser();

        reservas = reservaRepository.findByMailTuristaAndFechaFinAfterOrderByFechaInicioAsc(turista.getMail(), new Date(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)).getTime()));

        TablaReservas.getItems().addAll(reservas);

        Label placeholder = new Label();
        placeholder.setText("No hay reservas proximas.");
        TablaReservas.setPlaceholder(placeholder);
    }

    @FXML
    public void verExp(ActionEvent actionEvent) throws IOException {
        String expSeleccionada = TablaReservas.getSelectionModel().getSelectedItem().getNombreExperiencia();
        String opSeleccionado=TablaReservas.getSelectionModel().getSelectedItem().getOperadorExperiencia();

        irAExperiencia(experienciaRepository.findByNombreAndOperador(expSeleccionada,opSeleccionado));
    }

    private void irAExperiencia(ExperienciaEntity experiencia) throws IOException{
        Stage stage = (Stage) this.btnVerExp.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("Experiencia.fxml"));
        stage.setUserData(experiencia);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void volver(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainController.class.getResourceAsStream("main.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}
