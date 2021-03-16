package com.example.Interview.controllers;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.service.Impl.QuestionPoolServiceJdbc;
import com.example.Interview.service.Impl.QuestionServiceJdbc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DefaultController {

    //    private final QuestionPoolServiceImpl questionPoolService;
//    private final QuestionServiceImpl questionService;
//
//    @Autowired
//    public DefaultController(QuestionPoolServiceImpl questionPoolService, QuestionServiceImpl questionService) {
//        this.questionPoolService = questionPoolService;
//        this.questionService = questionService;
//    }
    private final QuestionServiceJdbc questionServiceJdbc;
    private final QuestionPoolServiceJdbc questionPoolServiceJdbc;

    public DefaultController(QuestionServiceJdbc questionServiceJdbc, QuestionPoolServiceJdbc questionPoolServiceJdbc) {
        this.questionServiceJdbc = questionServiceJdbc;
        this.questionPoolServiceJdbc = questionPoolServiceJdbc;
    }

    @GetMapping("/")
    public String initUser(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String initQuestionPool(Model model) {
//        List<QuestionPool> questionPoolList = questionPoolService.getAllQuestionPool();
        List<QuestionPoolDto> questionPoolList = questionPoolServiceJdbc.findAllQuestionPool();
        model.addAttribute("questionPoolList", questionPoolList);
        return "index";
    }

    @GetMapping("/addQuestionPool")
    public String addQuestionPool(Model model) {
//        QuestionPool questionPool = new QuestionPool();
        QuestionPoolDto questionPool = new QuestionPoolDto();
        model.addAttribute("questionPool", questionPool);
        return "new_QuestionPool";
    }

    @PostMapping("/saveNewQuestionPool")
    public String saveNewQuestionPoo(QuestionPoolDto questionPoolDto) {
//        questionPoolService.saveQuestionPool(questionPool);
        questionPoolServiceJdbc.createQuestionPool(questionPoolDto);
        return "redirect:/index";
    }

    @GetMapping("/addQuestion/{id}")
    public String addQuestion(@PathVariable(value = "id") Long id, Model model) {
//        QuestionPool questionPool = questionPoolService.getQuestionPoolById(id);
//        Question question = new Question();
//        question.setQuestionPoolId(questionPool.getId());
        QuestionPoolDto questionPool = questionPoolServiceJdbc.findQuestionPoolDtoById(id);
        QuestionDto question = new QuestionDto();
        question.setQuestionPoolId(questionPool.getId());
        model.addAttribute("questionPool", questionPool);
        model.addAttribute("question", question);
//        model.addAttribute("questionP", questionPoolService.getQuestionPoolById(id));
        return "addQuestion";
    }

    @PostMapping("/saveQuestion")
    public String saveNewQuestion(QuestionDto question) {
//        questionService.saveQuestion(question);
        questionServiceJdbc.saveQuestionDto(question);
        return "redirect:/index";
    }

    @GetMapping("/updateQuestionPool/{id}")
    public String updateQuestionPool(@PathVariable(value = "id") Long id, Model model) {
//        List<Question> questionList = questionService.searchQuestion(id);
        List<QuestionDto> questionList = questionServiceJdbc.findQuestionsDtoByQuestionPoolId(id);
        model.addAttribute("questionList", questionList);
        return "updateQuestionPool";
    }

    @GetMapping("/updateQuestion/{id}")
    public String updateQuestion(@PathVariable(value = "id") Long id, Model model) {
//        Question question = questionService.getQuestionById(id);
//        QuestionPool questionPool = questionPoolService.getQuestionPoolById(question.getQuestionPoolId());
//        question.setQuestionPoolId(questionPool.getId());

        QuestionDto question = questionServiceJdbc.findQuestionById(id);
        QuestionPoolDto questionPool = questionPoolServiceJdbc.findQuestionPoolDtoById(question.getQuestionPoolId());
        model.addAttribute("questionPool", questionPool);
        model.addAttribute("question", question);
        return "updateQuestion";
    }

    @PostMapping("/updateQuestionId")
    public String updateQuestionId(QuestionDto question) {
        questionServiceJdbc.updateQuestionDto(question);
//        questionService.update(question);
        return "redirect:/index";
    }

    @GetMapping("/deleteQuestionPool/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
//        questionPoolService.deleteQuestionPoolById(id);
        questionPoolServiceJdbc.deleteQuestionPoolById(id);
        return "redirect:/index";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(value = "id") Long id) {
        System.out.println("Delete question");
        questionServiceJdbc.deleteQuestionById(id);
        return "redirect:/index";
    }

}
