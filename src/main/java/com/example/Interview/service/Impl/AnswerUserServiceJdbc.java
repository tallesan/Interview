package com.example.Interview.service.Impl;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.repository.AnswerUsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerUserServiceJdbc {
    private final AnswerUsersRepository answerUsersRepository;

    public AnswerUserServiceJdbc(AnswerUsersRepository answerUsersRepository) {
        this.answerUsersRepository = answerUsersRepository;
    }

    public void deleteAnswerUserByQuestionIdList(List<QuestionDto> questionDtoList) {
        for (QuestionDto questionDto : questionDtoList) {
            answerUsersRepository.deleteAnswerUserByQuestionId(questionDto.getId());
        }
    }

    public void deleteAnswerUserByQuestionId(Long id) {
        answerUsersRepository.deleteAnswerUserByQuestionId(id);
    }
}
