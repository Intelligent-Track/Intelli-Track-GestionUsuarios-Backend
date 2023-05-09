package com.architechz.project.service.EmailNotifications;

//import java.io.File;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.architechz.project.payload.Emails.NewUser;


@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String mallFrom;

    @Override
    public ResponseEntity<?> newUser(NewUser NewuserNotification) {

        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setTo(NewuserNotification.getUsername());
           // String url=" http://localhost:4200"; //add login page https://10.43.101.95/login
            String url="https://10.43.101.95/api/auth/login";
            mimeMessageHelper.setText(
                "Hola "+ NewuserNotification.getName() + " bienvenido a Architechz es un gusto contar contigo, \n a seguir se le brindara las credenciales para entrar a la aplicacion en el enlace " 
                + url + " : \n Username: "+ NewuserNotification.getUsername() + "\n Contrasena: "+ NewuserNotification.getPassword());
                mimeMessageHelper.setSubject("Architechz Nuevo usuario");
                //javaMailSender.send(mimeMessage);
           return ResponseEntity.ok("Correo enviado con exito al usuario "+ NewuserNotification.getUsername());     

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Fue imposible mandar el correo en este momento");

        }     
    }


    @Override
    public ResponseEntity<?> ForgotPassword(String username, String token) {
        
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            System.out.println("llegue "+ username);
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setTo(username);
            
           // String url= "http://localhost:4200/change-password/"+token; //add login page https://10.43.101.95/login
            String url= "https://10.43.101.95/api/auth/change-password/"+token;
            mimeMessageHelper.setText(
                "Hola, \n en el siguiente enlace podras actualizar tu contrasena: " 
                + url) ;
                mimeMessageHelper.setSubject("Architechz cambio de contrasena");
                javaMailSender.send(mimeMessage);
           return ResponseEntity.ok("Correo para actualizar contrasena enviado con exito al usuario "+ username);     

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Fue imposible mandar el correo en este momento");

        }     
        
    }
    
    @Override
    public ResponseEntity<?> sentMessagge(String username, String messagge) {
        
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            System.out.println("llegue "+ username);
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setTo(username);
            
            mimeMessageHelper.setText(messagge);
            javaMailSender.send(mimeMessage);
            return ResponseEntity.ok("Mensaje enviado por correo con exito al usuario con correo: "+ username);     

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "Fue imposible mandar el correo en este momento");

        }     
        
    } 
}
