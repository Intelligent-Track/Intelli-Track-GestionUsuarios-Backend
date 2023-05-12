package com.architechz.project.service.Clientes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.architechz.project.models.Client;
import com.architechz.project.payload.InsertionRequests.Approve;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.repository.UserRepository;
import com.architechz.project.service.AuthService.AuthService;
import com.architechz.project.service.EmailNotifications.EmailService;
import com.architechz.project.payload.request.LoginRequest;
import com.architechz.project.payload.request.SignupRequest;

import net.bytebuddy.utility.RandomString;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    AuthService AuthService;

    @Autowired
    EmailService emailService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AuthService authservice;

    @Autowired
    UserRepository UserRepository;

    @Autowired
    EmailService email;

    @Override
    public ResponseEntity<?> addUser(ClienteRequest user) {

        if (this.clienteRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El correo " + user.getUsername() + " ya existe en nuestras bases de datos!");
        } else {
            System.out.println(user.getNit());
            if (this.clienteRepository.existsByNit(user.getNit())) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El Nit " + user.getNit() + " ya existe en nuestras bases de datos!");
            } else {
                String token = RandomString.make(5);
                Set<String> rol = new HashSet<>();
                System.out.println(token);
                if (!user.getAdm()) { // is the opposite of the client

                    rol.add("CLIADM");
                    Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                            "Cliente ADM", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(),
                            user.getManagerUsername(), false, token, false);
                    clienteRepository.save(cliente);
                } else {
                    rol.add("CLI");
                    Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                            "Cliente Representante", user.getLocation(), user.getNit(), user.getCompanyName(),
                            user.getAdm(), user.getManagerUsername(), true, "none", true);
                    clienteRepository.save(cliente);
                }
                email.Verify(user.getUsername(), token);// email de verificacao
                SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), user.getPassword(), rol);
                AuthService.addUser(user2);
            }
            return ResponseEntity.ok("Hola " + user.getName()
                    + ", fue enviado un correo de verificacion al siguiente correo: " + user.getUsername());
        }
    }

    @Override
    public String addClient(Client client) {
        String messagge;
        if (this.clienteRepository.existsByUsername(client.getUsername())) {

            return "Error: El correo " + client.getUsername() + " ya existe en nuestras bases de datos!";
        } else {
            System.out.println(client.getId());
            clienteRepository.save(client);
            messagge = "Bienvenido, usted a sido registrado como un Cliente representante de la empresa"
                    + client.getCompanyName();
            emailService.sentMessagge(client.getUsername(), messagge);

            return "El usuario con correo " + client.getUsername()
                    + " fue añadido como Cliente Representante exitosamente!";

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
    public Client findByUsername(String username) {
        return clienteRepository.findByUsername(username);
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
    public ResponseEntity<?> verifyClient(String code, String username) {
        try {

            Client client = clienteRepository.findByCode(code);
            client.setVerified(true);
            client.setCode("verified");
            clienteRepository.save(client);
            System.out.println("email enviado a adm");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código incorrecto, vuelvalo a intentar!");
        }

        return ResponseEntity.ok("Cliente verificado con exito");
    }

    @Override
    public String verifyPet(LoginRequest loginRequest) {

        try {

            if (clienteRepository.existsByUsername(loginRequest.getUsername())
                    && !clienteRepository.findByUsername(loginRequest.getUsername()).getVerified()) {

                // add a retry method to get a new email.
                System.out.println("Usuario aun no verificado, verifique el correo primero....");
                return "1";

            } else {

                if (!clienteRepository.findByUsername(loginRequest.getUsername()).getApproved()) {
                    System.out.println("Usuario aun no aprobado, una vez aprobado recibiras un correo confirmando....");
                    return "2";

                }
            }

        } catch (Exception e) {
            return "3";
        }

        return "3";
    }

    @Override
    public ResponseEntity<?> aproveClient(Approve code) {
        try {
            if (code.getResult() == "true") {
                Client client = clienteRepository.findByUsername(code.getUsername());
                client.setApproved(true);
                clienteRepository.save(client);
                return ResponseEntity.ok("Usuario Aprobado!");
            } else {
                clienteRepository.deleteByUsername(code.getUsername());
                UserRepository.deleteByUsername(code.getUsername());
                return ResponseEntity.ok("Usuario no aprobado y eliminado de las bases de datos!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No fue posible aceptar el usuario");
        }
    }

    @Override
    public String findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Client> GetClientsApprove() {
        return clienteRepository.findByApproved(false);
    }
}
