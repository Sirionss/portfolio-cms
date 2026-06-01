package com.portfolio.portfolio_cms.controller;

import com.portfolio.portfolio_cms.dto.AuthResponse;
import com.portfolio.portfolio_cms.dto.LoginRequest;
import com.portfolio.portfolio_cms.dto.RegisterRequest;
import com.portfolio.portfolio_cms.model.Role;
import com.portfolio.portfolio_cms.model.User;
import com.portfolio.portfolio_cms.repository.UserRepository;
import com.portfolio.portfolio_cms.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        if (userRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().build();
        }
    }
}
