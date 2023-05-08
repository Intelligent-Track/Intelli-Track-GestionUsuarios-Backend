package com.architechz.project.service.Clientes;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.architechz.project.models.Client;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.payload.request.LoginRequest;
import com.architechz.project.payload.InsertionRequests.Approve;

public interface ClienteService {

    public abstract ResponseEntity<?> addUser(ClienteRequest user);

    public abstract String delUser(String username);

    public List<Client> getUser();

    public Client findById(Long id);

    public String updateClient(Client client);

    public ResponseEntity<?> verifyClient(String code);

    public String verifyPet(LoginRequest loginRequest);

    public ResponseEntity<?> AprroveCli(Approve code);

}
