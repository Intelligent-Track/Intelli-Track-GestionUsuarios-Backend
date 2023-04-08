package com.architechz.project.service.AuthService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.architechz.project.models.Erole;
import com.architechz.project.models.Role;
import com.architechz.project.security.jwt.JwtUtils;
import com.architechz.project.payload.request.SignupRequest;
import com.architechz.project.models.*;
import com.architechz.project.repository.*;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


@Override
public String addUser(SignupRequest userRequest) {
        
    /////////////////////////////////////////////////////
    System.out.println(userRequest.getName());
    System.out.println(userRequest.getPassword());
    if (userRepository.existsByUsername(userRequest.getUsername())) {
      return "Error: El correo "+ userRequest.getUsername() + " ya existe en nuestras bases de datos!";
    }

    // Create new user's account
    User user = new User(userRequest.getUsername(),            
               encoder.encode(userRequest.getPassword()),
               userRequest.getName());

    Set<String> strRoles = userRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(Erole.ROLE_CLIENTEADM)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
      /*Cliente cliente = new Cliente(userRequest.getUsername(),userRequest.getName());
      ClienteRepository.save(cliente); //agregar el distintivo de que es ADM*/
    } else {
      strRoles.forEach(role -> {
        switch (role) {

        case "ADMIN":
          Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;

          case "CLI":
          Role cli = roleRepository.findByName(Erole.ROLE_CLIENTEREPRE)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(cli);

          break;
        case "GGEN":
          Role modRole = roleRepository.findByName(Erole.ROLE_GERENTEGEN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);
          // Create new client's account
          /*Gerente Gerente = new Gerente(userRequest.getUsername(),userRequest.getName());
          GerenteRepository.save(Gerente);*/
          //llamada del servicio de correo
          break;

        case "GREG":
          Role GREG = roleRepository.findByName(Erole.ROLE_GERENTEREG)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(GREG);
          /*Gerente = new Gerente(userRequest.getUsername(),userRequest.getName()); //agregar el distintivo de que es regional
          GerenteRepository.save(Gerente);*/
          //llamada del servicio de correo
          break;

          case "CON":
          Role CON = roleRepository.findByName(Erole.ROLE_CONDUCTOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(CON);
          /*Conductor Conductor = new Conductor(userRequest.getUsername(),userRequest.getName());
          ConductorRepository.save(Conductor);*/
          //llamada del servicio de correo
          break;

          case "CONV":
          Role CONV = roleRepository.findByName(Erole.ROLE_CONDUCTORVIS)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(CONV);
          /*Conductor = new Conductor(userRequest.getUsername(),userRequest.getName());
          ConductorRepository.save(Conductor); //agregar el distintivo de que es invitado*/
          //llamada del servicio de correo
          break;

          case "MEC":
          Role MEC = roleRepository.findByName(Erole.ROLE_CONDUCTORVIS)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(MEC);
         /*  Mecanico Mecanico = new Mecanico(userRequest.getUsername(),userRequest.getName());
          MecanicoRepository.save(Mecanico);*/
          //llamada del servicio de correo
            ///////////////////////////////////////////////////////////////////////////////////////////////////////
          break;

          case "PART":
          Role PART = roleRepository.findByName(Erole.ROLE_CONDUCTORVIS)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(PART);
          //llamada del servicio de correo
          break;

        case "OPER":
          Role OPER = roleRepository.findByName(Erole.ROLE_OPERADOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(OPER);
          break;
          
          /*Operador Operador = new Operador(userRequest.getUsername(),userRequest.getName());
          OperadorRepository.save(Operador);*/
          //llamada del servicio de correo
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    return "Bienvenido, "+ userRequest.getName() +", fuiste registrado exisitosamente con el correo "+ userRequest.getUsername()+" lo cual usuaras para tu loggin!";

}  

}
