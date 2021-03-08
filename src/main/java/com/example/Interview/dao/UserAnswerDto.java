package com.example.Interview.dao;

import com.example.Interview.model.AnswerUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAnswerDto {
    List<AnswerUserDto> answerUsers;
}
