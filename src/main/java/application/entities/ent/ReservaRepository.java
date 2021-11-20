package application.entities.ent;

import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends CrudRepository<ReservaEntity,ReservaEntityPK> {

    @Query("select SUM (r.cantidad) from ReservaEntity r where r.operadorExperiencia = ?1 and r.nombreExperiencia = ?2 and r.fechaInicio < ?3 and r.fechaFin > ?4")
    Long countByOperadorExperienciaAndNombreExperienciaAndFechaInicioBeforeAndFechaFinAfter(String operadorExperiencia, String nombreExperiencia, Date fechaInicio, Date fechaFin);

    //Collection<ReservaEntity> findByMailTurista(String mailTurista);


    //List<ReservaEntity> findByMailTuristaOrderByFechaInicioDesc(String mailTurista);


    List<ReservaEntity> findByOperadorExperienciaAndFechaFinAfterOrderByFechaInicioAsc(String operadorExperiencia, Date fechaFin);


    List<ReservaEntity> findByMailTuristaAndFechaFinAfterOrderByFechaInicioAsc(String mailTurista, Date fechaFin);


    @Query("select r from ReservaEntity r where r.operadorExperiencia = ?1")
    List<ReservaEntity> findByOperadorExperiencia(String operadorExperiencia);



}