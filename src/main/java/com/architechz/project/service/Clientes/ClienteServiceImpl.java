package com.architechz.project.service.Clientes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.payload.request.SignupRequest;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.repository.UserRepository;
import com.architechz.project.service.AuthService.AuthService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    AuthService AuthService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(ClienteRequest user) {
        if (this.clienteRepository.existsByUsername(user.getUsername())) {

            return "Error: El correo " + user.getUsername() + " ya existe en nuestras bases de datos!";
        } else {

            if (this.clienteRepository.existsByNit(user.getNit())) {

                return "Error: El Nit " + user.getNit() + " ya existe en nuestras bases de datos!";
            } else {

                Set<String> rol = new HashSet<>();

            if(!user.getAdm()){
                
                rol.add("CLIADM");
                Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Cliente ADM", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(), user.getManagerUsername());
                clienteRepository.save(cliente);
            }else{
                rol.add("CLI");
                Client cliente = new Client(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Cliente Representante", user.getLocation(), user.getNit(), user.getCompanyName(), user.getAdm(), user.getManagerUsername());
                clienteRepository.save(cliente);
            }
        }
    
            return "Bienvenido "+ user.getName() +" tu cuenta ha sido creada con el username: "+ user.getUsername();
    }


  /*  @Override
    public List<Client> getUser() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow();
    }*/ 

    /*@Override
    public String deleteUser(String username) {
        try {

            this.clienteRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);

        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }

        return "Cliente " + username + " borrado con exito!";
    }

   /*  @Override
    public String UpdateClient(Client user) {
        
        
        try {
            
            Client client = clienteRepository.findByUsername(user.getUsername());
            
            client.setLocation(user.getLocation());
            client.setName(user.getName());
            client.setPhone(user.getPhone());
            client.setManagerUsername(user.getManagerUsername());
            clienteRepository.save(client);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }
        
        return "Usuario actualizado con exito!!";

    }*/
     /*public String updateClient(Client client) {
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
     }*/

}
   
   /* @Override
    public List<Client> GetUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GetUser'");
    }*/

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

   /*  @Override
    public String UpdateClient(Client user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateClient'");
    }*/

    @Override
    public List<Client> getUser() {
        return clienteRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    /*@Override
    public String deleteUser(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }*/

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