package com.project.bookStore.controllers;

import com.project.bookStore.dataAccess.entities.User;
import com.project.bookStore.service.UserService;
import com.project.bookStore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String index(Authentication authentication) {
        return authentication == null ? "index" : "redirect:/authenticated";
    }

    @GetMapping("/index")
    public String showHomePage(Model model, Principal principal){
        if (principal != null) {
            String email = principal.getName();
            User user = userService.findByEmail(email);
            model.addAttribute("user", user);
            return "index";
        }
        return "login";
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        return "/authenticated";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        return authentication == null ? "login" : "redirect:/authenticated";
    }


}
