package application.project.main;

import application.Main;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.TuristaEntity;
import application.project.ingresar.InitialController;
import application.project.utils.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Controller
public class MainController {

    @FXML
    public Text txtPag;

    @FXML
    private Button btnPgAnterior;

    @FXML
    private Button btnPgSiguiente;


    // ---- EXPERIENCIAS ----//

    @FXML
    private ImageView imagenExperiencia0;

    @FXML
    private Text nombreExperiencia0;

    @FXML
    private ImageView imagenExperiencia1;

    @FXML
    private Text nombreExperiencia1;

    @FXML
    private ImageView imagenExperiencia2;

    @FXML
    private Text nombreExperiencia2;

    @FXML
    private ImageView imagenExperiencia3;

    @FXML
    private Text nombreExperiencia3;

    @FXML
    private ImageView imagenExperiencia4;

    @FXML
    private Text nombreExperiencia4;

    @FXML
    private ImageView imagenExperiencia5;

    @FXML
    private Text nombreExperiencia5;

    @FXML
    private ImageView imagenExperiencia6;

    @FXML
    private Text nombreExperiencia6;

    @FXML
    private ImageView imagenExperiencia7;

    @FXML
    private Text nombreExperiencia7;

    @FXML
    private ImageView imagenExperiencia8;

    @FXML
    private Text nombreExperiencia8;

    @FXML
    private ImageView imagenExperiencia9;

    @FXML
    private Text nombreExperiencia9;

    @FXML
    private ImageView imagenExperiencia10;

    @FXML
    private Text nombreExperiencia10;

    // ----------------------//

    private TuristaEntity user;

    private ArrayList<ExperienciaEntity> sugerencias;

    private int start_index = 0;

    @Autowired
    private Utilities utilities;

    public void initialize() {
        user = Main.getCurrentSession().getActiveUser();
        sugerencias = utilities.getSugerencias(user);
        refresh_experiencias();


    }

    private void refresh_experiencias(){
        try {
            txtPag.setText("Página " + start_index/11+1 + " de " + sugerencias.size()/11+1);
            nombreExperiencia0.setText(sugerencias.get(start_index).getNombre());
            imagenExperiencia0.setImage(sugerencias.get(start_index).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia1.setText(sugerencias.get(start_index + 1).getNombre());
            imagenExperiencia1.setImage(sugerencias.get(start_index + 1).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia2.setText(sugerencias.get(start_index + 2).getNombre());
            imagenExperiencia2.setImage(sugerencias.get(start_index + 2).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia3.setText(sugerencias.get(start_index + 3).getNombre());
            imagenExperiencia3.setImage(sugerencias.get(start_index + 3).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia4.setText(sugerencias.get(start_index + 4).getNombre());
            imagenExperiencia4.setImage(sugerencias.get(start_index + 4).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia5.setText(sugerencias.get(start_index + 5).getNombre());
            imagenExperiencia5.setImage(sugerencias.get(start_index + 5).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia6.setText(sugerencias.get(start_index + 6).getNombre());
            imagenExperiencia6.setImage(sugerencias.get(start_index + 6).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia7.setText(sugerencias.get(start_index + 7).getNombre());
            imagenExperiencia7.setImage(sugerencias.get(start_index + 7).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia8.setText(sugerencias.get(start_index + 8).getNombre());
            imagenExperiencia8.setImage(sugerencias.get(start_index + 8).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia9.setText(sugerencias.get(start_index + 9).getNombre());
            imagenExperiencia9.setImage(sugerencias.get(start_index + 9).getImagens().iterator().next().getJavaFxImage(159, 96));
            nombreExperiencia10.setText(sugerencias.get(start_index + 10).getNombre());
            imagenExperiencia10.setImage(sugerencias.get(start_index + 10).getImagens().iterator().next().getJavaFxImage(159, 96));
        }catch (IndexOutOfBoundsException e){} catch (Exception ie){
            ie.printStackTrace();}
    }

    private void displayExperiencia(ArrayList<ExperienciaEntity> sugerencias,int index,ImageView imagenExperiencia, Text nombreExperiencia){
        try{
            nombreExperiencia.setText(sugerencias.get(index).getNombre());
            imagenExperiencia.setImage(sugerencias.get(index).getImagens().iterator().next().getJavaFxImage(159, 96));
        }catch (Exception ie){
            nombreExperiencia.setText("");
            imagenExperiencia.setImage(null);
            //ie.printStackTrace();
            }
    }

    @FXML
    void irAExperiencia0(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia1(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia2(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia3(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia4(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia5(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia6(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+6));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia7(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+7));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia8(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia9(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+9));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irAExperiencia10(MouseEvent event) {
        try {
            irAExperienciaSeleccionada(sugerencias.get(start_index+10));
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
        if (sugerencias.size() > start_index+11){
            start_index += 11;
            refresh_experiencias();
        }
    }

    private void irAExperienciaSeleccionada(ExperienciaEntity experiencia) throws IOException {
        Stage stage = (Stage) this.btnPgSiguiente.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienceController.class.getResourceAsStream("Experiencia.fxml"));
        Stage newStage = new Stage();
        newStage.setUserData(experiencia);
        newStage.setScene(new Scene(root));
        newStage.show();
    }

}
