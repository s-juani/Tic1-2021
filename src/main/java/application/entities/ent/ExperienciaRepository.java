package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface ExperienciaRepository extends CrudRepository<ExperienciaEntity,ExperienciaEntityPK> {
    ExperienciaEntity findOneByNombre(String nombre);
}
