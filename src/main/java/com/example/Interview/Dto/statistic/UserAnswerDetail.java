package com.example.Interview.Dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAnswerDetail {
    private String description;
    private String answerName;
    private String trueAnswer;
    private String userAnswer;
}
