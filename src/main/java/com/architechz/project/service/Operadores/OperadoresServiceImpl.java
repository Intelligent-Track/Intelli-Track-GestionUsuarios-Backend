package com.architechz.project.service.Operadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architechz.project.payload.RegisterRequests.OperadorRequest;
import com.architechz.project.models.Operator;
import com.architechz.project.repository.*;
import com.architechz.project.service.AuthService.*;

import net.bytebuddy.utility.RandomString;

import com.architechz.project.payload.request.SignupRequest;

@Service
public class OperadoresServiceImpl implements OperadoresService {

    @Autowired
    OperadorRepository OperadorRepository;

    @Autowired
    AuthService AuthService;

    @Autowired
    UserRepository UserRepository;

    @Override
    public String addUser(OperadorRequest user) {

        if (OperadorRepository.existsByUsername(user.getUsername())) {
            return "Error: El correo " + user.getUsername() + " ya existe en nuestras bases de datos!";
        } else {

            Set<String> rol = new HashSet<String>();
            rol.add("ADMIN");

            try {

                Operator operador = new Operator(user.getName(), user.getUsername(), user.getDocument(),
                        user.getPhone(), "Operador", user.getLocation(), user.getManagerUsername());
                OperadorRepository.save(operador);

                ////////

                String token = RandomString.make(10);

                ///////

                SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), token, rol);
                AuthService.addUser(user2);

            } catch (Exception e) {
                return e.toString();
            }

            return "Operador guardado con exito";
        }
    }

    @Override
    public List<Operator> GetUser() {
        return OperadorRepository.findAll();
    }

    @Override
    public List<Operator> GetUsersByName(String name) {
        List<Operator> operators = OperadorRepository.findAll();
        List<Operator> searchedOperators = new ArrayList<>();
        for(Operator operator : operators) {
            if(operator.getName().toLowerCase().contains(name.toLowerCase())) {
                searchedOperators.add(operator);
            }
        }
        return searchedOperators;
    }

    @Transactional
    public String delUser(String username) {
        System.out.println(username);
        try {

            OperadorRepository.deleteByUsername(username);
            UserRepository.deleteByUsername(username);

        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }

        return "Operador " + username + " borrado con exito!";
    }

    @Override
    public String UpdateUser(Operator user) {

        try {

            Operator operador = OperadorRepository.findByUsername(user.getUsername());

            operador.setLocation(user.getLocation());
            operador.setName(user.getName());
            operador.setPhone(user.getPhone());
            operador.setManagerUsername(user.getManagerUsername());
            operador.setName(user.getName());
            OperadorRepository.save(operador);

        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }

        return "Usuario actualizado con exito!!";
    }

}
