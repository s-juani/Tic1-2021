package application.project.operator;

import application.Main;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ImagenEntity;
import application.entities.ent.OperadorEntity;
import application.project.ingresar.InitialController;
import application.project.utils.Utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

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
    private ImageView imagenExperiencia0;

    @FXML
    private ImageView imagenExperiencia1;

    @FXML
    private ImageView imagenExperiencia2;

    @FXML
    private ImageView imagenExperiencia3;

    @FXML
    private ImageView imagenExperiencia4;

    @FXML
    private ImageView imagenExperiencia6;

    @FXML
    private ImageView imagenExperiencia7;

    @FXML
    private ImageView imagenExperiencia8;

    @FXML
    private ImageView imagenExperiencia9;

    @FXML
    private ImageView imagenExperiencia10;

    @FXML
    private ImageView imagenExperiencia11;

    @FXML
    private ImageView imagenExperiencia12;

    @FXML
    private ImageView imagenExperiencia13;

    @FXML
    private ImageView imagenExperiencia14;

    @FXML
    private Text nombreExperiencia0;

    @FXML
    private Text nombreExperiencia1;

    @FXML
    private Text nombreExperiencia2;

    @FXML
    private Text nombreExperiencia3;

    @FXML
    private Text nombreExperiencia4;

    @FXML
    private Text nombreExperiencia6;

    @FXML
    private Text nombreExperiencia7;

    @FXML
    private Text nombreExperiencia8;

    @FXML
    private Text nombreExperiencia9;

    @FXML
    private Text nombreExperiencia10;

    @FXML
    private Text nombreExperiencia11;

    @FXML
    private Text nombreExperiencia12;

    @FXML
    private Text nombreExperiencia13;

    @FXML
    private Text nombreExperiencia14;

    @FXML
    private Text txtPag;

    private ArrayList<ExperienciaEntity> experiencias;

    private int start_index;

    @Autowired
    private Utilities utilities;

    private OperadorEntity operador;


    public void initialize() {
        this.operador = (OperadorEntity)Main.getCurrentSession().getActiveUser();
        this.experiencias = this.utilities.getExperienciasByOperador(this.operador);
        this.refresh_experiencias();
    }

    private void refresh_experiencias() {
        int var10001 = this.start_index / 11 + 1;
        this.txtPag.setText("Página " + var10001 + " de " + (1 + this.experiencias.size() / 11));
        this.displayExperiencia(this.experiencias, this.start_index, this.imagenExperiencia0, this.nombreExperiencia0);
        this.displayExperiencia(this.experiencias, this.start_index + 1, this.imagenExperiencia1, this.nombreExperiencia1);
        this.displayExperiencia(this.experiencias, this.start_index + 2, this.imagenExperiencia2, this.nombreExperiencia2);
        this.displayExperiencia(this.experiencias, this.start_index + 3, this.imagenExperiencia3, this.nombreExperiencia3);
        this.displayExperiencia(this.experiencias, this.start_index + 4, this.imagenExperiencia4, this.nombreExperiencia4);
        this.displayExperiencia(this.experiencias, this.start_index + 5, this.imagenExperiencia6, this.nombreExperiencia6);
        this.displayExperiencia(this.experiencias, this.start_index + 6, this.imagenExperiencia7, this.nombreExperiencia7);
        this.displayExperiencia(this.experiencias, this.start_index + 7, this.imagenExperiencia8, this.nombreExperiencia8);
        this.displayExperiencia(this.experiencias, this.start_index + 8, this.imagenExperiencia9, this.nombreExperiencia9);
        this.displayExperiencia(this.experiencias, this.start_index + 9, this.imagenExperiencia10, this.nombreExperiencia10);
        this.displayExperiencia(this.experiencias, this.start_index + 10, this.imagenExperiencia11, this.nombreExperiencia11);
        this.displayExperiencia(this.experiencias, this.start_index + 11, this.imagenExperiencia12, this.nombreExperiencia12);
        this.displayExperiencia(this.experiencias, this.start_index + 12, this.imagenExperiencia13, this.nombreExperiencia13);
        this.displayExperiencia(this.experiencias, this.start_index + 13, this.imagenExperiencia14, this.nombreExperiencia14);
    }

    private void displayExperiencia(ArrayList<ExperienciaEntity> experiencias, int index, ImageView imagenExperiencia, Text nombreExperiencia) {
        try {
            nombreExperiencia.setText(((ExperienciaEntity)experiencias.get(index)).getNombre());
            imagenExperiencia.setImage(((ImagenEntity)((ExperienciaEntity)experiencias.get(index)).getImagens().iterator().next()).getJavaFxImage(159, 96));
        } catch (Exception var6) {
            nombreExperiencia.setText("");
            imagenExperiencia.setImage((Image)null);
        }

    }

    @FXML
    void irAExperiencia0(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia1(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 1));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia2(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 2));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia3(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 3));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia4(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 4));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia6(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 5));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia7(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 6));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia8(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 7));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia9(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 8));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia10(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 9));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia11(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 10));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia12(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 11));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia13(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 12));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAExperiencia14(MouseEvent event) {
        try {
            this.irAExperienciaSeleccionada((ExperienciaEntity)this.experiencias.get(this.start_index + 13));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    @FXML
    void irAPgAnterior(ActionEvent event) {
        if (this.start_index != 0) {
            this.start_index -= 14;
            this.refresh_experiencias();
        }

    }

    @FXML
    public void irAPgSiguiente(ActionEvent actionEvent) {
        if (this.experiencias.size() > this.start_index + 14) {
            this.start_index += 14;
            this.refresh_experiencias();
        }

    }

    private void irAExperienciaSeleccionada(ExperienciaEntity experiencia) throws IOException {
        Stage stage = (Stage)this.btnPgSiguiente.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        ConfigurableApplicationContext var10001 = Main.getContext();
        Objects.requireNonNull(var10001);
        fxmlLoader.setControllerFactory(var10001::getBean);
        Parent root = (Parent)fxmlLoader.load(VerExperienciaControler.class.getResourceAsStream("VerExperiencia.fxml"));
        stage.setUserData(experiencia);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)this.btnPgSiguiente.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        ConfigurableApplicationContext var10001 = Main.getContext();
        Objects.requireNonNull(var10001);
        fxmlLoader.setControllerFactory(var10001::getBean);
        Parent root = (Parent)fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
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

    @FXML
    void volverAlMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

    }

}
