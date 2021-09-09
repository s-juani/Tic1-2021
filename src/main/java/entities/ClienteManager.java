package entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteManager {

        @Autowired
        private ClienteRepository clienteRepository;

        public void addClient(String mail, String usuario, String nombre, LocalDate fechaNacimiento, String pw) {

            // Verifico si el cliente no existe

            // Hace falta convertir fechaNacimiento de LocalDate a sql.Date

            ClientesEntity cliente = new ClientesEntity(mail, usuario, nombre, fechaNacimiento, pw);

            clienteRepository.save(cliente);

        }
}
