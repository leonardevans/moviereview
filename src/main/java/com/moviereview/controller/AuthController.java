package com.moviereview.controller;

import com.moviereview.dto.AuthRequest;
import com.moviereview.model.User;
import com.moviereview.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage(Model model){
        model.addAttribute("authRequest", new AuthRequest());
        return "authentication";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("authRequest") AuthRequest authRequest, Model model){
        model.addAttribute("authRequest", authRequest);

        //check if user with this name exists
        if(userRepo.existsAllByUsername(authRequest.getUsername())){
            model.addAttribute("usernameTaken", "This username is taken. Please use another username");
            return "authentication";
        }

        //create new user
        User newUser = new User();
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        newUser.setEmail(authRequest.getUsername()+ "@movie.com");
        newUser.setRole("ROLE_MEMBER");

        //save user
        userRepo.save(newUser);
        return "redirect:/login?registered";
    }
}
