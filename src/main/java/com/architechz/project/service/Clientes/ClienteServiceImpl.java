package com.architechz.project.service.Clientes;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.repository.UserRepository;
import com.architechz.project.service.AuthService.AuthService;
import com.architechz.project.service.EmailNotifications.EmailService;

import net.bytebuddy.utility.RandomString;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    AuthService AuthService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UserRepository UserRepository;

    @Autowired
    EmailService email;

    @Override
    public ResponseEntity<?> addUser(ClienteRequest user) {
        if (this.clienteRepository.existsByUsername(user.getUsername())) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: El correo " + user.getUsername() + " ya existe en nuestras bases de datos!");
        } else {
            System.out.println(user.getNit());
            if (this.clienteRepository.existsByNit(user.getNit())) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: El Nit " + user.getNit() + " ya existe en nuestras bases de datos!");
            } else {
                String token = RandomString.make(5);
                Set<String> rol = new HashSet<>();
                System.out.println(token);
                if (!user.getAdm()) {   //is the opposite of the client

                    rol.add("CLIADM");
                    Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                            "Cliente ADM", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(),
                            user.getManagerUsername(),false,token,false);                           
                    clienteRepository.save(cliente);
                } else {
                    rol.add("CLI");
                    Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                            "Cliente Representante", user.getLocation(), user.getNit(), user.getCompanyName(),
                            user.getAdm(), user.getManagerUsername(),true,"none",true);
                    clienteRepository.save(cliente);
                }
                email.Verify(user.getUsername(), token);//email de verificacao
            }               
            return ResponseEntity.ok("Hola " + user.getName() + ", fue enviado un correo de verificacion al siguiente correo: " + user.getUsername());
        }
    }

    @Override
    public String delUser(String username) {
        try {

            this.clienteRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);

        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }

        return "Cliente " + username + " borrado con exito!";
    }

    @Override
    public List<Client> getUser() {
        return clienteRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    @Override
    public String updateClient(Client client) {
        try {
            Client clientFound = this.clienteRepository.findById(client.getId()).orElseThrow();
            clientFound.setAdm(client.getAdm());
            clientFound.setCompanyName(client.getCompanyName());
            clientFound.setDocument(client.getDocument());
            clientFound.setJob(client.getJob());
            clientFound.setLocation(client.getLocation());
            clientFound.setManagerUsername(client.getManagerUsername());
            clientFound.setName(client.getName());
            clientFound.setNit(client.getNit());
            clientFound.setPhone(client.getPhone());
            clientFound.setUsername(client.getUsername());
            this.clienteRepository.save(clientFound);
            return "Cliente actualizado correctamente";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public ResponseEntity<?> verifyClient(String code) {
        try {

            Client client = clienteRepository.findByCode(code);
            System.out.println(client.getUsername());
            System.out.println(client.getVerified());
            client.setVerified(true);
            clienteRepository.save(client);
            System.out.println(client.getVerified());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Código incorrecto, vuelvalo a intentar!");
    }
            return ResponseEntity.ok("Cliente verificado con exito");
            
    }
}