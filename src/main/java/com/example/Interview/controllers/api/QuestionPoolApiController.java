package com.example.Interview.controllers.api;

import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.model.QuestionPool;
import com.example.Interview.repository.QuestionsPoolRepository;
import com.example.Interview.service.Impl.QuestionPoolServiceJdbc;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/questionPool")
public class QuestionPoolApiController {

    private final QuestionsPoolRepository questionsPoolRepository;
    private final QuestionPoolServiceJdbc questionPoolServiceJdbc;

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestionPool() {
        List<QuestionPoolDto> questionPoolDtoList = questionPoolServiceJdbc.findAllQuestionPool();
        if (questionPoolDtoList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questionPoolDtoList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionPool(@PathVariable(value = "id") Long id) {
        QuestionPoolDto questionPoolDto = questionPoolServiceJdbc.findQuestionPoolDtoById(id);
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questionPoolDto, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> newQuestionPool(@RequestBody QuestionPoolDto questionPoolDto) {
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        QuestionPoolDto newQuestionPoolDto = questionPoolServiceJdbc.createQuestionPool(questionPoolDto);
        return new ResponseEntity<>(newQuestionPoolDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> updateQuestionPool(@RequestBody QuestionPoolDto questionPoolDto) {
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        questionPoolServiceJdbc.updateQuestionPoll(questionPoolDto);
        return new ResponseEntity<>(questionPoolDto, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> deleteQuestionPool(@PathVariable(value = "id") Long id) {
        QuestionPoolDto questionPoolDto = questionsPoolRepository.findQuestionPoolById(id);
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        questionPoolServiceJdbc.deleteQuestionPoolById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
