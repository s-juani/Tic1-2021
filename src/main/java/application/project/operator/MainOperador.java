package application.project.operator;

import application.Main;
import application.entities.ent.OperadorEntity;
import application.entities.ent.ReservaEntity;
import application.entities.ent.ReservaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;

@Controller
public class MainOperador {

    @Autowired
    private ReservaRepository reservaRepository;

    @FXML
    private TableView<ReservaEntity> tableView;

    @FXML
    private TableColumn<ReservaEntity, Date> colFecha;

    @FXML
    private TableColumn<ReservaEntity, String> colExp;

    @FXML
    private TableColumn<ReservaEntity, Integer> colCant;

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

    ObservableList<ReservaEntity> tbData = null;

    private OperadorEntity operador = null;

    private ObservableList<TableColumn> columns;

    @FXML
    void initialize(){

        operador = Main.getCurrentSession().getActiveUser();

        tbData = FXCollections.observableList(reservaRepository.findByOperadorExperiencia(operador.getMail()));

        colFecha.setCellValueFactory(
                new PropertyValueFactory<ReservaEntity, Date>("fechaInicio")
        );
        colExp.setCellValueFactory(
                new PropertyValueFactory<ReservaEntity, String>("nombreExperiencia")
        );
        tableView.setItems(tbData);
        tableView.getColumns().addAll(colExp, colFecha, colCant);

    }



    @FXML
    void goToCrearExperencia(ActionEvent event) throws IOException {
        Stage stage = (Stage)  this.btnReservas.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(NuevaExperienciaController.class.getResourceAsStream("Nuevaexperiencia.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void goToExperiencias(ActionEvent event) throws IOException {
        Stage stage = (Stage)  this.btnReservas.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void goToReservas(ActionEvent event) throws IOException {
        Stage stage = (Stage)  this.btnReservas.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ReservasOperador.class.getResourceAsStream("Reservas.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
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
