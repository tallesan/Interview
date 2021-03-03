package com.example.Interview.controllers;

import com.example.Interview.dao.UserDao;
import com.example.Interview.model.Users;
import com.example.Interview.service.Impl.UserServiceImpl;
import com.example.Interview.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registr")
    public String registerPage(Model model) {
        model.addAttribute("userItem", new UserDao());
        System.out.println("1");
        return "registration";
    }

    @PostMapping("/registr")
    public String registration(UserDao users) {
        System.out.println(users);
        userService.saveUsers(users);
        return "redirect:/login";
    }
}
