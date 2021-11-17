package application.entities.ent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OperadorRepository extends CrudRepository<OperadorEntity, String> {

    OperadorEntity findOneByUsuario(String usuario);

}

