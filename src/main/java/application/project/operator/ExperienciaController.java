package application.project.operator;
//
//import application.Main;
//import application.entities.ent.ImagenEntity;
//import application.project.ingresar.InitialController;
//import application.project.main.MainController;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.Text;
//import javafx.scene.control.MenuItem;
//import javafx.stage.Stage;
//import org.springframework.stereotype.Controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Controller
//public class ExperienciaController {
//    @FXML
//    private AnchorPane ap;
//
//    @FXML
//    private MenuItem btnCerrarSesion;
//
//    @FXML
//    private Button btnReservas;
//
//    @FXML
//    private Button btnVolver;
//
//    @FXML
//    private Button btnEliminar;
//
//    @FXML
//    private Text txtExperienceName;
//
//    @FXML
//    private Text txtExperienceDescription;
//
//    @FXML
//    private Text txtDescCalificacionPromedio;
//
//    @FXML
//    private Text txtRes1Nombre;
//
//    @FXML
//    private Text txtRes1Desc;
//
//    @FXML
//    private Text txtRes2Nombre;
//
//    @FXML
//    private Text txtRes2Desc;
//
//    @FXML
//    private Button btnDejarComentario;
//
//    @FXML
//    private Text txtCalificacionPromedio;
//
//    @FXML
//    private Text txtRes1Calificacion;
//
//    @FXML
//    private Text txtRes2Calificacion;
//
//    @FXML
//    private ImageView img;
//
//    @FXML
//    private Text txtInformacionSanitaria;
//
//    @FXML
//    private Text tituloInformacionSanitaria;
//
//    @FXML
//    private Button btnResMenos;
//
//    @FXML
//    private Button btnResMas;
//
//    private ArrayList<ImagenEntity> imagenEntities;
//
//    private int idxImg=0;
//
//    @FXML
//    void decRes(ActionEvent event) {
//
//    }
//
//    @FXML
//    void eliminarExp(ActionEvent event) {
//
//    }
//
//    @FXML
//    void incRes(ActionEvent event) {
//
//    }
//
//    @FXML
//    void iraReservas(ActionEvent event) {
//
//    }
//
//    @FXML
//    void siguienteImg(ActionEvent event) {
//        idxImg=Math.floorMod(idxImg+1,imagenEntities.size());
//        showImagen();
//    }
//
//    @FXML
//    void imgAnterior(ActionEvent event) {
//        idxImg=Math.floorMod(idxImg-1,imagenEntities.size());
//        showImagen();
//    }
//
//    private void showImagen(){
//        img.setImage(imagenEntities.get(idxImg).getJavaFxImage(600, 400));
//    }
//
//    @FXML
//    void volvelAlMain(ActionEvent event) {
//        Stage stage = (Stage) this.ap.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//        Parent root = fxmlLoader.load(MainOperador.class.getResourceAsStream("MainOperador.fxml"));
//        stage.setScene(new Scene(root));
//        stage.show();
//
//    }
//
//    @FXML
//    void cerrarSesion(ActionEvent event) throws IOException {
//        Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
//        stage.close();
//        Main.closeCurrentSession();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//        Parent root = fxmlLoader.load(InitialController.class.getResourceAsStream("initial.fxml"));
//        Stage newStage = new Stage();
//        newStage.setScene(new Scene(root));
//        newStage.show();
//
//    }
//
//}
