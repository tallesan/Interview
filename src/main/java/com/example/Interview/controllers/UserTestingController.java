package com.example.Interview.controllers;

import com.example.Interview.dao.AnswersToQuestions;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.AnswerUserRepository;
import com.example.Interview.service.Impl.QuestionPoolServiceImpl;
import com.example.Interview.service.Impl.QuestionServiceImpl;
import com.example.Interview.service.Impl.UsersAnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserTestingController {
    private final QuestionPoolServiceImpl questionPoolService;
    private final QuestionServiceImpl questionService;
    private final UsersAnswerService usersAnswerService;

    public UserTestingController(QuestionPoolServiceImpl questionPoolService, QuestionServiceImpl questionService, UsersAnswerService usersAnswerService) {
        this.questionPoolService = questionPoolService;
        this.questionService = questionService;
        this.usersAnswerService = usersAnswerService;
    }

    @GetMapping("/test/take")
    public String testingUserIndex(Model model) {
        List<QuestionPool> questionPools = questionPoolService.getAllQuestionPool();
        model.addAttribute("questionPoolsList", questionPools);
        return "test/currentTests";
    }

    @GetMapping("/test/selectQuestion/{id}")
    public String selectQuestion(@PathVariable(value = "id") Long id, Model model, Principal principal) {
        System.out.println(principal.getName());
        List<QuestionDao> questionDaoList = questionService.convertQuestionDaoList(questionService.searchQuestion(id), principal.getName());
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
