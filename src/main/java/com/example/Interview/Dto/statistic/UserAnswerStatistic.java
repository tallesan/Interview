package com.example.Interview.Dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAnswerStatistic {
    private String description;
    private List<UserAnswerStatisticDetails> userAnswerStatisticDetails;
}
