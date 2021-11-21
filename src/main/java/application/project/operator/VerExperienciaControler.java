package application.project.operator;

import application.Main;
import application.entities.ExperienciaManager;
import application.entities.ent.CalificacionEntity;
import application.entities.ent.CalificacionRepository;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ImagenEntity;
import application.project.ingresar.InitialController;
import application.project.main.ExperienceController;
import application.project.main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VerExperienciaControler {

    @FXML
    public Text txtRes1Nombre;

    @FXML
    public Text txtRes1Desc;

    @FXML
    public Text txtRes2Nombre;

    @FXML
    public Text txtRes2Desc;

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
    private Text txtInformacionSanitaria;

    @FXML
    private Text tituloInformacionSanitaria;

    private ArrayList<ImagenEntity> imagenEntities;

    private int idxImg=0;

    @Autowired
    private CalificacionRepository calificacionRepository;

    List<CalificacionEntity> calificaciones;

    @Autowired
    private ExperienciaManager experienciaManager;

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

    @FXML
    public void eliminarExperiencia(ActionEvent actionEvent) throws IOException {
        Optional<ButtonType> resultado = showConfirmationAlert("IMPORTANTE","Borrar la experiencia la eliminará completamente, ¿seguro que quiere hacerlo?");
        if (resultado.isPresent() && resultado.get()==ButtonType.OK){
            experienciaManager.eliminarExperiencia(experiencia);
            volvelAlMain(new ActionEvent());
        }
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
        Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Optional<ButtonType> showConfirmationAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        return alert.showAndWait();
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.txtExperienceName.getScene().getWindow();
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

