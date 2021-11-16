package application.entities.ent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface ExperienciaRepository extends CrudRepository<ExperienciaEntity,ExperienciaEntityPK> {
    @Query(name="select * from experiencia where nombre=?1 and operador=?2", nativeQuery = true)
    ExperienciaEntity findOneByNombreAndOperador(String nombre, String operador);

    Collection<ExperienciaEntity> findAll();

    Collection<ExperienciaEntity> findByAprovada(boolean aprovada);

    Set<ExperienciaEntity> findByIntereses_Nombre(String nombre);

    Set<ExperienciaEntity> findByNombreContainsIgnoreCaseOrIntereses_NombreContainsIgnoreCase(String experiencia, String interes);


}
