package com.example.Interview.controllers;

import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.model.Users;
import com.example.Interview.service.Impl.QuestionPoolServiceImpl;
import com.example.Interview.service.Impl.QuestionServiceImpl;
import com.example.Interview.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DefaultController {

    private final QuestionPoolServiceImpl questionPoolService;
    private final QuestionServiceImpl questionService;

    @Autowired
    public DefaultController(QuestionPoolServiceImpl questionPoolService, QuestionServiceImpl questionService) {
        this.questionPoolService = questionPoolService;
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String initUser(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String initQuestionPool(Model model) {
        List<QuestionPool> questionPoolList = questionPoolService.getAllQuestionPool();
        model.addAttribute("questionPoolList", questionPoolList);
        return "index";
    }

    @GetMapping("/addQuestionPool")
    public String addQuestionPool(Model model) {
        QuestionPool questionPool = new QuestionPool();
        model.addAttribute("questionPool", questionPool);
        return "new_QuestionPool";
    }

    @PostMapping("/saveNewQuestionPool")
    public String saveNewQuestionPoo(QuestionPool questionPool) {
        questionPoolService.saveQuestionPool(questionPool);
        return "redirect:/index";
    }

    @GetMapping("/addQuestion/{id}")
    public String addQuestion(@PathVariable(value = "id") Long id, Model model) {
        Question question = new Question();
        question.setQuestionPool(questionPoolService.getQuestionPoolById(id));
        model.addAttribute("question", question);
        model.addAttribute("questionP", questionPoolService.getQuestionPoolById(id));
        return "addQuestion";
    }

    @PostMapping("/saveQuestion")
    public String saveNewQuestion(Question question) {
        questionService.saveQuestion(question);
        return "redirect:/index";
    }

    @GetMapping("/updateQuestionPool/{id}")
    public String updateQuestionPool(@PathVariable(value = "id") Long id, Model model) {
        List<Question> questionList = questionService.searchQuestion(id);
        model.addAttribute("questionList", questionList);
        return "updateQuestionPool";
    }

    @GetMapping("/updateQuestion/{id}")
    public String updateQuestion(@PathVariable(value = "id") Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        System.out.println(question);
        model.addAttribute("question",question);
        return "updateQuestion";
    }
    @PostMapping("/updateQuestionId")
    public String updateQuestionId(Question question){
        System.out.println(question);
        questionService.update(question);
        return "redirect:/index";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(value = "id") Long id){
        System.out.println("Delete question");
        return "redirect:/index";
    }

}
