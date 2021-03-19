package com.example.Interview.controllers.api;

import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.repository.QuestionsPoolRepository;
import com.example.Interview.service.Impl.QuestionPoolServiceJdbc;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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

    @ApiOperation(value = "Возвращаем список всех тем с вопросами",response = Iterable.class)
    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestionPool() {
        List<QuestionPoolDto> questionPoolDtoList = questionPoolServiceJdbc.findAllQuestionPool();
        if (questionPoolDtoList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questionPoolDtoList, new HttpHeaders(), HttpStatus.OK);
    }
    @ApiOperation(value = "Возвращаем тему опроса с вложенными вопросами",response = QuestionPoolDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionPool(@PathVariable(value = "id") Long id) {
        QuestionPoolDto questionPoolDto = questionPoolServiceJdbc.findQuestionPoolDtoById(id);
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questionPoolDto, new HttpHeaders(), HttpStatus.OK);
    }
    @ApiOperation(value = "Создаем новую тему с списком вопросов",response = QuestionPoolDto.class)
    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> newQuestionPool(@RequestBody QuestionPoolDto questionPoolDto) {
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        QuestionPoolDto newQuestionPoolDto = questionPoolServiceJdbc.createQuestionPool(questionPoolDto);
        return new ResponseEntity<>(newQuestionPoolDto, new HttpHeaders(), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Обновляем тему с списком вопросов",response = QuestionPoolDto.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> updateQuestionPool(@RequestBody QuestionPoolDto questionPoolDto) {
        if (questionPoolDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        questionPoolServiceJdbc.updateQuestionPoll(questionPoolDto);
        return new ResponseEntity<>(questionPoolDto, new HttpHeaders(), HttpStatus.OK);
    }
    @ApiOperation(value = "Удаляем тему с списком вопросов")
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
