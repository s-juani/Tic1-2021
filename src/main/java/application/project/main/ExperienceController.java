package application.project.main;

import application.Main;
import application.entities.ent.CalificacionEntity;
import application.entities.ent.CalificacionRepository;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ImagenEntity;
import application.project.ingresar.InitialController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ExperienceController {

    @FXML
    public Text txtRes1Nombre;

    @FXML
    public Text txtRes1Desc;

    @FXML
    public Text txtRes2Nombre;

    @FXML
    public Text txtRes2Desc;

    @FXML
    public Button btnDejarComentario;

    @FXML
    public Text txtCalificacionPromedio;

    @FXML
    public Text txtRes1Calificacion;

    @FXML
    public Text txtRes2Calificacion;

    @FXML
    public Button btnResMenos;

    @FXML
    public Button btnResMas;

    @FXML
    public Text txtDescCalificacionPromedio;

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

    @FXML
    private Text txtInformacionSanitaria;

    @FXML
    private Text tituloInformacionSanitaria;

    private ArrayList<ImagenEntity> imagenEntities;

    private int idxImg=0;

    @Autowired
    private CalificacionRepository calificacionRepository;

    List<CalificacionEntity> calificaciones;

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
                        if (!experiencia.isConReserva()){
                            btnReservar.setVisible(false);
                        }
                        String textoInfoSanitaria = "";
                        if (experiencia.isVacunacion()){
                            textoInfoSanitaria += "•Se debe presentar certificado de vacunación completa ";
                        }
                        if (experiencia.getAforo()!=null){
                            textoInfoSanitaria += "•Aforo: " + experiencia.getAforo();
                        }
                        txtInformacionSanitaria.setText(textoInfoSanitaria);
                        if (textoInfoSanitaria.equals("")){
                            tituloInformacionSanitaria.setText("");
                        }

                        calificaciones = calificacionRepository.findByNombreExperienciaAndOperadorExperienciaOrderByPuntajeDesc(experiencia.getNombre(), experiencia.getOperador());

                        if (calificaciones.size() == 0){
                            txtCalificacionPromedio.setText("Aun no se hicieron calificaciones");
                            txtRes1Calificacion.setVisible(false);
                            txtRes1Nombre.setVisible(false);
                            txtRes1Desc.setVisible(false);

                            txtRes2Nombre.setVisible(false);
                            txtRes2Calificacion.setVisible(false);
                            txtRes2Desc.setVisible(false);

                            btnResMas.setVisible(false);
                            btnResMenos.setVisible(false);
                        } else {
                            // TODO init cuando si hay comentarios
                        }
                    }
                });
            }
        });

        // TODO armar estructura por "sets" para ver mas comentarios

    }

    public void decRes(ActionEvent actionEvent) {
    }

    public void incRes(ActionEvent actionEvent) {
    }

    public void nuevoComentario(ActionEvent actionEvent) {
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
        stage.setScene(new Scene(root));
        stage.show();
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

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.btnReservar.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }


}
