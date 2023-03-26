package com.architechz.project.service.Gerente;

import java.util.List;

import com.architechz.project.dto.DtoManager;
import com.architechz.project.models.Gerente;

public interface GerenteService {

    public List<Gerente> getAllManagers();
    public List<DtoManager> getInfoManagers();
    
}
