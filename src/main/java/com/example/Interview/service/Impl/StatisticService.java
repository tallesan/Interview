package com.example.Interview.service.Impl;

import com.example.Interview.Dto.statistic.UserAnswerDetail;
import com.example.Interview.Dto.statistic.UserPollResultDto;
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

    //    public List<UserTestAnswer> statisticUser(Long id) {
//        return statisticRepository.statisticUserAnswers(id);
//    }
    public UserPollResultDto findResultUser(String username) {
        Users users = userService.getUsersByEmail(username);
        return statisticRepository.statisticFromUserId(users.getId());
    }

    public List<UserAnswerDetail> findUserDetail(Long id) {
        return statisticRepository.findUserDetailsByUserId(id);
    }
}
