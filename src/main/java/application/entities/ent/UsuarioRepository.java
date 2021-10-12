package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, String> {

    UsuarioEntity findOneByUsuario(String usuario);

}
