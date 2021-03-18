package com.example.Interview.Dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserStatistic {
    private String userName;
    private String firstName;
    private String lastName;
    private List<UserAnswerStatistic> userAnswerStatistics;
}
