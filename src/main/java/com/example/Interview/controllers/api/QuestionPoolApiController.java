package com.example.Interview.controllers.api;

import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.dao.UserDao;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.QuestionsPoolRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/questionPool")
public class QuestionPoolApiController {
    private final QuestionsPoolRepository questionsPoolRepository;

    public QuestionPoolApiController(QuestionsPoolRepository questionsPoolRepository) {
        this.questionsPoolRepository = questionsPoolRepository;
    }

    @GetMapping("/all")
    public List<QuestionPoolDto> getAllQuestionPool() {
        return questionsPoolRepository.findAllQuestionPool();
    }

    @GetMapping("/{id}")
    public QuestionPoolDto getQuestionPool(@PathVariable(value = "id") Long id) {
        return questionsPoolRepository.findQuestionPoolById(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> newQuestionPool(@RequestBody QuestionPoolDto questionPoolDto) {
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        QuestionPoolDto newQuestionPoolDto = questionsPoolRepository.createQuestionPool(questionPoolDto);
        return new ResponseEntity<>(newQuestionPoolDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> updateQuestionPool(@RequestBody QuestionPool questionPoolDto) {
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        questionsPoolRepository.updateQuestionPool(questionPoolDto);
        return new ResponseEntity<>(questionPoolDto, new HttpHeaders(), HttpStatus.OK);
    }


}
