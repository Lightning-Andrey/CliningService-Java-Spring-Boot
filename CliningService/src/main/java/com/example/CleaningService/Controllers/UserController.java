package com.example.CleaningService.Controllers;

import com.example.CleaningService.Models.User;
import com.example.CleaningService.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", user);
            return "profile";
        }
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("avatar") MultipartFile avatar,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userService.updateProfile(user, phoneNumber, avatar);
        }
        return "redirect:/profile";
    }
}

