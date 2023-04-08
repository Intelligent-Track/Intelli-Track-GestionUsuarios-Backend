package com.architechz.project.service.Mecanicos;

import java.util.List;

import com.architechz.project.models.*;
import com.architechz.project.payload.RegisterRequests.*;

public interface MecanicosService {
    
    public abstract String addUser(MecanicoRequest user);
    public abstract List<Mecanic> GetUser();
    public abstract String delUser(String username);
    public abstract String UpdateUser(Mecanic user);
}
