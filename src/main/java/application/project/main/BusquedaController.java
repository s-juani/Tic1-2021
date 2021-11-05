package application.project.main;

import application.Main;
import application.entities.ent.ExperienciaEntity;
import application.project.utils.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class BusquedaController {

    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnPgAnterior;

    @FXML
    private Button btnPgSiguiente;

    @FXML
    private CheckBox chbFiltro1;

    @FXML
    private CheckBox chbFiltro2;

    @FXML
    private CheckBox chbFiltro3;

    @FXML
    private CheckBox chbFiltro4;

    @FXML
    private CheckBox chbFiltro5;

    @FXML
    private CheckBox chbFiltro6;

    @FXML
    private CheckBox chbFiltro7;

    @FXML
    private CheckBox chbFiltro8;

    @FXML
    private CheckBox chbFiltro9;

    @FXML
    private ImageView imagenExperiencia0;

    @FXML
    private ImageView imagenExperiencia1;

    @FXML
    private ImageView imagenExperiencia10;

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
    private Text nombreExperiencia0;

    @FXML
    private Text nombreExperiencia1;

    @FXML
    private Text nombreExperiencia10;

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
    private Text txtPag;

    private ArrayList<ExperienciaEntity> resultado_busqueda;

    private int start_index;

    @Autowired
    private Utilities utilities;

    public void initialize() {
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // Si llego acá es que se abrió el stage
                        Stage stage = (Stage) ap.getScene().getWindow();
                        String busqueda=(String) stage.getUserData();
                        resultado_busqueda = utilities.getBusqueda(busqueda);
                        refresh_experiencias();
                    }
                });
            }
        });

    }

    private void refresh_experiencias(){
        txtPag.setText("Página " + (start_index/11 +1) + " de " + (1+(resultado_busqueda.size()/11)));
        displayExperiencia(resultado_busqueda,start_index,imagenExperiencia0, nombreExperiencia0);
        displayExperiencia(resultado_busqueda,start_index+1,imagenExperiencia1, nombreExperiencia1);
        displayExperiencia(resultado_busqueda,start_index+2,imagenExperiencia2, nombreExperiencia2);
        displayExperiencia(resultado_busqueda,start_index+3,imagenExperiencia3, nombreExperiencia3);
        displayExperiencia(resultado_busqueda,start_index+4,imagenExperiencia4, nombreExperiencia4);
        displayExperiencia(resultado_busqueda,start_index+6,imagenExperiencia6, nombreExperiencia6);
        displayExperiencia(resultado_busqueda,start_index+7,imagenExperiencia7, nombreExperiencia7);
        displayExperiencia(resultado_busqueda,start_index+8,imagenExperiencia8, nombreExperiencia8);
        displayExperiencia(resultado_busqueda,start_index+9,imagenExperiencia9, nombreExperiencia9);
        displayExperiencia(resultado_busqueda,start_index+10,imagenExperiencia10, nombreExperiencia10);
    }

    private void displayExperiencia(ArrayList<ExperienciaEntity> resultado_busqueda,int index,ImageView imagenExperiencia, Text nombreExperiencia){
        try{
            nombreExperiencia.setText(resultado_busqueda.get(index).getNombre());
            imagenExperiencia.setImage(resultado_busqueda.get(index).getImagens().iterator().next().getJavaFxImage(159, 96));
        }catch (Exception ie){
            nombreExperiencia.setText("");
            imagenExperiencia.setImage(null);
            //ie.printStackTrace();
        }
    }
    @FXML
    void irAExperiencia0(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia1(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia2(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia3(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia4(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia6(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+6));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia7(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+7));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia8(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia9(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+9));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia10(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(resultado_busqueda.get(start_index+10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAPgAnterior(ActionEvent event) {
        if (start_index != 0){
            start_index -= 11;
            refresh_experiencias();
        }
    }

    @FXML
    public void irAPgSiguiente(ActionEvent actionEvent) {
        if (resultado_busqueda.size() > start_index+11){
            start_index += 11;
            refresh_experiencias();
        }
    }

    private void irAExperienciaSeleccionada(ExperienciaEntity experiencia) throws IOException {
        Stage stage = (Stage) this.btnPgSiguiente.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("Experiencia.fxml"));
        stage.setUserData(experiencia);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
