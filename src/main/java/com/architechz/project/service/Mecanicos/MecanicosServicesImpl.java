package com.architechz.project.service.Mecanicos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.payload.RegisterRequests.MecanicoRequest;
import com.architechz.project.models.Mecanic;
import com.architechz.project.service.AuthService.*;

import net.bytebuddy.utility.RandomString;

import com.architechz.project.payload.request.SignupRequest;

import com.architechz.project.repository.*;

@Service
public class MecanicosServicesImpl implements MecanicosService {

    @Autowired
    AuthService AuthService;

    @Autowired
    MecanicoRepository MecanicoRepository;

    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(MecanicoRequest user) {
        if (MecanicoRepository.existsByUsername(user.getUsername())) {
            return "Error: El correo "+ user.getUsername() + " ya existe en nuestras bases de datos!";
          }else{
        Set<String>rol = new HashSet<>();
        rol.add("MEC");
        try {

            Mecanic mecanico = new Mecanic(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(), "Mecanico", user.getLocation(), user.getManagerUsername());
            MecanicoRepository.save(mecanico);
    

            String token = RandomString.make(10);


            SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), token, rol );
            AuthService.addUser(user2);
    
            } catch (Exception e) {
                return e.toString(); 
        }
    
            return "Mecanico guardado con exito";
    }
    }

    @Override
    public List<Mecanic> GetUser() {
       return MecanicoRepository.findAll();
    }

    @Transactional
    public String delUser(String username) {
        System.out.println(username);
        try {
            
            MecanicoRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }


        return "Mecanico " + username +" borrado con exito!";
    }

    @Override
    public String UpdateUser(Mecanic user) {
        
        try {
            
            Mecanic Mecanico = MecanicoRepository.findByUsername(user.getUsername());
            
            Mecanico.setLocation(user.getLocation());
            Mecanico.setName(user.getName());
            Mecanico.setPhone(user.getPhone());
            Mecanico.setManagerUsername(user.getManagerUsername());
            Mecanico.setName(user.getName());
            MecanicoRepository.save(Mecanico);


        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }
        
        return "Usuario actualizado con exito!!";
    }

}
