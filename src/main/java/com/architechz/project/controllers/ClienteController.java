package com.architechz.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.architechz.project.models.Client;

import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.service.Clientes.ClienteService;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;

/*import com.architechz.project.models.Client;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Clientes.ClienteService;*/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cli")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;


    @PostMapping("/ClientCreate")
    public ResponseEntity<?> ClientSignup(@Valid @RequestBody ClienteRequest clientRequest) {
      return ResponseEntity.ok(new MessageResponse(clienteService.addUser(clientRequest)));
    }

   /*  @PutMapping("/UpdateClient")
    public ResponseEntity<?> UpdateClient(@Valid @RequestBody Client clientRequest) {
      return ResponseEntity.ok(new MessageResponse(clienteService.UpdateClient(clientRequest)));
    }*/


    @GetMapping("/searchClient/{id}")
    public Client searchClient(@PathVariable Long id) {
      return this.clienteService.findById(id);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<?> updateClient(@Valid @RequestBody Client clientRequest) {
      return ResponseEntity.ok(new MessageResponse(this.clienteService.updateClient(clientRequest)));
    }

    @GetMapping("/listAllClients")
    public List<Client> allClients() {
      return clienteRepository.findAll();
    }
}
    

