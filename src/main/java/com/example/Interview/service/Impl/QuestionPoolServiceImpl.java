package com.example.Interview.service.Impl;

import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.QuestionPoolRepository;
import com.example.Interview.service.QuestionPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionPoolServiceImpl implements QuestionPoolService {

    private final QuestionPoolRepository questionPoolRepository;

    @Autowired
    public QuestionPoolServiceImpl(QuestionPoolRepository questionPoolRepository) {
        this.questionPoolRepository = questionPoolRepository;
    }

    @Override
    public List<QuestionPool> getAllQuestionPool() {
        return questionPoolRepository.findAll();
    }

    @Override
    public QuestionPool getQuestionPoolById(Long id) {
        return questionPoolRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveQuestionPool(QuestionPool questionPool) {
        questionPoolRepository.save(questionPool);
    }

    @Override
    public void updateQuestionPool(Long id, QuestionPool questionPool) {

    }

    @Override
    public void deleteQuestionPool(QuestionPool questionPool) {
        questionPoolRepository.delete(questionPool);
    }

    @Override
    public void deleteQuestionPoolById(Long id) {
        questionPoolRepository.deleteById(id);
    }

    @Override
    public void deleteAllQuestionPool() {
        questionPoolRepository.deleteAll();
    }
}
