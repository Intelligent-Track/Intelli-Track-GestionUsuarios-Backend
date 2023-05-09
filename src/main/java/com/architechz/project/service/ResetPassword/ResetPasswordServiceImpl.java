package com.architechz.project.service.ResetPassword;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.architechz.project.payload.Password.PasswordChange;
import com.architechz.project.repository.PasswordRepository;
import com.architechz.project.repository.UserRepository;
import com.architechz.project.service.EmailNotifications.EmailService;

import net.bytebuddy.utility.RandomString;
import com.architechz.project.models.PasswordRequests;
import com.architechz.project.models.User;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailservice;

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> PasswordUser(PasswordChange Password) {
        
        try {

            if(userRepository.existsByUsername(Password.getUsername())){

                String token = RandomString.make(10);
                PasswordRequests passwordRequests = new PasswordRequests(Password.getUsername(), token);
                passwordRepository.save(passwordRequests);
                emailservice.ForgotPassword(Password.getUsername(),token);
                
                return ResponseEntity.ok("Correo enviado con exito al usuario "+ Password.getUsername());
            }else{

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body( "El usuario no existe en nuestras bases de datos");

            }

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Fue imposible mandar el correo en este momento");

        }
        
    }

    @Override
    @Transactional
    public ResponseEntity<?> ResetUser(PasswordChange Password,String token) {
        
        try {
                       
            PasswordRequests passwordRequests = passwordRepository.findByToken(token);
            Optional<User> user = userRepository.findByUsername(passwordRequests.getUsername());
            if (user.isPresent()) {
                User existingUser = user.get();
                existingUser.setPassword(encoder.encode(Password.getPassword()));
                userRepository.save(existingUser);
                System.out.println(Password.getPassword());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Fue imposible cambiar la contrasena en este momento");// handle the case where the user does not exist
            }

        } catch (Exception e) {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Se puteo");
        }
        
       
        return ResponseEntity.ok( "Cambio exitoso de contrasena");
    }
    




    
}
