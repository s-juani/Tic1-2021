package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    UsuarioEntity findOneByUsuario(String usuario);

}
