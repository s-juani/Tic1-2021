package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<ClientesEntity, Long> {

    ClientesEntity findOneByUsuario(String usuario);

}
