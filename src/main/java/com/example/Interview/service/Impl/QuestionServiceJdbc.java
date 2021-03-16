package com.example.Interview.service.Impl;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.model.Question;
import com.example.Interview.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceJdbc {
    private final QuestionsRepository questionsRepository;
    private final AnswerUserServiceJdbc answerUserService;

    @Autowired
    public QuestionServiceJdbc(QuestionsRepository questionsRepository, AnswerUserServiceJdbc answerUserService) {
        this.questionsRepository = questionsRepository;
        this.answerUserService = answerUserService;
    }

    public List<QuestionDto> findQuestionsDtoByQuestionPoolId(Long id) {
        return questionsRepository.findAllQuestionDtoByQuestionPoolId(id);
    }

    public QuestionDto findQuestionById(Long id){
        return questionsRepository.findQuestionById(id);
    }

    /**
     * переделать
     */
    public void saveQuestionList(List<QuestionDto> questionDtoList, Long questionPoolId) {
        String stringBuilder = questionDtoList.stream().map(questionDto -> "insert into question(answer_one, answer_two, answer_three, answer_four, question_name, true_question, question_pool_id) values(" + questionDto.getAnswerOne() + "," + questionDto.getAnswerTwo() + "," + questionDto.getAnswerThree() + "," + questionDto.getAnswerFour() + "," + questionDto.getQuestionName() + "," + questionDto.getTrueQuestion() + "," + questionPoolId + ")").collect(Collectors.joining());
    }

    public List<QuestionDto> saveQuestionDtoList(List<QuestionDto> questionDtoList, Long questionPoolId) {
        return questionsRepository.saveQuestionList(questionDtoList, questionPoolId);
    }

    public void saveQuestionDto(QuestionDto questionDto){
        questionsRepository.saveQuestion(questionDto);
    }

    public void updateQuestionDtoList(List<QuestionDto> questionDtoList) {
        questionsRepository.updateQuestionList(questionDtoList);
    }

    public void updateQuestionDto(QuestionDto questionDto){
        questionsRepository.updateQuestion(questionDto);

    }

    public Question convertToQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setQuestionName(questionDto.getQuestionName());
        question.setAnswerOne(questionDto.getAnswerOne());
        question.setAnswerTwo(questionDto.getAnswerTwo());
        question.setAnswerThree(questionDto.getAnswerThree());
        question.setAnswerFour(questionDto.getAnswerFour());
        question.setTrueQuestion(questionDto.getTrueQuestion());
        question.setQuestionPoolId(questionDto.getQuestionPoolId());
        return question;
    }

    public List<Question> convertListToQuestion(List<QuestionDto> questionDtoList) {
        return questionDtoList.stream().map(this::convertToQuestion).collect(Collectors.toList());
    }


    public void deleteQuestionByQuestionPoolId(List<QuestionDto> questionDtoList, Long QuestionPoolId) {
        answerUserService.deleteAnswerUserByQuestionIdList(questionDtoList);
        questionsRepository.deleteQuestionByQuestionPoolId(QuestionPoolId);

    }

    public void deleteQuestionById(Long id) {
        answerUserService.deleteAnswerUserByQuestionId(id);
        questionsRepository.deleteQuestionById(id);
    }
}
