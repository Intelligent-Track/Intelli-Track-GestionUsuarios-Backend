package com.architechz.project.service.Clientes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.payload.RegisterRequests.ClienteRequest;

import com.architechz.project.models.Client;

import com.architechz.project.service.AuthService.*;
import com.architechz.project.payload.request.SignupRequest;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.repository.UserRepository;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    AuthService AuthService;

    @Autowired
    ClienteRepository ClienteRepository;

    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(ClienteRequest user) {
        if (ClienteRepository.existsByUsername(user.getUsername())) {

            return "Error: El correo "+ user.getUsername() + " ya existe en nuestras bases de datos!";
          }
          else{

            if (ClienteRepository.existsByNit(user.getNit())) {

                return "Error: El Nit "+ user.getNit() + " ya existe en nuestras bases de datos!";
              }
              else{

        Set<String>rol = new HashSet<>();
        
        try {

            if(!user.getAdm()){
                
                rol.add("CLIADM");
                Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Cliente ADM", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(), user.getManagerUsername());
                ClienteRepository.save(cliente);
            }else{
                rol.add("CLI");
                Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Cliente Representante", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(), user.getManagerUsername());
                ClienteRepository.save(cliente);
            }
            

            SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), user.getPassword(), rol );
            AuthService.addUser(user2);
    
            } catch (Exception e) {
                return e.toString(); 
        }
    
            return "Bienvenido "+ user.getName() +" tu cuenta ha sido creada con el username: "+ user.getUsername();
    }
    }
}

    @Override
    public List<Client> GetUser() {
        return ClienteRepository.findAll();
    }

    @Transactional
    public String delUser(String username) {
        try {
            
            ClienteRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }


        return "Cliente " + username +" borrado con exito!";
    }

    @Override
    public String UpdateClient(Client user) {
        
        
        try {
            
            Client client = ClienteRepository.findByUsername(user.getUsername());
            
            client.setLocation(user.getLocation());
            client.setName(user.getName());
            client.setPhone(user.getPhone());
            client.setManagerUsername(user.getManagerUsername());
            client.setName(user.getName());
            ClienteRepository.save(client);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }
        
        return "Usuario actualizado con exito!!";

    }
}
