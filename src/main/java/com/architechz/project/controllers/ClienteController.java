package com.architechz.project.controllers;

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
import com.architechz.project.service.Clientes.ClienteService;
import com.architechz.project.payload.RegisterRequests.ClienteRequest;
import com.architechz.project.payload.RegisterRequests.ClientVerify;

/*import com.architechz.project.models.Client;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Clientes.ClienteService;*/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cli")
public class ClienteController {

  @Autowired
  ClienteService clienteService;

  @PostMapping("/ClientCreate")
  public ResponseEntity<?> ClientSignup(@Valid @RequestBody ClienteRequest clientRequest) {
    return ResponseEntity.ok((clienteService.addUser(clientRequest)));
  }

  /*   @PostMapping("/ClientCreate")
    public ResponseEntity<?> ClientSignup(@Valid @RequestBody ClienteRequest clientRequest) {
      System.out.println("fuuuuuuck");
      return clienteService.addUser(clientRequest);
    }*/

  @GetMapping("/searchClient/{username}")
  public Client searchClient(@PathVariable String username) {
    return this.clienteService.findByUsername(username);
  }

  /*@PutMapping("/updateClient")
  public ResponseEntity<?> updateClient(@Valid @RequestBody Client clientRequest) {
    return ResponseEntity.ok(new MessageResponse(this.clienteService.updateClient(clientRequest)));
  }*/

    /*@GetMapping("/searchClient/{id}")
    public Client searchClient(@PathVariable Long id) {
      return clienteService.findById(id);
    }*/

    @PutMapping("/updateClient")
    public ResponseEntity<?> updateClient(@Valid @RequestBody Client clientRequest) {
      return ResponseEntity.ok(new MessageResponse(clienteService.updateClient(clientRequest)));
    }

    @PostMapping("/verifyUser")
    public ResponseEntity<?> VerifClient(@Valid @RequestBody ClientVerify clientRequest) {
      System.out.println(clientRequest.getCode());
      return clienteService.verifyClient(clientRequest.getCode());
    }
    
}
