package application.project.main;

import application.Main;
import application.entities.CalificacionManager;
import application.entities.ent.ExperienciaEntity;
import application.entities.ent.TuristaEntity;
import application.entities.exceptions.InvalidInformation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

@Controller
public class DejarComentarioController {
    @FXML
    private TextArea campoComentario;

    @FXML
    private Text star1;

    @FXML
    private Text star2;

    @FXML
    private Text star3;

    @FXML
    private Text star4;

    @FXML
    private Text star5;

    private byte stars = 0;

    @Autowired
    private CalificacionManager calificacionManager;

    @FXML
    void cancelarComentario(ActionEvent event) {
        Stage stage = (Stage) this.campoComentario.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enviarComentario(ActionEvent event) {
        String comentario = campoComentario.getText();
        Stage stage = (Stage) star2.getScene().getWindow();
        ExperienciaEntity experiencia = (ExperienciaEntity) stage.getUserData();
        try {
            calificacionManager.addCalificacion(comentario,stars,experiencia, (TuristaEntity) Main.getCurrentSession().getActiveUser());
            showAlert(
                    "Comentario registrado",
                    "El comentario ha sido registrado exitosamente"
            );
            stage.close();

        } catch (InvalidInformation e) {
            showAlert(
                    "Datos faltantes",
                    "No hay comentario o puntaje"
            );
        }

    }

    @FXML
    void rate1(MouseEvent event) {
        stars = 1;
        setStars();
    }

    @FXML
    void rate2(MouseEvent event) {
        stars = 2;
        setStars();
    }

    @FXML
    void rate3(MouseEvent event) {
        stars = 3;
        setStars();
    }

    @FXML
    void rate4(MouseEvent event) {
        stars = 4;
        setStars();
    }

    @FXML
    void rate5(MouseEvent event) {
        stars = 5;
        setStars();
    }

    private void setStars(){
        star1.setText("★");
        star2.setText((stars>=2) ? "★" : "☆");
        star3.setText((stars>=3) ? "★" : "☆");
        star4.setText((stars>=4) ? "★" : "☆");
        star5.setText((stars==5) ? "★" : "☆");
    }

    private void showAlert(String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
