package application.entities.ent;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalificacionRepository extends CrudRepository<CalificacionEntity, CalificacionEntityPK> {

    List<CalificacionEntity> findByNombreExperienciaAndOperadorExperienciaOrderByPuntajeDesc(String nombreExperiencia, String operadorExperiencia);

}
