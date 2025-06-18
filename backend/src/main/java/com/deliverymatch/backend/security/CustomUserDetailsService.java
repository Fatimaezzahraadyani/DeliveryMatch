package com.deliverymatch.backend.security;

import com.deliverymatch.backend.model.User;
import com.deliverymatch.backend.repository.AdminRepository;
import com.deliverymatch.backend.repository.DriverRepository;
import com.deliverymatch.backend.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final DriverRepository driverRepository;
    private final SenderRepository senderRepository;


    @Autowired
    public CustomUserDetailsService(AdminRepository adminRepository, DriverRepository driverRepository, SenderRepository senderRepository) {
        this.adminRepository = adminRepository;
        this.driverRepository = driverRepository;
        this.senderRepository = senderRepository;
    }

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException{

        User user = adminRepository.findByEmail(email).orElse(null);

        if(user == null){
            user = senderRepository.findByEmail(email).orElse(null);
        }

        if(user == null){
            user = driverRepository.findByEmail(email).orElse(null);
        }

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email);
        }

        System.out.println("ROLE from DB: " + user.getRole());

        return new CustomUserDetails(user);

    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities(){
//        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }
}

