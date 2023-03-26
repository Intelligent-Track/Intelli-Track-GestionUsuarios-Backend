package com.architechz.project.service.Gerente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.dto.DtoManager;
import com.architechz.project.models.Gerente;
import com.architechz.project.repository.GerenteRepository;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    GerenteRepository gerenteRepository;

    @Override
    public List<Gerente> getAllManagers(){
        return gerenteRepository.findAll();
    }

    @Override
    public List<DtoManager> getInfoManagers(){
        List<Gerente> managers = this.getAllManagers();
        List<DtoManager> infoManagers = new ArrayList<>();
        for(Gerente manager : managers){
            DtoManager dtoManager = new DtoManager();
            dtoManager.setId(manager.getId());
            dtoManager.setFullName(manager.getName());
            infoManagers.add(dtoManager);
            // TO-DO Set all attributes
        }
        return infoManagers;
    }
    
}
