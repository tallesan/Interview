package com.example.Interview.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPollResultDto {
    private Long allAnswer;
    private Long trueAnswer;
    private Long uniqueTrueAnswer;
    private Long uniqueQuestionPoll;
    private Long allQuestionPoll;
}
