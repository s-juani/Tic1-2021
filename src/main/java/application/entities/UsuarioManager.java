package application.entities;

import application.entities.ent.OperadorRepository;
import application.entities.ent.TuristaRepository;
import application.entities.ent.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioManager {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TuristaRepository turistaRepository;


}
