package application.entities;

import application.entities.ent.InteresEntity;
import application.entities.ent.InteresRepository;
import application.entities.exceptions.InteresAlreadyExists;
import application.entities.exceptions.InvalidInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InteresManager {

    @Autowired
    private InteresRepository interesRepository;

    public void addInteres(String nombre, InteresEntity esSubinteresDe) throws InteresAlreadyExists, InvalidInformation {
        if (nombre.equals("")) {
            throw new InvalidInformation();
        }
        try {
            InteresEntity interes = new InteresEntity(nombre, esSubinteresDe);
            interesRepository.save(interes);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
