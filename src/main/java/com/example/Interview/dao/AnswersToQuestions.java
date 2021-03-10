package com.example.Interview.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswersToQuestions {
    private List<QuestionDao> answersToDao;
}
