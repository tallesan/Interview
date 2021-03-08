package com.example.Interview.controllers;

import com.example.Interview.dao.AnswerUserDto;
import com.example.Interview.dao.QuestionDao;
import com.example.Interview.dao.UserAnswerDto;
import com.example.Interview.model.AnswerUser;
import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.service.Impl.QuestionPoolServiceImpl;
import com.example.Interview.service.Impl.QuestionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserTestingController {
    private final QuestionPoolServiceImpl questionPoolService;
    private final QuestionServiceImpl questionService;

    public UserTestingController(QuestionPoolServiceImpl questionPoolService, QuestionServiceImpl questionService) {
        this.questionPoolService = questionPoolService;
        this.questionService = questionService;
    }

    @GetMapping("/test/take")
    public String testingUserIndex(Model model) {
        List<QuestionPool> questionPools = questionPoolService.getAllQuestionPool();
        model.addAttribute("questionPoolsList", questionPools);
        return "test/currentTests";
    }

    @GetMapping("/test/selectQuestion/{id}")
    public String selectQuestion(@PathVariable(value = "id") Long id, Model model) {
        List<QuestionDao> questionDaoList = questionService.convertQuestionDaoList(questionService.searchQuestion(id));
        System.out.println(questionDaoList);
        List<AnswerUserDto> answerUserList = new ArrayList<>();
        for (QuestionDao questionDao : questionDaoList) {
            answerUserList.add(new AnswerUserDto());
        }
        UserAnswerDto userAnswerDto = new UserAnswerDto(answerUserList);
        model.addAttribute("userAnswerDto", userAnswerDto);
        model.addAttribute("questionDaoList", questionDaoList);
        return "test/user_answer";
    }

    @PostMapping("/test/userAnswer")
    public String userAnswer(@ModelAttribute UserAnswerDto userAnswerDto) {
        System.out.println(userAnswerDto);
        System.out.println(userAnswerDto.getAnswerUsers().get(0).getQuestionId());
        return "redirect:/test/take";
    }
}
