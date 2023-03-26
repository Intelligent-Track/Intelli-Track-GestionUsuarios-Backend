package com.architechz.project.dto;

public class DtoManager {

    private Long id;
    private String fullName;
    private String region;
    private int driversInCharge;
    private int operatorsInCharge;
    private String warehouseLocation;
    
    public DtoManager() {
    }

    public DtoManager(Long id, String fullName, String region, int driversInCharge, int operatorsInCharge,
            String warehouseLocation) {
        this.id = id;
        this.fullName = fullName;
        this.region = region;
        this.driversInCharge = driversInCharge;
        this.operatorsInCharge = operatorsInCharge;
        this.warehouseLocation = warehouseLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getDriversInCharge() {
        return driversInCharge;
    }

    public void setDriversInCharge(int driversInCharge) {
        this.driversInCharge = driversInCharge;
    }

    public int getOperatorsInCharge() {
        return operatorsInCharge;
    }

    public void setOperatorsInCharge(int operatorsInCharge) {
        this.operatorsInCharge = operatorsInCharge;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
    
}
