package com.example.Interview.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerUserDto {
    private Long id;
    private Long userId;
    private String answer;
    private Boolean trueAnswer;
    private Long questionId;
}
