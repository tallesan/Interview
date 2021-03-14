package com.example.Interview.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDto {
    private Long id;
    private String questionName;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String trueQuestion;
    private Long questionPoolId;
}
