package com.architechz.project.service.RegisterRequests;

import java.util.List;

import com.architechz.project.models.RegisterRequest;

public interface RegisterRequestService {

    public String addRegisterRequest(RegisterRequest newUser);

    public List<RegisterRequest> getRegisterRequests();

    public String manageRequest(RegisterRequest registerRequest);

}
