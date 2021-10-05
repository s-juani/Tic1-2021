package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface OperadorRepository extends CrudRepository<OperadorEntity, String> {

    OperadorEntity findOneByUsuario(String usuario);

}

