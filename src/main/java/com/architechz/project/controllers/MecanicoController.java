package com.architechz.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.models.Mecanic;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Mecanicos.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mech")
public class MecanicoController {


    @Autowired
    MecanicosService mecanicosService;
     
    @PutMapping("/UpdateMecanic")
    public ResponseEntity<?> UpdtMecanico(@Valid @RequestBody Mecanic MecRequest) {
      return ResponseEntity.ok(new MessageResponse(mecanicosService.UpdateUser(MecRequest)));
    }


}
