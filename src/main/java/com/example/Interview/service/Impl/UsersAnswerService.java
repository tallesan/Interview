package com.example.Interview.service.Impl;

import com.example.Interview.dao.AnswersToQuestions;
import com.example.Interview.model.AnswerUser;
import com.example.Interview.repository.AnswerUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersAnswerService {
    private final AnswerUserRepository answerUserRepository;
    private final QuestionServiceImpl questionService;

    public UsersAnswerService(AnswerUserRepository answerUserRepository, QuestionServiceImpl questionService) {
        this.answerUserRepository = answerUserRepository;
        this.questionService = questionService;
    }

    public void saveAnswer(AnswersToQuestions answersToQuestions) {
        List<AnswerUser> answerUserList = convertToAnswer(answersToQuestions);
        answerUserList.forEach(answerUserRepository::save);

    }

    protected List<AnswerUser> convertToAnswer(AnswersToQuestions answersToQuestions) {
        return answersToQuestions.getAnswersToDao().stream().map(questionDao -> new AnswerUser(null, questionDao.getUsers(), questionDao.getAnswerUser(), questionDao.getAnswerUser().equals(questionDao.getTrueQuestion()), questionService.getQuestionById(questionDao.getId()))).collect(Collectors.toList());
    }
}
