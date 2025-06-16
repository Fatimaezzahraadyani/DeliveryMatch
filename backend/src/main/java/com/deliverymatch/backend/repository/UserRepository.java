package com.deliverymatch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverymatch.backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByEmail(String email);

    Optional<User> findByEmail(String email);
}
