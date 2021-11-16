package application.project.main;


import application.Main;
import application.entities.ReservaManager;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ImagenEntity;
import application.entities.exceptions.AforoCompleto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ReservaController {

    private ExperienciaEntity experiencia;

    @Autowired
    private ReservaManager reservaMgr;

    @FXML
    private AnchorPane ap;

    @FXML
    private Text txtExperienceName;

    @FXML
    private Text txtExperienceDescription;

    @FXML
    private ImageView img;

    @FXML
    private Button btnVolver;

    @FXML
    private Label txtFechaInicio;

    @FXML
    private Button btnDiasReserva;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private Label txtFechaFin;

    @FXML
    private DatePicker dateFechaFin;

    @FXML
    private CheckBox checkRequisitos;

    @FXML
    private Button btnReservar;

    private boolean reservaMultiplesDias = true;

    private ArrayList<ImagenEntity> imagenEntities;

    private int idxImg=0;

    @FXML
    private Button btnMenos;

    @FXML
    private Button btnMas;

    @FXML
    private Label txtCantidad;

    private Integer cantidad = 1;

    @FXML
    void decrementar(ActionEvent event) {
        if (cantidad > 1){
            cantidad -= 1;
            txtCantidad.setText(cantidad.toString());
        }
    }

    @FXML
    void incrementar(ActionEvent event){
        cantidad += 1;
        txtCantidad.setText(cantidad.toString());
    }

    @FXML
    void toggleDiasReserva(ActionEvent event) {
        reservaMultiplesDias = !reservaMultiplesDias;
        if (!reservaMultiplesDias) {
            txtFechaInicio.setText("Fecha de la reserva");
            txtFechaFin.setVisible(false);
            dateFechaFin.setVisible(false);
            btnDiasReserva.setText("Reservar múltiples días");
        } else {
            txtFechaInicio.setText("Fecha de inicio de la reserva");
            txtFechaFin.setVisible(true);
            dateFechaFin.setVisible(true);
            btnDiasReserva.setText("Reservar un solo día");
        }
    }

    @FXML
    void initialize(){

        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // Si llego acá es que se abrió el stage
                        Stage stage = (Stage) ap.getScene().getWindow();
                        experiencia = (ExperienciaEntity) stage.getUserData();
                        txtExperienceName.setText(experiencia.getNombre());
                        txtExperienceDescription.setText(experiencia.getDescripcion());
                        imagenEntities = new ArrayList<>(experiencia.getImagens());
                        showImagen();
                    }
                });
            }
        });



        toggleDiasReserva(new ActionEvent());

        dateFechaFin.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        dateFechaInicio.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

    }

    private void showImagen(){
        img.setImage(imagenEntities.get(idxImg).getJavaFxImage(600, 400));
    }


    @FXML
    void imgAnterior(ActionEvent event) {
        idxImg=Math.floorMod(idxImg-1,imagenEntities.size());
        showImagen();
    }

    @FXML
    void siguienteImg(ActionEvent event) {
        idxImg=Math.floorMod(idxImg+1,imagenEntities.size());
        showImagen();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.ap.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainController.class.getResourceAsStream("Experiencia.fxml"));
        stage.setScene(new Scene(root));
        stage.setUserData(experiencia);
        stage.show();
    }

    @FXML
    void reservar(ActionEvent event) throws IOException, AforoCompleto {
        if (checkRequisitos.isSelected() ){
            if (dateFechaInicio.getValue() != null){
                if (reservaMultiplesDias){
                    if (dateFechaFin.getValue() != null){
                        try {
                            reservaMgr.createReserva(Main.getCurrentSession().getActiveUser(), experiencia, dateFechaInicio.getValue(), dateFechaFin.getValue(), cantidad);
                            showAlert("Felicitaciones!",
                                    "Su reserva se registro con exito!"
                            );
                        } catch (AforoCompleto e) {
                            showAlert("ERROR!",
                                    "No hay suficiente aforo"
                            );
                        }
                        volver(new ActionEvent());
                    }
                    else{
                        showAlert("ERROR!",
                                "Debe debe seleccionar una fecha de finalizacion"
                        );
                    }
                }
                else {
                    try{
                        reservaMgr.createReserva(Main.getCurrentSession().getActiveUser(), experiencia, dateFechaInicio.getValue(), null, cantidad);
                        showAlert("Felicitaciones!",
                                "Su reserva se registro con exito!"
                        );
                    } catch (AforoCompleto e){
                        showAlert("ERROR!",
                                "No hay suficiente aforo"
                        );
                    }
                    volver(new ActionEvent());
                }
            }
            else {
                showAlert("ERROR!",
                        "Debe debe seleccionar una fecha"
                );
            }
        }
        else {
            showAlert("ERROR!",
                    "Debe aceptar las condiciones."
            );
        }
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
