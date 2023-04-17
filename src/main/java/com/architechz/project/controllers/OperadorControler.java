package com.architechz.project.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.architechz.project.service.Operadores.OperadoresService;
import com.architechz.project.models.Operator;
import com.architechz.project.payload.response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/opt")
public class OperadorControler {

    @Autowired
    OperadoresService OperadoresService;

    @PutMapping("/UpdateOperator")
    public ResponseEntity<?> UpdtOperador(@Valid @RequestBody Operator operadorRequest) {
      return ResponseEntity.ok(new MessageResponse(OperadoresService.UpdateUser(operadorRequest)));
    }

}
