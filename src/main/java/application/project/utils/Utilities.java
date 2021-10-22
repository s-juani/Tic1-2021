package application.project.utils;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ExperienciaRepository;
import application.entities.ent.InteresEntity;
import application.entities.ent.TuristaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class Utilities {

    @Autowired
    private ExperienciaRepository experienciaRepository;

    public ArrayList<ExperienciaEntity> getSugerencias(TuristaEntity user){
        /**
         * Este es el famoso "algoritmo de sugerencias".
         */

        Collection<ExperienciaEntity> experiencias = experienciaRepository.findAll();

        Collection<InteresEntity> userInterests = user.getIntereses();

        //TODO terminar

        return new ArrayList<>(experiencias);

    }

}
