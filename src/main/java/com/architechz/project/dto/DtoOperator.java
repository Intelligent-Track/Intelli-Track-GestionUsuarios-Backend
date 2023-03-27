package com.architechz.project.dto;

public class DtoOperator {

    private Long id;
    private String name;
    private String email;
    private String managerName;
    private Long managerId;
    
    public DtoOperator() {
    }

    public DtoOperator(Long id, String name, String email, String managerName, Long managerId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.managerName = managerName;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
    
}
