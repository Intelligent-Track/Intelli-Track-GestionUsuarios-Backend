package com.architechz.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.models.Client;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Clientes.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cli")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PutMapping("/updateClient")
    public ResponseEntity<?> UpdateClient(@Valid @RequestBody Client clientRequest) {
      return ResponseEntity.ok(new MessageResponse(this.clienteService.updateClient(clientRequest)));
    }
    
}
