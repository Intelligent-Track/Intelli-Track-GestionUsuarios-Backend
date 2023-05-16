package com.architechz.project.service.Gerente;


import java.util.List;

import com.architechz.project.models.Manager;
import com.architechz.project.payload.RegisterRequests.*;

public interface GerenteService {

    public abstract String addUser(GerenteRequest user);
    public abstract List<Manager> GetUser();
    public List<Manager> GetUsersByName(String name);
    public abstract String delUser(String username);
    public abstract String UpdateUser(Manager user);
}
