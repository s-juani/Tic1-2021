package application.project.operator;

import application.Main;
import application.entities.ent.*;
import application.project.ingresar.InitialController;
import javafx.application.Platform;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
public class MainOperador {

    @FXML
    public Button btnVerUser;

    @FXML
    private TableView<ReservaEntity> tvReservas;

    @FXML
    private TableColumn<ReservaEntity, Date> tcFecha;

    @FXML
    private TableColumn<ReservaEntity, Date> tcFechaF;

    @FXML
    private TableColumn<ReservaEntity, String> tcExperiencia;

    @FXML
    private TableColumn<ReservaEntity, Integer> tcCantPersonas;

    @FXML
    private Button btnCrearExperiencia;

    @FXML
    private Button btnVerExperiencias;

    @FXML
    private Button btnReservas;

    private OperadorEntity operador;

    private List<ReservaEntity> reservas;

    @Autowired
    private ReservaRepository reservaRepository;

    @FXML
    void initialize(){

        operador = Main.getCurrentSession().getActiveUser();

        tcExperiencia.setCellValueFactory(new PropertyValueFactory<ReservaEntity,String>("nombreExperiencia"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<ReservaEntity, Date>("fechaInicio"));
        tcFechaF.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Date>("fechaFin"));
        tcCantPersonas.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Integer>("cantidad"));

        reservas = reservaRepository.findByOperadorExperienciaAndFechaFinAfterOrderByFechaInicioAsc(operador.getMail(), new Date(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)).getTime()));

        tvReservas.getItems().addAll(reservas);

        Label placeholder = new Label();
        placeholder.setText("No hay reservas proximas");
        tvReservas.setPlaceholder(placeholder);
    }

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @FXML
    public void VerExp(ActionEvent actionEvent) throws IOException {
        String expSeleccionada = tvReservas.getSelectionModel().getSelectedItem().getNombreExperiencia();
        operador = Main.getCurrentSession().getActiveUser();

        irAExperiencia(experienciaRepository.findByNombreAndOperador(expSeleccionada,operador.getMail()));
    }


    private void irAExperiencia(ExperienciaEntity experiencia) throws IOException{
        Stage stage = (Stage) this.btnCrearExperiencia.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(VerExperienciaControler.class.getResourceAsStream("VerExperiencia.fxml"));
        stage.setUserData(experiencia);
        stage.setScene(new Scene(root));
        stage.show();
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
    void goToExperiencias(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnReservas.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienciasOperador.class.getResourceAsStream("Experiencias.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void goToReservas(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnReservas.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ReservasOperador.class.getResourceAsStream("Reservas.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }


    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnCrearExperiencia.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @Autowired
    private TuristaRepository turistaRep;

    public void verUser(ActionEvent actionEvent) {
        String selection = tvReservas.getSelectionModel().getSelectedItem().getMailTurista();
        TuristaEntity turista = turistaRep.findByMail(selection);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String tipo;
        if (turista.getTipoDocumento()){
            tipo = "Pasaporte";
        } else {
            tipo = "Documento de Identidad";
        }
        alert.setTitle("Usuario");
        alert.setHeaderText("Datos de usuario:");
        alert.setContentText(
                "Nombre: "+turista.getNombre()+"\n" +
                        "Documento: "+turista.getNroDocumento().toString()+"\n" +
                        "Tipo de documento: "+tipo+"\n"+
                        "Origen de documento: " +turista.getOrigenDocumento()+"\n"+
                        "Mail: "+turista.getMail()
        );
        alert.showAndWait();
    }
}