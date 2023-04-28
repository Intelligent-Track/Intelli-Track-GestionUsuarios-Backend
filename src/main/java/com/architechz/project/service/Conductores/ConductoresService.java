package com.architechz.project.service.Conductores;

import java.util.List;

import com.architechz.project.models.Driver;
import com.architechz.project.payload.RegisterRequests.*;

public interface ConductoresService {
    
    public String addUser(ConductorRequest user);
    public Driver getByUsername(String username);
    public List<Driver> GetUser();
    public String delUser(String username);
    public String UpdateUser(Driver user);
    public String updateDriverFiles(byte[] license, byte[] mecReview, Long id);

}
