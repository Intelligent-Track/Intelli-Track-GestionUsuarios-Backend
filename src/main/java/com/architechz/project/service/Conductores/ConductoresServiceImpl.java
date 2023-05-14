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
import com.architechz.project.service.EmailNotifications.EmailService;

import net.bytebuddy.utility.RandomString;

import com.architechz.project.payload.request.SignupRequest;

@Service
public class ConductoresServiceImpl implements ConductoresService {

    @Autowired
    AuthService AuthService;

    @Autowired
    ConductorRepository ConductorRepository;

    @Autowired
    UserRepository UserRepository;

    @Autowired
    EmailService emailService;

    @Override
    public String addUser(ConductorRequest user) {
        if (ConductorRepository.existsByUsername(user.getUsername())) {
            return "Error: El correo " + user.getUsername() + " ya existe en nuestras bases de datos!";
        } else {
            Set<String> rol = new HashSet<>();

            try {

                if (user.isExternal()) {
                    rol.add("CON");
                } else {
                    rol.add("CONV");
                }

                Driver conductor = new Driver(user.getName(), user.getUsername(), user.getDocument(), user.getPhone(),
                        user.getJob(), user.getLocation(), user.getManagerUsername(), user.getVehiclePlate(),
                        user.getLicense(), user.getMecReview(), user.isExternal());
                ConductorRepository.save(conductor);

                String token = RandomString.make(10);

                String message = "Bienvenido conductor " + user.getName() + " a IntelliTrack!\n\n" +
                        "Por medio de este correo le enviamos la contraseña por la cual podrá acceder al sistema: "
                        + token;

                this.emailService.sendMessagge(user.getUsername(), "Te damos la bienvenida a IntelliTrack", message);

                SignupRequest user2 = new SignupRequest(user.getName(), user.getUsername(), token, rol);
                AuthService.addUser(user2);

            } catch (Exception e) {
                return e.toString();
            }

            return "Conductor guardado con exito";
        }
    }

    @Override
    public Driver getByUsername(String username) {
        return this.ConductorRepository.findByUsername(username);
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

        return "Conductor " + username + " borrado con exito!";
    }

    @Override
    public String UpdateUser(Driver user) {

        try {

            Driver driver = ConductorRepository.findByUsername(user.getUsername());

            driver.setLocation(user.getLocation());
            driver.setName(user.getName());
            driver.setPhone(user.getPhone());
            driver.setManagerUsername(user.getManagerUsername());
            driver.setName(user.getName());
            ConductorRepository.save(driver);

        } catch (Exception e) {
            return e.toString();// TODO: handle exception
        }

        return "Usuario actualizado con exito!!";
    }

    @Override
    public String updateDriverFiles(byte[] license, byte[] mecReview, Long id) {
        try {
            Driver driver = this.ConductorRepository.findById(id).orElseThrow();
            driver.setLicense(license);
            driver.setMecReview(mecReview);
            this.ConductorRepository.save(driver);
        } catch (Exception e) {
            return e.toString();
        }
        return "Driver files updated correctly";
    }

}