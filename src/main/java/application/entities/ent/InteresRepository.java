package application.entities.ent;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface InteresRepository extends CrudRepository<InteresEntity,String> {
    Collection<InteresEntity> findAll();
}
