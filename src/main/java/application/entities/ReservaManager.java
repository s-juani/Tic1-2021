package application.entities;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ReservaEntity;
import application.entities.ent.ReservaRepository;
import application.entities.ent.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class ReservaManager {

    @Autowired
    private ReservaRepository reservaRepository;

    public void createReserva(UsuarioEntity user, ExperienciaEntity experiencia, LocalDate fechaInicio, LocalDate fechaFin, Integer cantidad) {

        try {

            Date inicio = Date.valueOf(fechaInicio);
            Date fin = null;
            if (fechaFin != null){
                fin = Date.valueOf(fechaFin);
            }

            System.out.println("Por crear reserva");
            ReservaEntity reserva = new ReservaEntity(user.getMail(), experiencia.getOperador(), experiencia.getNombre(), inicio, cantidad, fin);
            System.out.println("Reserva creada");
            reservaRepository.save(reserva);
            System.out.println("Reserva guardada");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
