package application.entities.ent;

import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends CrudRepository<ReservaEntity,ReservaEntityPK> {

}