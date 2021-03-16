package com.example.Interview.service.Impl;

import com.example.Interview.dao.AnswersToQuestions;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.model.AnswerUser;
import com.example.Interview.repository.AnswerUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersAnswerService {
    private final AnswerUserRepository answerUserRepository;
    private final QuestionServiceImpl questionService;
    private final QuestionServiceJdbc questionServiceJdbc;

    public UsersAnswerService(AnswerUserRepository answerUserRepository, QuestionServiceImpl questionService, QuestionServiceJdbc questionServiceJdbc) {
        this.answerUserRepository = answerUserRepository;
        this.questionService = questionService;
        this.questionServiceJdbc = questionServiceJdbc;
    }

    public void saveAnswer(AnswersToQuestions answersToQuestions) {
        List<AnswerUser> answerUserList = convertToAnswer(answersToQuestions);
        for (AnswerUser answerUser : answerUserList) {
            answerUserRepository.save(answerUser);
        }
    }

    protected List<AnswerUser> convertToAnswer(AnswersToQuestions answersToQuestions) {
        List<AnswerUser> list = new ArrayList<>();
        for (QuestionDao questionDao : answersToQuestions.getAnswersToDao()) {
            AnswerUser answerUser = new AnswerUser(null, questionDao.getUsers(), questionDao.getAnswerUser(), questionDao.getAnswerUser().equals(questionDao.getTrueQuestion()), questionService.getQuestionById(questionDao.getId()));
            list.add(answerUser);
        }
        return list;
    }
}
