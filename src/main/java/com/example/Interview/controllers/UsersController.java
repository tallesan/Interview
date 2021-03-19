package com.example.Interview.controllers;

import com.example.Interview.dao.UserDao;
import com.example.Interview.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UsersController {

    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registerPage(Model model) {
        model.addAttribute("userItem", new UserDao());
        System.out.println("1");
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(UserDao users) {
        System.out.println(users);
        userService.saveUsers(users);
        return "redirect:/login";
    }
}
