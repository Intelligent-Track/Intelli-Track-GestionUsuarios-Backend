package com.architechz.project.service.EmailNotifications;

import org.springframework.http.ResponseEntity;

import com.architechz.project.payload.Emails.NewUser;
import com.architechz.project.payload.Password.PasswordChange;

public interface EmailService {
    
    public ResponseEntity<?> newUser(NewUser NewuserNotification);
    public ResponseEntity<?> ForgotPassword(String Username, String token);
    
}
