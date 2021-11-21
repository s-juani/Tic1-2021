package application.project.operator;

import application.Main;
import application.project.ingresar.InitialController;
import application.project.main.ReservasController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

@Controller
public class ExperienciasOperador {

    @FXML
    private Button btnReservas;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnPgAnterior;

    @FXML
    private Button btnPgSiguiente;

    @FXML
    private Text txtPag;

    @FXML
    private Text nombreExperiencia0;

    @FXML
    private ImageView imagenExperiencia0;

    @FXML
    private Text nombreExperiencia1;

    @FXML
    private ImageView imagenExperiencia1;

    @FXML
    private Text nombreExperiencia2;

    @FXML
    private ImageView imagenExperiencia2;

    @FXML
    private Text nombreExperiencia3;

    @FXML
    private ImageView imagenExperiencia3;

    @FXML
    private Text nombreExperiencia4;

    @FXML
    private ImageView imagenExperiencia4;

    @FXML
    private Text nombreExperiencia6;

    @FXML
    private ImageView imagenExperiencia6;

    @FXML
    private Text nombreExperiencia7;

    @FXML
    private ImageView imagenExperiencia7;

    @FXML
    private Text nombreExperiencia8;

    @FXML
    private ImageView imagenExperiencia8;

    @FXML
    private Text nombreExperiencia9;

    @FXML
    private ImageView imagenExperiencia9;

    @FXML
    private Text nombreExperiencia10;

    @FXML
    private ImageView imagenExperiencia10;

    @FXML
    private Text nombreExperiencia11;

    @FXML
    private ImageView imagenExperiencia11;

    @FXML
    private Text nombreExperiencia12;

    @FXML
    private ImageView imagenExperiencia12;

    @FXML
    private Text nombreExperiencia13;

    @FXML
    private ImageView imagenExperiencia13;

    @FXML
    private Text nombreExperiencia14;

    @FXML
    private ImageView imagenExperiencia14;

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
    void irAExperiencia11(MouseEvent event) {

    }

    @FXML
    void irAExperiencia12(MouseEvent event) {

    }

    @FXML
    void irAExperiencia13(MouseEvent event) {

    }

    @FXML
    void irAExperiencia14(MouseEvent event) {

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

    @FXML
    void irAPgSiguiente(ActionEvent event) {

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

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnPgSiguiente.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void iraReservas(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnReservas.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ReservasOperador.class.getResourceAsStream("Reservas.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

    }

}
