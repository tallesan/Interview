package com.example.Interview.utils;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DaoQuestionConvert {
    public final UserRepository userRepository;

    public DaoQuestionConvert(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public QuestionDao convertQuestionToDao(QuestionDto question, String userName) {
        QuestionDao questionDao = new QuestionDao();
        questionDao.setId(question.getId());
        questionDao.setQuestionName(question.getQuestionName());
        questionDao.setQuestionPoolId(question.getQuestionPoolId());
        questionDao.setTrueQuestion(question.getTrueQuestion());
        questionDao.setAnswers(List.of(question.getAnswerOne(), question.getAnswerTwo(), question.getAnswerThree(), question.getAnswerFour()));
        questionDao.setUsers(userRepository.getUserByEmail(userName));
        return questionDao;
    }

    public List<QuestionDao> convertQuestionDaoList(List<QuestionDto> questions, String userName) {
       return questions.stream().map(questionDto -> convertQuestionToDao(questionDto, userName)).collect(Collectors.toList());
    }
}
