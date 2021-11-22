package application.entities;

import application.entities.ent.*;
import application.entities.exceptions.InvalidInformation;
import application.entities.exceptions.UserAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class UsuarioManager {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private OperadorRepository operadorRepository;

    public void addTurist(String name, String user, String pw, String mail, LocalDate birth, PaisEntity country, Collection<InteresEntity> interest)
            throws UserAlreadyInUse, InvalidInformation{
        if (name.equals("") || user.equals("") || pw.equals("") || mail.equals("") || country == null){
            throw new InvalidInformation();
        }
        try{
            System.out.println("****BEGIN CREAR TURISTA****");
            System.out.println("*Crear entidad*");
            TuristaEntity turista = new TuristaEntity(mail,user,name,birth,pw);
            turista.setIntereses(interest);
            turista.setNacionalidad(country);
            System.out.println("*Entidad creada*");
            System.out.println("*Guardar en crud*");
            turistaRepository.save(turista);
            System.out.println("*Guardado con exito*");
        }
        catch (Exception e){
            e.printStackTrace();
            throw new UserAlreadyInUse();
        }
    }

    public void addOperator(String mail, String user, String pw, String contacto) throws UserAlreadyInUse {
        try{
            System.out.println("****BEGIN CREAR TURISTA****");
            System.out.println("*Crear entidad*");
            OperadorEntity operador = new OperadorEntity(mail, user, pw, contacto);
            System.out.println("*Entidad creada*");
            System.out.println("*Guardar en crud*");
            operadorRepository.save(operador);
            System.out.println("*Guardado con exito*");
        } catch (Exception e){
            throw new UserAlreadyInUse();
        }
    }
}
