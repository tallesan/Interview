package com.example.Interview.service.Impl;

import com.example.Interview.dao.QuestionDao;
import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.QuestionRepository;
import com.example.Interview.repository.UserRepository;
import com.example.Interview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
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


    public QuestionDao convertQuestionToDao(Question question, String userName) {
        QuestionDao questionDao = new QuestionDao();
        questionDao.setId(question.getId());
        questionDao.setQuestionName(question.getQuestionName());
        questionDao.setQuestionPoolId(question.getQuestionPoolId());
        questionDao.setTrueQuestion(question.getTrueQuestion());
        questionDao.setAnswers(List.of(question.getAnswerOne(), question.getAnswerTwo(), question.getAnswerThree(), question.getAnswerFour()));
        questionDao.setUsers(userRepository.getUserByEmail(userName));
        return questionDao;
    }

    public List<QuestionDao> convertQuestionDaoList(List<Question> questions, String userName) {
        return questions.stream().map((Question question) -> convertQuestionToDao(question ,userName)).collect(Collectors.toList());
    }
}
