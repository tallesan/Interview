package com.example.Interview.controllers;

import com.example.Interview.Dto.statistic.UserAnswerDetail;
import com.example.Interview.Dto.statistic.UserPollResultDto;
import com.example.Interview.Dto.statistic.UserStatistic;
import com.example.Interview.model.Users;
import com.example.Interview.service.Impl.StatisticService;
import com.example.Interview.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class StatisticController {
    private final UserServiceImpl userService;
    private final StatisticService statisticService;

    public StatisticController(UserServiceImpl userService, StatisticService statisticService) {
        this.userService = userService;
        this.statisticService = statisticService;
    }

    @GetMapping("/statistic/user")
    public String initStatistic(Model model, Principal principal) {
        Users users = userService.getUsersByEmail(principal.getName());
        UserPollResultDto userPollResultDto = statisticService.findResultUser(principal.getName());
        model.addAttribute("userPollResultDto", userPollResultDto);
        model.addAttribute("users", users);
        return "statistic/stat_index";
    }

    @GetMapping("/statistic/detailsUserPoll/{userName}")
    public String detailUserAnswer(@PathVariable(value = "userName") String userName, Model model, Principal principal) {

        UserStatistic userStatistic = statisticService.findUserStatisticByUserName(principal.getName());
        System.out.println(userStatistic);
        if (!userName.equals(principal.getName())) return "redirect:/index";
        model.addAttribute("userStatistic", userStatistic);

        return "statistic/stat_details";

    }
    @GetMapping("statistic/allUser")
    public String statisticAllUser(Model model){

        return "statistic/stat_index";
    }
}
