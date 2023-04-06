package com.architechz.project.service.Clientes;

import java.util.List;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.*;


public interface ClienteService {
    
    public abstract String addUser(ClienteRequest user);
    public abstract List<Client> GetUser();
    public abstract String delUser(String username);
}
