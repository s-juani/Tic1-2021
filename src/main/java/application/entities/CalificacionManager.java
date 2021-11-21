package application.entities;

import application.entities.ent.*;
import application.entities.exceptions.InvalidInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class CalificacionManager {

    @Autowired
    private CalificacionRepository calificacionRepository;

    public void addCalificacion(String comentario, byte puntaje, ExperienciaEntity experiencia, TuristaEntity turista) throws InvalidInformation {
        if (comentario.equals("") || puntaje==0) {
            throw new InvalidInformation();
        }
        try {
            CalificacionEntity calificacion = new CalificacionEntity(experiencia.getOperador(), experiencia.getNombre(),
                    turista.getMail(), puntaje, comentario, Timestamp.valueOf(LocalDateTime.now()));
            calificacionRepository.save(calificacion);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
