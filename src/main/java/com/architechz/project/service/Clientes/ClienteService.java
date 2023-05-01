package com.architechz.project.service.Clientes;

import java.util.List;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;

public interface ClienteService {

    public abstract String addUser(ClienteRequest user);

    public String addClient(Client client);

    public abstract String delUser(String username);

    public List<Client> getUser();

    public Client findByUsername(String username);

    public String updateClient(Client client);

}
