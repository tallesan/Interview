package com.example.Interview.Dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SystemStatistic {
    private Long userSum;
    private Long questionPullSum;
    private Long questionSum;
    private Long answerSum;
    private Long trueQuestionSum;
}
