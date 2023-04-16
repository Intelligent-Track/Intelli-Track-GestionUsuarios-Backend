package com.architechz.project.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.models.Manager;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Gerente.GerenteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/man")
public class GerenteController {

    @Autowired
    GerenteService GerenteService;

    @PutMapping("/UpdateManager")
    public ResponseEntity<?> UpdtConductor(@Valid @RequestBody Manager operadorRequest) {
      return ResponseEntity.ok(new MessageResponse(GerenteService.UpdateUser(operadorRequest)));
    }
}
