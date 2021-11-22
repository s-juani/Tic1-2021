package application.project.admin;

import application.Main;
import application.entities.ExperienciaManager;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ImagenEntity;
import application.entities.ent.InteresEntity;
import application.entities.ent.OperadorRepository;
import application.project.ingresar.InitialController;
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
import java.util.Optional;
import java.util.Set;

@Controller
public class AproveExperienceController {
    @Autowired
    ExperienciaManager experienciaManager;

    ExperienciaEntity experiencia;

    @FXML
    private Text txtInformacionSanitaria;

    @FXML
    private Text tituloInformacionSanitaria;

    @FXML
    private Text txtExperienceName;

    @FXML
    private Text txtExperienceDescription;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView img;

    private ArrayList<ImagenEntity> imagenEntities;

    private int idxImg=0;

    @FXML
    private Text txtIntereses;

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
                        String textoIntereses = "Categorías: ";
                        Set<InteresEntity> intereses = experiencia.getIntereses();
                        for (InteresEntity interes: intereses){
                            textoIntereses = textoIntereses.concat(interes + ", ");

                        }
                        txtIntereses.setText(textoIntereses.substring(0, textoIntereses.length() - 2));
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
        Parent root = fxmlLoader.load(MainAdminController.class.getResourceAsStream("MainAdmin.fxml"));
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
        Stage stage = (Stage) this.ap.getScene().getWindow();
        stage.close();
        Main.closeCurrentSession();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    public void denegarExperiencia(ActionEvent actionEvent) throws IOException {
        Optional<ButtonType> resultado = showConfirmationAlert("IMPORTANTE","Denegar la experiencia la eliminará completamente, ¿seguro que quiere hacerlo?");
        if (resultado.isPresent() && resultado.get()==ButtonType.OK){
            experienciaManager.eliminarExperiencia(experiencia);
            volvelAlMain(new ActionEvent());
        }
    }

    public void aprovarExperiencia(ActionEvent actionEvent) throws IOException {
        Optional<ButtonType> resultado = showConfirmationAlert("IMPORTANTE","¿Seguro que quiere aprovar la experiencia?");
        if (resultado.isPresent() && resultado.get()==ButtonType.OK){
            experiencia.setAprovada(true);
            experienciaManager.actualizarExperiencia(experiencia);
            volvelAlMain(new ActionEvent());
        }
    }

    public void verDireccion(ActionEvent actionEvent) {
        showAlert(
                "Direccion:",
                experiencia.getDireccion()
        );
    }

    @Autowired
    private OperadorRepository operadorRepository;

    public void verContacto(ActionEvent actionEvent) {
        showAlert(
                "Contacto:",
                operadorRepository.findOneByMail(experiencia.getOperador()).getFormaContactosByMail()
        );
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
