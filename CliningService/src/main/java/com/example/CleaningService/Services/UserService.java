package com.example.CleaningService.Services;

import com.example.CleaningService.Models.User;
import com.example.CleaningService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }
}
