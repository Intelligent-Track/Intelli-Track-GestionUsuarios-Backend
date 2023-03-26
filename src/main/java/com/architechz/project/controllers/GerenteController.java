package com.architechz.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.dto.DtoManager;
import com.architechz.project.models.Gerente;
import com.architechz.project.service.Gerente.GerenteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/man")
public class GerenteController {

    @Autowired
    GerenteService gerenteService;

    @GetMapping("/allManagers")
    public List<Gerente> getAllManagers(){
        return gerenteService.getAllManagers();
    }

    @GetMapping("/infoManagers")
    public List<DtoManager> getInfoManagers(){
        return gerenteService.getInfoManagers();
    }
    
}
