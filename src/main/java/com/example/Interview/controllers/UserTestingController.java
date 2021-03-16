package com.example.Interview.controllers;

import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.dao.AnswersToQuestions;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.service.Impl.*;
import com.example.Interview.utils.DaoQuestionConvert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserTestingController {
    private final QuestionServiceJdbc questionServiceJdbc;
    private final QuestionPoolServiceJdbc questionPoolServiceJdbc;
    private final UsersAnswerService usersAnswerService;
    private final DaoQuestionConvert questionConvert;

    public UserTestingController(QuestionServiceJdbc questionServiceJdbc, QuestionPoolServiceJdbc questionPoolServiceJdbc, UsersAnswerService usersAnswerService, DaoQuestionConvert questionConvert) {
        this.questionServiceJdbc = questionServiceJdbc;
        this.questionPoolServiceJdbc = questionPoolServiceJdbc;
        this.usersAnswerService = usersAnswerService;
        this.questionConvert = questionConvert;
    }

    @GetMapping("/test/take")
    public String testingUserIndex(Model model) {
        List<QuestionPoolDto> questionPools = questionPoolServiceJdbc.findAllQuestionPool();
        model.addAttribute("questionPoolsList", questionPools);
        return "test/currentTests";
    }

    @GetMapping("/test/selectQuestion/{id}")
    public String selectQuestion(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        List<QuestionDao> questionDaoList = questionConvert.convertQuestionDaoList(questionServiceJdbc.findQuestionsDtoByQuestionPoolId(id), principal.getName());
        AnswersToQuestions answersToQuestions = new AnswersToQuestions();
        answersToQuestions.setAnswersToDao(questionDaoList);
        model.addAttribute("answersToQuestions", answersToQuestions);
        return "test/user_answer";
    }

    @PostMapping("/test/userAnswer")
    public String userAnswer(AnswersToQuestions userAnswerDto) {
        usersAnswerService.saveAnswer(userAnswerDto);
        return "redirect:/test/take";
    }
}
