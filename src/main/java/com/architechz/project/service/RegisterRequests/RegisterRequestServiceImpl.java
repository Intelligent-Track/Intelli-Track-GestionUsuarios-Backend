package com.architechz.project.service.RegisterRequests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.architechz.project.models.Client;
import com.architechz.project.models.RegisterRequest;
import com.architechz.project.models.User;
import com.architechz.project.payload.request.SignupRequest;
import com.architechz.project.repository.ClienteRepository;
import com.architechz.project.repository.RegisterRepository;
import com.architechz.project.repository.UserRepository;
import com.architechz.project.service.AuthService.AuthService;
import com.architechz.project.service.Clientes.ClienteService;
import com.architechz.project.service.EmailNotifications.EmailService;

@Service
public class RegisterRequestServiceImpl implements RegisterRequestService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthService authService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmailService emailService;

    @Override
    public String addRegisterRequest(RegisterRequest newUser) {
        try {
            if (registerRepository.existsByUsername(newUser.getUsername())) {
                return "Error: El correo " + newUser.getUsername() + " ya existe en nuestras bases de datos!";
            }

            newUser.setPassword(encoder.encode(newUser.getPassword()));

            registerRepository.save(newUser);
        } catch (Exception e) {
            return e.toString();
        }

        return "El usuario " + newUser
                + "estará en revisión y podrá ser utilizado según la confirmación recibida proximamente al correo";
    }

    @Override
    public List<RegisterRequest> getRegisterRequests() {
        return registerRepository.findAll();
    }

     @Override
    public String manageRequest(RegisterRequest registerRequest) {
     /*    try {
            if (registerRequest.getAccepted()) {
                authService.addUser(new SignupRequest(registerRequest.getName(), registerRequest.getUsername(),
                        registerRequest.getPassword(), null), true);
                User user = userRepository.findByUsername(registerRequest.getUsername()).orElseThrow();
                clienteService.addClient(new Client(user.getId(), registerRequest.getName(),
                        registerRequest.getUsername(), registerRequest.getDocument(), registerRequest.getPhone(),
                        registerRequest.getJob(), registerRequest.getLocation(), registerRequest.getNit(),
                        registerRequest.getCompanyName(), registerRequest.getAdm(),
                        registerRequest.getManagerUsername()));
                registerRepository.deleteByUsername(registerRequest.getUsername());

            } else {
                registerRepository.deleteByUsername(registerRequest.getUsername());
                emailService.sentMessagge(registerRequest.getUsername(),
                        "Lo sentimos, la solicitud de registro fue rechazada. \n \n Recomendamos verificar toda la información y volverlo a intentar");

            }
        } catch (Exception e) {
            return e.toString();
        }*/
        return "Funcion no funcional...";
    }

}
