package com.example.Interview.service.Impl;

import com.example.Interview.dao.QuestionDao;
import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.QuestionRepository;
import com.example.Interview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveListQuestion(List<Question> questions, QuestionPool questionPool) {
        questions.forEach(questionRepository::save);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> searchQuestion(Long id) {
        return questionRepository.findByQuestionPoolId(id);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow();
    }

    public void update(Question questionUpd) {
        Question question = getQuestionById(questionUpd.getId());
        question.setQuestionName(questionUpd.getQuestionName());
        question.setAnswerOne(questionUpd.getAnswerOne());
        question.setAnswerTwo(questionUpd.getAnswerTwo());
        question.setAnswerThree(questionUpd.getAnswerThree());
        question.setAnswerFour(questionUpd.getAnswerFour());
        question.setTrueQuestion(questionUpd.getTrueQuestion());
        questionRepository.save(question);
    }

    public QuestionDao convertQuestionToDao(Question question) {
        QuestionDao questionDao = new QuestionDao();
        questionDao.setId(question.getId());
        questionDao.setQuestionName(question.getQuestionName());
        questionDao.setQuestionPool(question.getQuestionPool());
        questionDao.setTrueQuestion(question.getTrueQuestion());
        questionDao.setAnswers(List.of(question.getAnswerOne(), question.getAnswerTwo(), question.getAnswerThree(), question.getAnswerFour()));
        return questionDao;
    }

    public List<QuestionDao> convertQuestionDaoList(List<Question> questions) {
        return questions.stream().map(this::convertQuestionToDao).collect(Collectors.toList());
    }
}
