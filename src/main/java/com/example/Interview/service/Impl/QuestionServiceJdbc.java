package com.example.Interview.service.Impl;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceJdbc {
    public Question convertToQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setQuestionName(questionDto.getQuestionName());
        question.setAnswerOne(questionDto.getAnswerOne());
        question.setAnswerTwo(questionDto.getAnswerTwo());
        question.setAnswerThree(questionDto.getAnswerThree());
        question.setAnswerFour(questionDto.getAnswerFour());
        question.setTrueQuestion(questionDto.getTrueQuestion());
//        question.setQuestionPool(questionDto.getQuestionPoolId());
        return question;
    }

    public List<Question> convertListToQuestion(List<QuestionDto> questionDtoList) {
        return questionDtoList.stream().map(this::convertToQuestion).collect(Collectors.toList());
    }

}
