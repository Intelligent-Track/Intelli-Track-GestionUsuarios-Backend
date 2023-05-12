package com.architechz.project.service.Clientes;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.payload.request.LoginRequest;
import com.architechz.project.payload.InsertionRequests.Approve;

public interface ClienteService {

    public abstract ResponseEntity<?> addUser(ClienteRequest user);

    public String addClient(Client client);

    public abstract String delUser(String username);

    public abstract String findById(Long id);

    public List<Client> getUser();

    public Client findByUsername(String username);

    public String updateClient(Client client);

    public List<Client> GetClientsApprove();

    public ResponseEntity<?> verifyClient(String code,String username);

    public String verifyPet(LoginRequest loginRequest);

    public ResponseEntity<?> AprroveCli(Approve code);

}
