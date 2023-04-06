package com.architechz.project.service.Conductores;

import java.util.List;

import com.architechz.project.models.Driver;
import com.architechz.project.payload.RegisterRequests.*;

public interface ConductoresService {
    
    public abstract String addUser(ConductorRequest user);
    public abstract List<Driver> GetUser();
    public abstract String delUser(String username);
    public abstract String UpdateUser(Driver user);
}
