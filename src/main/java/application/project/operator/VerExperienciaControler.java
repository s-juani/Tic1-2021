package application.project.operator;

import application.Main;
import application.entities.ExperienciaManager;
import application.entities.ent.*;
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
                            txtCalificacionPromedio.setText("Aun no se hiieron calificaciones");
                            txtRes1Calificacion.setVisible(false);
                            txtRes1Nombre.setVisible(false);
                            txtRes1Desc.setVisible(false);

                            txtRes2Nombre.setVisible(false);
                            txtRes2Calificacion.setVisible(false);
                            txtRes2Desc.setVisible(false);

                            btnResMas.setVisible(false);
                            btnResMenos.setVisible(false);
                        } else {
                            displayRating();
                            Long prom = Math.round((calificacionRepository.countByNombreExperienciaAndOperadorExperiencia(experiencia.getNombre(), experiencia.getOperador())/Double.valueOf(calificaciones.size())));
                            String estrellas = "";
                            for (int i=0; i<prom; i++){
                                estrellas = estrellas.concat("★");
                            }

                            txtCalificacionPromedio.setText(estrellas);

                        }
                    }
                });
            }
        });
    }

    @Autowired
    private TuristaRepository turistaRepository;

    private int idx1 = 0;

    private void displayRating(){
        txtRes1Nombre.setText(turistaRepository.findByMail(calificaciones.get(idx1).getMailTurista()).getNombre());
        txtRes1Desc.setText(calificaciones.get(idx1).getComentario());
        String estrellas1 = "";
        for (int i=0; i<calificaciones.get(idx1).getPuntaje(); i++){
            estrellas1 = estrellas1.concat("★");
        }
        txtRes1Calificacion.setText(estrellas1);
        try{
            txtRes2Nombre.setText(turistaRepository.findByMail(calificaciones.get(idx1+1).getMailTurista()).getNombre());
            txtRes2Desc.setText(calificaciones.get(idx1+1).getComentario());
            String estrellas2 = "";
            for (int i=0; i<calificaciones.get(idx1+1).getPuntaje(); i++){
                estrellas2 = estrellas2.concat("★");
            }
            txtRes2Calificacion.setText(estrellas2);
            txtRes2Nombre.setVisible(true);
            txtRes2Calificacion.setVisible(true);
            txtRes2Desc.setVisible(true);
        } catch (Exception e) {
            txtRes2Nombre.setVisible(false);
            txtRes2Calificacion.setVisible(false);
            txtRes2Desc.setVisible(false);
        }
    }

    public void decRes(ActionEvent actionEvent) {
        if (idx1 != 0){
            idx1 -= 2;
            displayRating();
        }
    }

    public void incRes(ActionEvent actionEvent) {
        if (calificaciones.size() > idx1 + 2){
            idx1 += 2;
            displayRating();
        }
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


    @FXML
    void iraReservas(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnResMas.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ReservasOperador.class.getResourceAsStream("Reservas.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

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

