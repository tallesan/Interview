package com.example.Interview.service.Impl;

import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.QuestionsPoolRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionPoolServiceJdbc {
    private final QuestionsPoolRepository questionsPoolRepository;

    public QuestionPoolServiceJdbc(QuestionsPoolRepository questionsPoolRepository) {
        this.questionsPoolRepository = questionsPoolRepository;
    }

    public QuestionPool convertToQuestionPool(QuestionPoolDto questionPoolDto) {
        QuestionPool questionPool = new QuestionPool();
        questionPool.setId(questionPoolDto.getId());
        questionPool.setDescription(questionPool.getDescription());
        return questionPool;
    }

    public QuestionPoolDto convertToQuestionPoolDto(QuestionPool questionPool) {
        QuestionPoolDto questionPoolDto = new QuestionPoolDto();
        questionPoolDto.setId(questionPool.getId());
        questionPoolDto.setDescription(questionPool.getDescription());
        return questionPoolDto;
    }
}
