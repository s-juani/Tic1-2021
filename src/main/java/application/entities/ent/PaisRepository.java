package application.entities.ent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PaisRepository extends CrudRepository<PaisEntity,String> {
    Collection<PaisEntity> findAll();
}
