package com.architechz.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.project.models.RegisterRequest;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.RegisterRequests.RegisterRequestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reg")
public class RegisterControler {

    @Autowired
    RegisterRequestService registerRequestService;

    @PostMapping("/addRequest")
    public ResponseEntity<?> addRequest(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(new MessageResponse(registerRequestService.addRegisterRequest(registerRequest)));
    }

    @GetMapping("/allRequests")
    public List<RegisterRequest> getAllRegisterResquests() {
        return registerRequestService.getRegisterRequests();
    }

    @PostMapping("/manageRequest")
    public ResponseEntity<?> manageRegisterRequest(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(new MessageResponse(registerRequestService.manageRequest(registerRequest)));
    }

}
