package application.entities;

import application.entities.ent.TuristaEntity;
import application.entities.ent.TuristaRepository;
//import application.entities.ent.ClientesEntity;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component
public class ClienteManager {

    @Autowired
    private TuristaRepository turistaRepository;

    public void addClient(String mail, String usuario, String nombre, LocalDate fechaNacimiento, String pw)
            throws UserAlreadyInUse, InvalidInformation {
        if(mail.equals("") || usuario.equals("") || nombre.equals("")||fechaNacimiento==null||pw.equals("")){
            throw new InvalidInformation();
        }
        try {
            System.out.println("Por crear cliente");
            TuristaEntity turista = new TuristaEntity(mail,usuario, nombre, fechaNacimiento,pw);
//            ClientesEntity cliente = new ClientesEntity(mail, usuario, nombre, fechaNacimiento, pw);
            System.out.println("Cliente creado");
            turistaRepository.save(turista);
            System.out.println("Cliente guardado");
        } catch(Exception e){
            throw new UserAlreadyInUse();
        }
    }
}