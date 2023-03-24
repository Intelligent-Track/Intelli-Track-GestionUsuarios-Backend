package com.architechz.proyect.service.AuthService;

import com.architechz.proyect.payload.request.SignupRequest;

public interface AuthService {
    
    public abstract String addUser(SignupRequest user);


}
