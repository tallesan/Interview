package com.example.Interview.controllers;

import com.example.Interview.model.Users;
import com.example.Interview.service.Impl.StatisticService;
import com.example.Interview.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class StatisticController {
    private final UserServiceImpl userService;
    private final StatisticService statisticService;

    public StatisticController(UserServiceImpl userService, StatisticService statisticService) {
        this.userService = userService;
        this.statisticService = statisticService;
    }

    @GetMapping("/statistic/user")
    public String initStatistic(Model model, Principal principal){
        Users users = userService.getUsersByEmail(principal.getName());
        System.out.println(statisticService.statisticUser(users.getId()).size());
        model.addAttribute("users",users);
        return "statistic/stat_index";
    }
}
