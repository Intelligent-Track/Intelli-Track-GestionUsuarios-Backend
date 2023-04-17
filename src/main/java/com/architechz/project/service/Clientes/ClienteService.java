package com.architechz.project.service.Clientes;

import java.util.List;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;

public interface ClienteService {
    
    public abstract String addUser(ClienteRequest user);
    //public abstract List<Client> GetUser();
    public abstract String delUser(String username);
    //public abstract String UpdateClient(Client user);
    //public String addUser(ClienteRequest user);
    public List<Client> getUser();
    public Client findById(Long id);
    //public String deleteUser(String username);
    public String updateClient(Client client);
    
}
