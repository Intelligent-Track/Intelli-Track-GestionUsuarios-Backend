package com.architechz.project.service.Operadores;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.architechz.project.models.Operator;
import com.architechz.project.payload.RegisterRequests.*;

public interface OperadoresService {
    
    public abstract String addUser(OperadorRequest user);
    public abstract List<Operator> GetUser();
    public abstract String delUser(String username);
    public abstract String UpdateUser(Operator user);

}

