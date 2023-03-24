package com.architechz.proyect.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.architechz.proyect.models.Erole;
import com.architechz.proyect.models.Role;
import com.architechz.proyect.models.User;
import com.architechz.proyect.payload.request.LoginRequest;
import com.architechz.proyect.payload.request.SignupRequest;
import com.architechz.proyect.payload.response.JwtResponse;
import com.architechz.proyect.payload.response.MessageResponse;
import com.architechz.proyect.repository.RoleRepository;
import com.architechz.proyect.repository.UserRepository;
import com.architechz.proyect.security.jwt.JwtUtils;
import com.architechz.proyect.security.services.UserDetailsImpl;
import com.architechz.proyect.service.AuthService.AuthService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class MecanicoController {
    
}
