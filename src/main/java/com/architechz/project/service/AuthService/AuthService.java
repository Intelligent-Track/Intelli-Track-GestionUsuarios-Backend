package com.architechz.project.service.AuthService;

import com.architechz.project.payload.request.SignupRequest;

public interface AuthService {

    public abstract String addUser(SignupRequest user);

}
