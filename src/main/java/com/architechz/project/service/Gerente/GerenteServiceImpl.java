package com.architechz.project.service.Gerente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.architechz.project.repository.*;
import com.architechz.project.models.Manager;
import com.architechz.project.payload.RegisterRequests.GerenteRequest;
import com.architechz.project.repository.GerenteRepository;
import com.architechz.project.service.AuthService.*;

import net.bytebuddy.utility.RandomString;

import com.architechz.project.payload.request.SignupRequest;

@Service
public class GerenteServiceImpl implements GerenteService {


    @Autowired
    AuthService AuthService;

    @Autowired
    GerenteRepository GerenteRepository;

    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(GerenteRequest user) {
        if (GerenteRepository.existsByUsername(user.getUsername())) {
            return "Error: El correo "+ user.getUsername() + " ya existe en nuestras bases de datos!";
          }else{
        Set<String>rol = new HashSet<>();
        

        try {

            if(user.getGerenteGeneral()){
                rol.add("GGEN");
                Manager gerente = new Manager(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Gerente General", user.getLocation(), user.getGerenteGeneral(), user.getManagerUsername());
                GerenteRepository.save(gerente);
            }else{
                rol.add("GREG");
                Manager gerente = new Manager(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Gerente Regional", user.getLocation(), user.getGerenteGeneral(), user.getManagerUsername());
                GerenteRepository.save(gerente);
            }

            
            String token = RandomString.make(10);


            SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), token, rol );
            AuthService.addUser(user2);
    
            } catch (Exception e) {
                return e.toString(); 
        }
    
            return "Gerente guardado con exito";
    }

    }

    @Override
    public List<Manager> GetUser() {
        return GerenteRepository.findAll();
    }

    @Transactional
    public String delUser(String username) {
        System.out.println(username);
        try {
            
            GerenteRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }


        return "Gerente " + username +" borrado con exito!";
    }

    @Override
    public String UpdateUser(Manager user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateUser'");
    }

    }
