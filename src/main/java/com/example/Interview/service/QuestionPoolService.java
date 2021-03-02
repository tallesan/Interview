package com.example.Interview.service;

import com.example.Interview.model.QuestionPool;

import java.util.List;

public interface QuestionPoolService {
    List<QuestionPool> getAllQuestionPool();
    QuestionPool getQuestionPoolById(Long id);
    void saveQuestionPool(QuestionPool questionPool);
    void updateQuestionPool(Long id, QuestionPool questionPool);
    void deleteQuestionPool(QuestionPool questionPool);
    void deleteQuestionPoolById(Long id);
    void deleteAllQuestionPool();
}
