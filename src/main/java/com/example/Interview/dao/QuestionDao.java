package com.example.Interview.dao;

import com.example.Interview.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDao {
    private Long id;
    private String questionName;
    private List<String> answers;
    private String trueQuestion;
    private Long questionPoolId;
    private String answerUser;
    private Users users;
}
