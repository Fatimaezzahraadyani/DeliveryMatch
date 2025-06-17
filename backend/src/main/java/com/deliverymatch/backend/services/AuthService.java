package com.deliverymatch.backend.services;

import com.deliverymatch.backend.dto.AuthRequest;
import com.deliverymatch.backend.dto.AuthResponse;
import com.deliverymatch.backend.repository.UserRepository;
import org.apache.coyote.Request;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    private final AuthenticationProvider authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(AuthenticationProvider authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(AuthRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()

                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtService.generateToken(new HashMap<>(), userDetails);

        }catch(AuthenticationException e){
            throw new RuntimeException(e);

        }
    }



}
