package com.example.Interview.dao;

import com.example.Interview.model.Users;
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
    private String trueAnswerBool;
    private Long questionId;
}
