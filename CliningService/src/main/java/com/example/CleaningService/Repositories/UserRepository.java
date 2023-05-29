package com.example.CleaningService.Repositories;

import com.example.CleaningService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByRole(String role);

}
