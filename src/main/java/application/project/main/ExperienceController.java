package application.project.main;

import application.Main;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ImagenEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;


@Controller
public class ExperienceController {

    ExperienciaEntity experiencia;

    @FXML
    private Text txtExperienceName;

    @FXML
    private Text txtExperienceDescription;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView img;

    @FXML
    private Button btnReservar;

    private ArrayList<ImagenEntity> imagenEntities;

    private int idxImg=0;

    @FXML
    public void initialize(){
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // Si llego acá es que se abrió el stage
                        Stage stage = (Stage) ap.getScene().getWindow();
                        experiencia=(ExperienciaEntity) stage.getUserData();
                        txtExperienceName.setText(experiencia.getNombre());
                        txtExperienceDescription.setText(experiencia.getDescripcion());
                        imagenEntities = new ArrayList<>(experiencia.getImagens());
                        showImagen();
                    }
                });
            }
        });

    }

    @FXML
    public void siguienteImg(ActionEvent actionEvent) {
        idxImg=Math.floorMod(idxImg+1,imagenEntities.size());
        showImagen();
    }

    @FXML
    public void imgAnterior(ActionEvent actionEvent) {
        idxImg=Math.floorMod(idxImg-1,imagenEntities.size());
        showImagen();
    }

    private void showImagen(){
        img.setImage(imagenEntities.get(idxImg).getJavaFxImage(600, 400));
    }

    @FXML
    public void volvelAlMain(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.ap.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainController.class.getResourceAsStream("main.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void gotoReserva(ActionEvent event)throws IOException {
        Stage stage = (Stage) this.ap.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainController.class.getResourceAsStream("Reserva.fxml"));
        stage.setScene(new Scene(root));
        stage.setUserData(experiencia);
        stage.show();
    }

}
