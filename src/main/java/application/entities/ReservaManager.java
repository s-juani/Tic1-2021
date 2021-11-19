package application.entities;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ReservaEntity;
import application.entities.ent.ReservaRepository;
import application.entities.ent.UsuarioEntity;
import application.entities.exceptions.AforoCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@Component
public class ReservaManager {

    private Date addDaysToFecha(Date fecha, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DATE, days);
        return (Date) c.getTime();
    }

    @Autowired
    private ReservaRepository reservaRepository;

    public void createReserva(UsuarioEntity user, ExperienciaEntity experiencia, LocalDate fechaInicio, LocalDate fechaFin, Integer cantidad)
    throws AforoCompleto {

        try {

            Date inicio = Date.valueOf(fechaInicio);
            Date fin = inicio;
            if (fechaFin != null){
                fin = Date.valueOf(fechaFin);
            }
            for (Date i = inicio; i.before(addDaysToFecha(fin,1)) ; addDaysToFecha(i,1)) {
                if (reservaRepository.countByOperadorExperienciaAndNombreExperienciaAndFechaInicioBeforeAndFechaFinAfter(
                        experiencia.getOperador(), experiencia.getNombre(), addDaysToFecha(i,1),addDaysToFecha(i,-1))
                        > experiencia.getAforo()){
                    throw new AforoCompleto();
                }

            }

            System.out.println("Por crear reserva");
            ReservaEntity reserva = new ReservaEntity(user.getMail(), experiencia.getOperador(), experiencia.getNombre(), inicio, cantidad, fin);
            System.out.println("Reserva creada");
            reservaRepository.save(reserva);
            System.out.println("Reserva guardada");
        }
        catch (AforoCompleto e2){
            throw e2;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
