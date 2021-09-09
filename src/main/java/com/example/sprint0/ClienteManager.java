package com.example.sprint0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
@Service
public class ClienteManager {

        @Autowired
        private ClienteRepository clienteRepository;

        public void addClient(String mail, String usuario, String nombre, Date fechaNacimiento, String pw) {

            // Verifico si el cliente no existe


            ClientesEntity cliente = new ClientesEntity(mail, usuario, nombre, fechaNacimiento, pw);

            clienteRepository.save(cliente);

        }
}
