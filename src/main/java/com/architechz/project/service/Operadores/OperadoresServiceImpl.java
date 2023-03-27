package com.architechz.project.service.Operadores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.architechz.project.dto.DtoLinkOperatorManager;
import com.architechz.project.dto.DtoOperator;
import com.architechz.project.models.Gerente;
import com.architechz.project.models.Operador;
import com.architechz.project.repository.GerenteRepository;
import com.architechz.project.repository.OperadorRepository;

public class OperadoresServiceImpl implements OperadoresService {

    @Autowired
    OperadorRepository operadorRepository;

    @Autowired
    GerenteRepository gerenteRepository;

    @Override
    public Operador getOperatorById(Long id) {
        return operadorRepository.getReferenceById(id);
    }

    @Override
    public List<Operador> getAllOperators(){
        return operadorRepository.findAll();
    }

    @Override
    public List<DtoOperator> getInfoOperators() {
        List<Operador> operators = this.getAllOperators();
        List<DtoOperator> infoOperators = new ArrayList<>();
        for(Operador operator : operators){
            infoOperators.add(new DtoOperator(operator.getId(), operator.getName(), null, null, null));
            // TO-DO Full data with relation of manager (that is not done already)
        }
        return infoOperators;
    }

    @Override
    public void createOperator(Operador operator){
        if(operadorRepository.findById(operator.getId()) == null){
            operadorRepository.save(operator);
        }
    }

    @Override
    public void deleteOperator(Long id) {
        operadorRepository.deleteById(id);
        // TO-DO Delete relations (that are not set by this time)
    }

    @Override
    public void linkOperatorManager(DtoLinkOperatorManager dtoLinkOperatorManager) {
        Operador operator = operadorRepository.getReferenceById(dtoLinkOperatorManager.getIdOperator());
        Gerente manager = gerenteRepository.getReferenceById(dtoLinkOperatorManager.getIdManager());
        // TO-DO Link operator and manager (insert into list) (relations are not set)
    }

    @Override
    public void unlinkOperatorManager(DtoLinkOperatorManager dtoLinkOperatorManager) {
        Operador operator = operadorRepository.getReferenceById(dtoLinkOperatorManager.getIdOperator());
        Gerente manager = gerenteRepository.getReferenceById(dtoLinkOperatorManager.getIdManager());
        // TO-DO Unlink operator and manager (remove from list) (relations are not set)
    }
    
}
