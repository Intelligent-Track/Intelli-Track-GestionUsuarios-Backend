package com.architechz.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.models.Driver;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Conductores.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dri")
public class ConductorController {

    @Autowired
    ConductoresService ConductoresService;

    @PutMapping("/UpdateDriver")
    public ResponseEntity<?> UpdtGerente(@Valid @RequestBody Driver operadorRequest) {
      return ResponseEntity.ok(new MessageResponse(ConductoresService.UpdateUser(operadorRequest)));
    }


}
