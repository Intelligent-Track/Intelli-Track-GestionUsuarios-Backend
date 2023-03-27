package com.architechz.project.service.Operadores;

import java.util.List;

import com.architechz.project.dto.DtoLinkOperatorManager;
import com.architechz.project.dto.DtoOperator;
import com.architechz.project.models.Operador;

public interface OperadoresService {

    public Operador getOperatorById(Long id);
    public List<Operador> getAllOperators();
    public List<DtoOperator> getInfoOperators();
    public void createOperator(Operador operator);
    public void deleteOperator(Long id);
    public void linkOperatorManager(DtoLinkOperatorManager dtoLinkOperatorManager);
    public void unlinkOperatorManager(DtoLinkOperatorManager dtoLinkOperatorManager);
    
}
