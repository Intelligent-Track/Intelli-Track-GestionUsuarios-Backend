package com.architechz.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.dto.DtoLinkOperatorManager;
import com.architechz.project.dto.DtoOperator;
import com.architechz.project.models.Operador;
import com.architechz.project.service.Operadores.OperadoresService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/opt")
public class OperadorControler {
    
    @Autowired
    OperadoresService operadoresService;

    @GetMapping("/operator/{id}")
    public Operador getOperator(@PathVariable("id") Long id){
        return operadoresService.getOperatorById(id);
    }

    @GetMapping("/allOperators")
    public List<Operador> getAllOperators() {
        return operadoresService.getAllOperators();
    }

    @GetMapping("/infoOperators")
    public List<DtoOperator> getInfoOperators() {
        return operadoresService.getInfoOperators();
    }

    @PostMapping("/operator")
    public void createOperator(@RequestBody Operador operator) {
        operadoresService.createOperator(operator);
    }

    @DeleteMapping("/operator/{id}")
    public void deleteOperator(@PathVariable("id") Long id){
        operadoresService.deleteOperator(id);
    }

    @PutMapping("/linkOperatorManager")
    public void linkOperatorManager(@RequestBody DtoLinkOperatorManager dtoLinkOperatorManager){
        operadoresService.linkOperatorManager(dtoLinkOperatorManager);
    }

    @PutMapping("/unlinkOperatorManager")
    public void unlinkOperatorManager(@RequestBody DtoLinkOperatorManager dtoLinkOperatorManager){
        operadoresService.unlinkOperatorManager(dtoLinkOperatorManager);
    }

}
