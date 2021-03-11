package com.example.Interview.dao.statisticDao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticUserDao {
    private List<StatisticUserAnswer> statisticUserAnswers;
}
