package com.example.Interview.service.Impl;

import com.example.Interview.Dto.statistic.*;
import com.example.Interview.model.Users;
import com.example.Interview.repository.StatisticRepository;
import com.example.Interview.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final UserService userService;

    public StatisticService(StatisticRepository statisticRepository, UserService userService) {
        this.statisticRepository = statisticRepository;
        this.userService = userService;
    }

    public UserPollResultDto findResultUser(String username) {
        Users users = userService.getUsersByEmail(username);
        return statisticRepository.statisticFromUserId(users.getId());
    }

    public UserStatistic findUserStatisticByUserName(String userName) {
        UserStatistic userStatistic = new UserStatistic();
        Users users = userService.getUsersByEmail(userName);
        if (users != null) {
            userStatistic.setUserName(users.getEmail());
            userStatistic.setFirstName(users.getFirstName());
            userStatistic.setLastName(users.getLastName());
            List<UserAnswerStatistic> userAnswerStatisticList = statisticRepository.findUserAnswerPollByUserName(users.getEmail());
            userAnswerStatisticList.forEach(userAnswerStatistic -> userAnswerStatistic.setUserAnswerStatisticDetails(statisticRepository.findUserStatisticDetailsByUserNameAndDescription(users.getEmail(), userAnswerStatistic.getDescription())));
            userStatistic.setUserAnswerStatistics(userAnswerStatisticList);
        }
        return userStatistic;
    }

    public SystemStatistic countSystemStatistic() {
        return statisticRepository.countSystemStatistic();
    }

    public List<StatisticUserDetailsPoll> getStatisticUserDetailsPoll() {
        List<StatisticUserDetailsPoll> statisticUserDetailsPolls = statisticRepository.getStatisticQuestionPoll();
        for (StatisticUserDetailsPoll statisticUserDetailsPoll : statisticUserDetailsPolls) {
            statisticUserDetailsPoll.setUserDetailList(statisticRepository.getStatisticUserDetails(statisticUserDetailsPoll.getDescription()));
        }
        return statisticUserDetailsPolls;
    }
}
