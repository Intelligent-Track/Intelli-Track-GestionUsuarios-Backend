package com.architechz.project.service.ResetPassword;

import org.springframework.http.ResponseEntity;

import com.architechz.project.payload.Password.PasswordChange;

public interface ResetPasswordService {
    
    public ResponseEntity<?> PasswordUser(PasswordChange Password); 
    public ResponseEntity<?> ResetUser(PasswordChange Password); 

    
}
