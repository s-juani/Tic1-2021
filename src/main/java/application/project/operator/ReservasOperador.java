package application.project.operator;

import application.Main;
import application.entities.ent.*;
import application.project.ingresar.InitialController;
import application.project.main.ExperienceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class ReservasOperador {
    @FXML
    private Button btnVolver;

    @FXML
    private TableView<ReservaEntity> TablaReservas;

    @FXML
    private TableColumn<ReservaEntity,Date> CFechaInicio;

    @FXML
    private TableColumn<ReservaEntity,Date> CFechaFin;

    @FXML
    private TableColumn<ReservaEntity,String> CExperiencia;

    @FXML
    private TableColumn<ReservaEntity,Integer> CPersonas;

    @FXML
    private Button btnVerExp;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ExperienciaRepository experienciaRepository;

    List<ReservaEntity> reservas;

    private OperadorEntity operador;

    @FXML
    void initialize(){

        CExperiencia.setCellValueFactory(new PropertyValueFactory<ReservaEntity,String>("nombreExperiencia"));
        CFechaInicio.setCellValueFactory(new PropertyValueFactory<ReservaEntity, Date>("fechaInicio"));
        CFechaFin.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Date>("fechaFin"));
        CPersonas.setCellValueFactory(new PropertyValueFactory<ReservaEntity,Integer>("cantidad"));

        operador = Main.getCurrentSession().getActiveUser();

        reservas = reservaRepository.findByOperadorExperienciaAndFechaFinBeforeOrderByFechaFinDesc(operador.getMail(), new Date(java.util.Date.from(Instant.now()).getTime()));

        TablaReservas.getItems().addAll(reservas);

        Label placeholder = new Label();
        placeholder.setText("No hay reservas hist??ricas.");
        TablaReservas.setPlaceholder(placeholder);
    }

    @FXML
    public void verExp(ActionEvent actionEvent) throws IOException {
        String expSeleccionada = TablaReservas.getSelectionModel().getSelectedItem().getNombreExperiencia();
        operador = Main.getCurrentSession().getActiveUser();

        irAExperiencia(experienciaRepository.findByNombreAndOperador(expSeleccionada,operador.getMail()));
    }

    private void irAExperiencia(ExperienciaEntity experiencia) throws IOException{
        Stage stage = (Stage) this.btnVerExp.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(VerExperienciaControler.class.getResourceAsStream("VerExperiencia.fxml"));
        stage.setUserData(experiencia);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void volverAlMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

    }

    public void cerrarsesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
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
        String selection = TablaReservas.getSelectionModel().getSelectedItem().getMailTurista();
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
