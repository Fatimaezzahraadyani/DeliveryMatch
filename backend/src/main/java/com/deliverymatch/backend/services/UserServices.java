package com.deliverymatch.backend.services;

import com.deliverymatch.backend.dto.CreateUserDto;
import com.deliverymatch.backend.dto.RegisterDTO;
import com.deliverymatch.backend.dto.UpdateUserDto;
import com.deliverymatch.backend.dto.UserDTO;
import com.deliverymatch.backend.model.*;
import com.deliverymatch.backend.repository.AdminRepository;
import com.deliverymatch.backend.repository.DriverRepository;
import com.deliverymatch.backend.repository.SenderRepository;
import com.deliverymatch.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserServices {

    private final AdminRepository adminRepository;
    private final DriverRepository driverRepository;
    private final SenderRepository senderRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final UserRepository userRepository;

    public UserServices(AdminRepository adminRepository,
                        DriverRepository driverRepository,
                        SenderRepository senderRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.driverRepository = driverRepository;
        this.senderRepository = senderRepository;
        this.userRepository = userRepository;
    }
    public ResponseEntity<?> register(RegisterDTO dto) {
        try {
            User user = registerUser(dto);
            return ResponseEntity.ok("Utilisateur enregistré avec succès : " + user.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de l'enregistrement");
        }
    }

    public User registerUser(RegisterDTO dto) {
        User newUser;

        String encryptedPassword = encoder.encode(dto.password());

        switch (dto.role()) {
            case ADMIN -> {
                Admin admin = new Admin();
                admin.setFirstName(dto.firstName());
                admin.setLastName(dto.lastName());
                admin.setEmail(dto.email());
                admin.setPassword(encryptedPassword);
                newUser = adminRepository.save(admin);
            }
            case DRIVER -> {
                Driver driver = new Driver();
                driver.setFirstName(dto.firstName());
                driver.setLastName(dto.lastName());
                driver.setEmail(dto.email());
                driver.setPassword(encryptedPassword);
                newUser = driverRepository.save(driver);
            }
            case SENDER -> {
                Sender sender = new Sender();
                sender.setFirstName(dto.firstName());
                sender.setLastName(dto.lastName());
                sender.setEmail(dto.email());
                sender.setPassword(encryptedPassword);
                newUser = senderRepository.save(sender);
            }
            default -> throw new IllegalArgumentException("Rôle non supporté : " + dto.role());
        }

        return newUser;
    }

    public List<UserDTO> getAllUsers (){
        List<UserDTO> admins = adminRepository.findAll().stream()
                .map(a-> new UserDTO(a.getId(), a.getFirstName(), a.getLastName(), a.getEmail(), "ADMIN" ))
                .toList();

        List<UserDTO> senders = senderRepository.findAll().stream()
                .map(sender -> new UserDTO(sender.getId(), sender.getFirstName(), sender.getLastName(), sender.getEmail(), "SENDER"))
                .toList();

        List<UserDTO> drivers = driverRepository.findAll().stream()
                .map(driver -> new UserDTO(driver.getId(), driver.getFirstName(), driver.getLastName(), driver.getEmail(),"DRIVER"))
                .toList();


        return Stream.concat(Stream.concat(admins.stream(), drivers.stream()), senders.stream())
                .toList();

    }

    public ResponseEntity<?> createUserByAdmin(CreateUserDto dto) {
        try{
            User user;
            String encryptedPassword = encoder.encode(dto.password());
            switch (dto.role()) {
                case ADMIN -> {
                    Admin admin = new Admin();
                    admin.setFirstName(dto.firstName());
                    admin.setLastName(dto.lastName());
                    admin.setEmail(dto.email());
                    admin.setPassword(encryptedPassword);
                    user = adminRepository.save(admin);
                }
                case DRIVER -> {
                    Driver driver = new Driver();
                    driver.setFirstName(dto.firstName());
                    driver.setLastName(dto.lastName());
                    driver.setEmail(dto.email());
                    driver.setPassword(encryptedPassword);
                    user = driverRepository.save(driver);

                }
                case SENDER -> {
                    Sender sender = new Sender();
                    sender.setFirstName(dto.firstName());
                    sender.setLastName(dto.lastName());
                    sender.setEmail(dto.email());
                    sender.setPassword(encryptedPassword);
                    user = senderRepository.save(sender);
                }
                default -> throw new IllegalArgumentException("Role not supported.");
            }

            return ResponseEntity.ok("Utilisateur bien ajouté " + user.getFirstName() + " " + user.getEmail());

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public User UpdateUser(Long id,UpdateUserDto dto) {
        User ExistingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("user intouvable"));

        ExistingUser.setFirstName(dto.firstName());
        ExistingUser.setLastName(dto.lastName());
        ExistingUser.setEmail(dto.email());
        if(dto.password() != null && !dto.password().isEmpty()){
            ExistingUser.setPassword(encoder.encode(dto.password()));
        }
        ExistingUser.setRole(dto.role());

        return userRepository.save(ExistingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
