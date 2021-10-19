package application.project.utils;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ExperienciaRepository;
import application.entities.ent.InteresEntity;
import application.entities.ent.TuristaEntity;
import javafx.collections.ObservableList;

import java.util.Collection;

@Component
public class Utilities {


    public static ObservableList<ExperienciaEntity> getSugerencias(TuristaEntity user){
        /**
         * Este es el famoso "algoritmo de sugerencias".
         */

        Collection<ExperienciaEntity> experiencias = experienciaRepository.findAll();

        Collection<InteresEntity> userInterests = user.getIntereses();

        //TODO terminar

        return null;

    }

}
