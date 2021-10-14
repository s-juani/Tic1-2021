package application.entities.ent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface TuristaRepository extends CrudRepository<TuristaEntity, String> {

    TuristaEntity findOneByUsuario(String usuario);

}
