package com.bnymellon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class MainControllerImpl implements MainController{
    @Override
	@GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @Override
	@GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @Override
	@GetMapping("/")
    public String home() {
        return "index";
    }
}