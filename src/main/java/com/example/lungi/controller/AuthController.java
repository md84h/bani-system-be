package com.example.lungi.controller;

import com.example.lungi.model.User;
import com.example.lungi.payload.request.LoginRequest;
import com.example.lungi.payload.request.SignupRequest;
import com.example.lungi.payload.response.AuthMessage;
import com.example.lungi.payload.response.JwtResponse;
import com.example.lungi.repository.RoleRepository;
import com.example.lungi.repository.UserRepository;
import com.example.lungi.security.jwt.JwtUtils;
import com.example.lungi.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> ((GrantedAuthority) item).getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok().body(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getMobile(), roles));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new AuthMessage( "Username is already taken"));
        }
        if (userRepository.existsByMobile(signupRequest.getMobile())) {
            return ResponseEntity.badRequest().body(new AuthMessage("Mobile is already taken"));
        }
        User user = new User(signupRequest.getUsername(), signupRequest.getMobile(), passwordEncoder.encode(signupRequest.getPassword()));
        String role = signupRequest.getRole();

        if (role == null) {
            role = "user";
        }

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok().body(new AuthMessage("User registered successfully"));
    }
}
