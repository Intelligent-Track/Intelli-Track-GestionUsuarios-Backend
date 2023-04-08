package com.architechz.project.service.Conductores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.payload.RegisterRequests.ConductorRequest;

import com.architechz.project.repository.*;
import com.architechz.project.models.Driver;
import com.architechz.project.service.AuthService.*;
import com.architechz.project.payload.request.SignupRequest;

@Service
public class ConductoresServiceImpl implements ConductoresService {

    @Autowired
    AuthService AuthService;

    @Autowired
    ConductorRepository ConductorRepository;
    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(ConductorRequest user) {
        if (ConductorRepository.existsByUsername(user.getUsername())) {
            return "Error: El correo "+ user.getUsername() + " ya existe en nuestras bases de datos!";
          }else{
        Set<String>rol = new HashSet<>();
        

        try {

            if(user.getDirecto()){
                rol.add("CON");
                Driver conductor = new Driver(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Conductor Directo", user.getLocation(), user.getDirecto(), user.getManagerUsername(), user.getPlacaVehiculo(), user.getLicencia(), user.getRevisionAutoMec());
                ConductorRepository.save(conductor);
            }else{
                rol.add("CONV");
                Driver conductor = new Driver(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Conductor Externo", user.getLocation(), user.getDirecto(), user.getManagerUsername(), user.getPlacaVehiculo(), user.getLicencia(), user.getRevisionAutoMec());
                ConductorRepository.save(conductor);
            }

            SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), user.getPassword(), rol );
            AuthService.addUser(user2);
    
            } catch (Exception e) {
                return e.toString(); 
        }
    
            return "Conductor guardado con exito";
    }
    }

    @Override
    public List<Driver> GetUser() {
        return ConductorRepository.findAll();
    }

    @Transactional
    public String delUser(String username) {
        System.out.println(username);
        try {
            
            ConductorRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }


        return "Conductor " + username +" borrado con exito!";
    }

    @Override
    public String UpdateUser(Driver user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateUser'");
    }
}
