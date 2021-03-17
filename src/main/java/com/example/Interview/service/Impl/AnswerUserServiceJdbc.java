package com.example.Interview.service.Impl;

import com.example.Interview.Dto.AnswerUserDto;
import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.dao.AnswersToQuestions;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.repository.AnswerUsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerUserServiceJdbc {
    private final AnswerUsersRepository answerUsersRepository;

    public AnswerUserServiceJdbc(AnswerUsersRepository answerUsersRepository) {
        this.answerUsersRepository = answerUsersRepository;
    }

    public void deleteAnswerUserByQuestionIdList(List<QuestionDto> questionDtoList) {
        questionDtoList.stream()
                .map(QuestionDto::getId)
                .forEach(answerUsersRepository::deleteAnswerUserByQuestionId);
    }

    public void deleteAnswerUserByQuestionId(Long id) {
        answerUsersRepository.deleteAnswerUserByQuestionId(id);
    }

    public void saveAnswerUser(AnswersToQuestions answersToQuestions) {
        List<AnswerUserDto> answerUserList = convertToAnswer(answersToQuestions);
        answerUserList.forEach(answerUsersRepository::saveAnswerUserDto);
    }


    protected List<AnswerUserDto> convertToAnswer(AnswersToQuestions answersToQuestions) {
        return answersToQuestions.getAnswersToDao()
                .stream().map(questionDao ->
                        new AnswerUserDto(null, questionDao.getUsers().getId(), questionDao.getAnswerUser(), questionDao.getAnswerUser().equals(questionDao.getTrueQuestion()), questionDao.getId()))
                .collect(Collectors.toList());
    }
}
