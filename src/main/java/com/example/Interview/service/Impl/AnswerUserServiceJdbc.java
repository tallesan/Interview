package com.example.Interview.service.Impl;

import com.example.Interview.Dto.AnswerUserDto;
import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.dao.AnswersToQuestions;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.model.AnswerUser;
import com.example.Interview.repository.AnswerUsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        for (AnswerUserDto answerUser : answerUserList) {
            answerUsersRepository.saveAnswerUserDto(answerUser);
//            answerUserRepository.save(answerUser);
        }
    }


    protected List<AnswerUserDto> convertToAnswer(AnswersToQuestions answersToQuestions) {
        List<AnswerUserDto> list = new ArrayList<>();
        for (QuestionDao questionDao : answersToQuestions.getAnswersToDao()) {
            AnswerUserDto answerUser = new AnswerUserDto(null, questionDao.getUsers().getId(), questionDao.getAnswerUser(), questionDao.getAnswerUser().equals(questionDao.getTrueQuestion()), questionDao.getId());
            list.add(answerUser);
        }
        return list;
    }
}
