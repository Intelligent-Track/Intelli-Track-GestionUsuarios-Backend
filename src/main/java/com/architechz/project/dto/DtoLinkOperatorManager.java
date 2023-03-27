package com.architechz.project.dto;

public class DtoLinkOperatorManager {

    private Long idOperator;
    private Long idManager;
    
    public DtoLinkOperatorManager() {
    }

    public DtoLinkOperatorManager(Long idOperator, Long idManager) {
        this.idOperator = idOperator;
        this.idManager = idManager;
    }

    public Long getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(Long idOperator) {
        this.idOperator = idOperator;
    }

    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
    }
    
}
