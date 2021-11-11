package application.entities;

import application.entities.ent.*;
import application.entities.exceptions.ExperienceAlreadyInUse;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public class ExperienciaManager {

    @Autowired
    private ExperienciaRepository xpRep;

    public void addExperiencia(ExperienciaEntity experiencia,String nombre, String direccion, String descripcion, Integer aforo,
                               boolean conReserva, boolean vacunacion, Set<InteresEntity> intereses,
                               Set<ImagenEntity> imagenes) throws ExperienceAlreadyInUse, InvalidInformation {
        if(nombre.equals("") || direccion.equals("") ||descripcion.equals("")){
            throw new InvalidInformation();
        }
        try {
            System.out.println("****BEGIN CREAR EXPERIENCIA****");
            System.out.println("*Crear entidad*");
            experiencia.setDatos(nombre,direccion,descripcion,aforo,conReserva,vacunacion);
            experiencia.setIntereses(intereses);
            experiencia.setImagens(imagenes);
            System.out.println("*Entidad creada*");
            System.out.println("*Guardar en crud*");
            xpRep.save(experiencia);
            System.out.println("*Guardado con exito*");
        } catch(Exception e){
            e.printStackTrace();
            throw new ExperienceAlreadyInUse();
        }
    }
}
