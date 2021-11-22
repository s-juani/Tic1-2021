package application.project.main;


import application.Main;
import application.entities.ReservaManager;
import application.entities.ent.*;
import application.entities.exceptions.AforoCompleto;
import application.entities.session.currentSession;
import application.project.ingresar.InitialController;
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
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class ReservaController {

    @FXML
    public AnchorPane apDatosPersonales;

    @FXML
    public ComboBox cbTipoDoc;

    @FXML
    public ComboBox<PaisEntity> cbOrigenDoc;

    @FXML
    public TextField txtNumeroDoc;

    private ExperienciaEntity experiencia;

    @Autowired
    private ReservaManager reservaMgr;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnReservas;

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

    @Autowired
    private PaisRepository paisRep;

    private void addCountries(){
        Collection<PaisEntity> paises = paisRep.findAll();
        cbOrigenDoc.getItems().addAll(paises);
    }

    private void addTipos(){
        cbTipoDoc.getItems().add("Documento de Identidad");
        cbTipoDoc.getItems().add("Pasaporte");
    }

    private TuristaEntity turista;

    @Autowired
    private TuristaRepository turistaRep;

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
                        addCountries();
                        addTipos();
                        turista = Main.getCurrentSession().getActiveUser();
                        if (turista.getNroDocumento() != null){
                            apDatosPersonales.setVisible(false);
                        }
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
        txtCantidad.setText(cantidad.toString());
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
    void reservar(ActionEvent event) throws IOException {
        if (checkRequisitos.isSelected() ){
            if (dateFechaInicio.getValue() != null){
                if (reservaMultiplesDias){
                    System.out.println("Reserva multiples dias");
                    if (dateFechaFin.getValue() != null){
                        if (turista.getNroDocumento() == null){
                            System.out.println("No tiene doc");
                            System.out.println(cbTipoDoc.getValue());
                            System.out.println(cbOrigenDoc.getValue());
                            System.out.println(txtNumeroDoc.getText());
                            if (cbTipoDoc.getValue() != null && cbOrigenDoc.getValue() != null && txtNumeroDoc.getText() != null && !txtNumeroDoc.getText().equals("")){
                                System.out.println("De cheto");
                                turista.setOrigenDocumento(cbOrigenDoc.getValue());
                                turista.setNroDocumento(txtNumeroDoc.getText());
                                turista.setTipoDocumento(cbTipoDoc.getValue() == "Pasaporte");
                                turistaRep.save(turista);
                                Main.setCurrentSession(new currentSession(turista));
                                try {
                                    reservaMgr.createReserva(Main.getCurrentSession().getActiveUser(), experiencia, dateFechaInicio.getValue(), dateFechaFin.getValue(), cantidad);
                                    showAlert("Felicitaciones!",
                                            "Su reserva se registro con exito!"
                                    );
                                } catch(AforoCompleto e) {
                                    showAlert("ERROR!",
                                            "No hay suficiente aforo"
                                    );
                                }
                                volver(new ActionEvent());
                            }
                            else {
                                System.out.println("Comp datos");
                                showAlert("ERROR!",
                                        "Debe completar todos los datos"
                                );
                            }
                        } else {
                            System.out.println("Tiene doc");
                            try {
                                reservaMgr.createReserva(Main.getCurrentSession().getActiveUser(), experiencia, dateFechaInicio.getValue(), dateFechaFin.getValue(), cantidad);
                                showAlert("Felicitaciones!",
                                        "Su reserva se registro con exito!"
                                );
                            } catch(AforoCompleto e) {
                                showAlert("ERROR!",
                                        "No hay suficiente aforo"
                                );
                            }

                            volver(new ActionEvent());
                        }
                    }
                    else{
                        showAlert("ERROR!",
                                "Debe debe seleccionar una fecha de finalizacion"
                        );
                    }
                }
                else {
                    System.out.println("Reserva unico dia");
                    if (turista.getNroDocumento() == null){
                        if (cbTipoDoc.getValue() != null && cbOrigenDoc.getValue() != null && txtNumeroDoc.getText() != null && !txtNumeroDoc.getText().equals("")){
                            turista.setOrigenDocumento(cbOrigenDoc.getValue());
                            turista.setNroDocumento(txtNumeroDoc.getText());
                            turista.setTipoDocumento(cbTipoDoc.getValue() == "Pasaporte");
                            turistaRep.save(turista);
                            Main.setCurrentSession(new currentSession(turista));
                            try {
                                reservaMgr.createReserva(Main.getCurrentSession().getActiveUser(), experiencia, dateFechaInicio.getValue(), dateFechaFin.getValue(), cantidad);
                                showAlert("Felicitaciones!",
                                        "Su reserva se registro con exito!"
                                );
                            } catch(AforoCompleto e) {
                                showAlert("ERROR!",
                                        "No hay suficiente aforo"
                                );
                            }
                            volver(new ActionEvent());
                        }
                        else {
                            System.out.println("Values");
                            System.out.println(cbTipoDoc.getValue());
                            System.out.println(cbOrigenDoc.getValue());
                            System.out.println(txtNumeroDoc.getText());
                            showAlert("ERROR!",
                                    "Debe completar todos los datos"
                            );
                        }
                    } else {
                        try {
                            reservaMgr.createReserva(Main.getCurrentSession().getActiveUser(), experiencia, dateFechaInicio.getValue(), null, cantidad);
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

    @FXML
    void iraReservas(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.btnReservas.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ReservasController.class.getResourceAsStream("Reservas.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();

    }
}
