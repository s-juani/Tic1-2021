package application.project.utils;

import application.entities.ent.ExperienciaEntity;
import application.entities.ent.ExperienciaRepository;
import application.entities.ent.InteresEntity;
import application.entities.ent.TuristaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Component
public class Utilities {

    @Autowired
    private ExperienciaRepository experienciaRepository;

    public ArrayList<ExperienciaEntity> getSugerencias(TuristaEntity user){
        /**
         * Este es el famoso "algoritmo de sugerencias".
         */


        Collection<InteresEntity> userInterests = user.getIntereses();

        Set<ExperienciaEntity> experiencias = new HashSet<>();

        for (InteresEntity interes: userInterests){
            experiencias.addAll(experienciaRepository.findByIntereses_Nombre(interes.getNombre()));
        }

        return new ArrayList<>(experiencias);

    }

    public ArrayList<ExperienciaEntity> getBusqueda(String busqueda) {
        return new ArrayList<>(experienciaRepository.findByNombreContainsIgnoreCaseOrIntereses_NombreContainsIgnoreCase(busqueda,busqueda));
    }

}
