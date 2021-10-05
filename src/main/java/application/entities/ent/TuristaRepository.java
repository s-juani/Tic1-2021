package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface TuristaRepository extends CrudRepository<TuristaEntity, Long> {

    ClientesEntity findOneByUsuario(String usuario);

}
