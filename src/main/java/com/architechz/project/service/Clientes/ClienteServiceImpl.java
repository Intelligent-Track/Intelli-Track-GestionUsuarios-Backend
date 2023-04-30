package com.architechz.project.service.Clientes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.repository.UserRepository;
import com.architechz.project.service.AuthService.AuthService;
import com.architechz.project.service.EmailNotifications.EmailService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    AuthService AuthService;

    @Autowired
    EmailService emailService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(ClienteRequest user) {
        String messagge;
        if (this.clienteRepository.existsByUsername(user.getUsername())) {

            return "Error: El correo " + user.getUsername() + " ya existe en nuestras bases de datos!";
        } else {

            if (this.clienteRepository.existsByNit(user.getNit())) {

                Set<String> rol = new HashSet<>();
                rol.add("CLI");
                Client cliente = new Client(
                    user.getName(), 
                    user.getUsername(), 
                    user.getDocument(), 
                    user.getPhone(),
                    "Cliente Representante", 
                    user.getLocation(), 
                    user.getNit(), 
                    user.getCompanyName(),
                    user.getAdm(), 
                    user.getManagerUsername()
                );

                clienteRepository.save(cliente);
                messagge= "Bienvenido, usted a sido registrado como un Cliente representante de la empresa" + user.getCompanyName();
                emailService.sentMessagge(user.getUsername(),messagge);
    
                    return "El usuario con correo " + user.getUsername() + " fue añadido como Cliente Representante exitosamente!";
            } else {

                Set<String> rol = new HashSet<>();

                if (!user.getAdm()) {

                    rol.add("CLIADM");
                    Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                            "Cliente ADM", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(),
                            user.getManagerUsername());
                    clienteRepository.save(cliente);
                } else {
                    rol.add("CLI");
                    Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                            "Cliente Representante", user.getLocation(), user.getNit(), user.getCompanyName(),
                            user.getAdm(), user.getManagerUsername());
                    clienteRepository.save(cliente);
                }
            }

            return "Bienvenido " + user.getName() + " tu cuenta ha sido creada con el username: " + user.getUsername();
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
}