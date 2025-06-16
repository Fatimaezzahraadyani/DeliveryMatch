package com.deliverymatch.backend.services;


import com.deliverymatch.backend.dto.RegisterDTO;
import com.deliverymatch.backend.model.User;
import com.deliverymatch.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserRepository userRepository;
    private final UserServices userServices;

    public UserServices(UserRepository userRepository, UserServices userServices) {
        this.userRepository = userRepository;
        this.userServices = userServices;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User registerUser (RegisterDTO registerDTO ) {
        User newUser = new User();

        String encryptedPassword = encoder.encode( registerDTO.password() );

        newUser.setFirstName(registerDTO.firstName());
        newUser.setLastName(registerDTO.lastName());
        newUser.setEmail(registerDTO.email());
        newUser.setPassword( encryptedPassword );

        return userRepository.save(newUser);
    }

}
