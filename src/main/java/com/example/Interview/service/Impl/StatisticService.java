package com.example.Interview.service.Impl;

import com.example.Interview.dao.statisticDao.UserTestAnswer;
import com.example.Interview.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<UserTestAnswer> statisticUser(Long id) {
        return statisticRepository.statisticUserAnswers(id);
    }
}
