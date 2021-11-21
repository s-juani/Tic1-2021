package application.entities.ent;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalificacionRepository extends CrudRepository<CalificacionEntity, CalificacionEntityPK> {

    List<CalificacionEntity> findByNombreExperienciaAndOperadorExperienciaOrderByPuntajeDesc(String nombreExperiencia, String operadorExperiencia);

    @Query("select sum(c.puntaje) from CalificacionEntity c where c.nombreExperiencia = ?1 and c.operadorExperiencia = ?2")
    Long countByNombreExperienciaAndOperadorExperiencia(String nombreExperiencia, String operadorExperiencia);
}
