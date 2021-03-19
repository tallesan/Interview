package com.example.Interview.controllers;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.service.Impl.QuestionPoolServiceJdbc;
import com.example.Interview.service.Impl.QuestionServiceJdbc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@ApiIgnore
@Controller
@AllArgsConstructor
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

//    public DefaultController(QuestionServiceJdbc questionServiceJdbc, QuestionPoolServiceJdbc questionPoolServiceJdbc) {
//        this.questionServiceJdbc = questionServiceJdbc;
//        this.questionPoolServiceJdbc = questionPoolServiceJdbc;
//    }

    @GetMapping("/")
    public String initUser(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String initQuestionPool(Model model) {
        List<QuestionPoolDto> questionPoolList = questionPoolServiceJdbc.findAllQuestionPool();
        model.addAttribute("questionPoolList", questionPoolList);
        return "index";
    }

    @GetMapping("/addQuestionPool")
    public String addQuestionPool(Model model) {
        QuestionPoolDto questionPool = new QuestionPoolDto();
        model.addAttribute("questionPool", questionPool);
        return "new_QuestionPool";
    }

    @PostMapping("/saveNewQuestionPool")
    public String saveNewQuestionPoo(QuestionPoolDto questionPoolDto) {
        questionPoolServiceJdbc.createQuestionPool(questionPoolDto);
        return "redirect:/index";
    }

    @GetMapping("/addQuestion/{id}")
    public String addQuestion(@PathVariable(value = "id") Long id, Model model) {
        QuestionPoolDto questionPool = questionPoolServiceJdbc.findQuestionPoolDtoById(id);
        QuestionDto question = new QuestionDto();
        question.setQuestionPoolId(questionPool.getId());
        model.addAttribute("questionPool", questionPool);
        model.addAttribute("question", question);
        return "addQuestion";
    }

    @PostMapping("/saveQuestion")
    public String saveNewQuestion(QuestionDto question) {
        questionServiceJdbc.saveQuestionDto(question);
        return "redirect:/index";
    }

    @GetMapping("/updateQuestionPool/{id}")
    public String updateQuestionPool(@PathVariable(value = "id") Long id, Model model) {
        List<QuestionDto> questionList = questionServiceJdbc.findQuestionsDtoByQuestionPoolId(id);
        model.addAttribute("questionList", questionList);
        return "updateQuestionPool";
    }

    @GetMapping("/updateQuestion/{id}")
    public String updateQuestion(@PathVariable(value = "id") Long id, Model model) {
        QuestionDto question = questionServiceJdbc.findQuestionById(id);
        QuestionPoolDto questionPool = questionPoolServiceJdbc.findQuestionPoolDtoById(question.getQuestionPoolId());
        model.addAttribute("questionPool", questionPool);
        model.addAttribute("question", question);
        return "updateQuestion";
    }

    @PostMapping("/updateQuestionId")
    public String updateQuestionId(QuestionDto question) {
        questionServiceJdbc.updateQuestionDto(question);
        return "redirect:/index";
    }

    @GetMapping("/deleteQuestionPool/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        questionPoolServiceJdbc.deleteQuestionPoolById(id);
        return "redirect:/index";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(value = "id") Long id) {
        questionServiceJdbc.deleteQuestionById(id);
        return "redirect:/index";
    }

}
