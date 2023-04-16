package com.architechz.project.service.Clientes;

import java.util.List;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;

public interface ClienteService {
    
    public String addUser(ClienteRequest user);
    public List<Client> getUser();
    public Client findById(Long id);
    public String deleteUser(String username);
    public String updateClient(Client client);
    
}
