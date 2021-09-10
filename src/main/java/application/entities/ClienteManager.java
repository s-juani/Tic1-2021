package application.entities;

import application.entities.ent.ClienteRepository;
import application.entities.ent.ClientesEntity;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteManager {

        @Autowired
        private ClienteRepository clienteRepository;

        public void addClient(String mail, String usuario, String nombre, LocalDate fechaNacimiento, String pw)
                throws UserAlreadyInUse, InvalidInformation {
            if(mail.equals("") || usuario.equals("") || nombre.equals("")||fechaNacimiento==null||pw.equals("")){
                throw new InvalidInformation();
            }
            try {
               ClientesEntity cliente = new ClientesEntity(mail, usuario, nombre, fechaNacimiento, pw);
               clienteRepository.save(cliente);
            } catch(Exception e){
               throw new UserAlreadyInUse();
            }
        }
}