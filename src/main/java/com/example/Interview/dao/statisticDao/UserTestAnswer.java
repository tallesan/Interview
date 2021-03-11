package com.example.Interview.dao.statisticDao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTestAnswer {
    private Long id;
    private String answer;
    private Boolean trueAnswer;
    private Long questionId;
    private Long userId;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String questionName;
    private String trueQuestion;
    private Long questionPoolId;
}
