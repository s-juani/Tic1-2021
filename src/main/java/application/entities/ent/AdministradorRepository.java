package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface AdministradorRepository extends CrudRepository<AdministradorEntity,String> {

    AdministradorEntity findOneByUsuario(String usuario);
}
