package com.example.Interview.service.Impl;

import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.repository.QuestionsPoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionPoolServiceJdbc {
    private final QuestionsPoolRepository questionsPoolRepository;
    private final QuestionServiceJdbc questionServiceJdbc;


    public QuestionPoolServiceJdbc(QuestionsPoolRepository questionsPoolRepository, QuestionServiceJdbc questionServiceJdbc) {
        this.questionsPoolRepository = questionsPoolRepository;
        this.questionServiceJdbc = questionServiceJdbc;
    }

//    public QuestionPool convertToQuestionPool(QuestionPoolDto questionPoolDto) {
//        QuestionPool questionPool = new QuestionPool();
//        questionPool.setId(questionPoolDto.getId());
//        questionPool.setDescription(questionPool.getDescription());
//        return questionPool;
//    }
//
//    public QuestionPoolDto convertToQuestionPoolDto(QuestionPool questionPool) {
//        QuestionPoolDto questionPoolDto = new QuestionPoolDto();
//        questionPoolDto.setId(questionPool.getId());
//        questionPoolDto.setDescription(questionPool.getDescription());
//        return questionPoolDto;
//    }

    public List<QuestionPoolDto> findAllQuestionPool() {
        List<QuestionPoolDto> questionPooDtoList = questionsPoolRepository.findAllQuestionPool();
        for (QuestionPoolDto questionPooldto : questionPooDtoList) {
            questionPooldto.setQuestion(questionServiceJdbc.findQuestionsDtoByQuestionPoolId(questionPooldto.getId()));
        }
        return questionPooDtoList;
    }

    public QuestionPoolDto findQuestionPoolDtoById(Long id) {
        QuestionPoolDto questionPoolDto = questionsPoolRepository.findQuestionPoolById(id);

        questionPoolDto.setQuestion(questionServiceJdbc.findQuestionsDtoByQuestionPoolId(questionPoolDto.getId()));
        return questionPoolDto;
    }

    public QuestionPoolDto createQuestionPool(QuestionPoolDto questionPoolDtoSave) {
        QuestionPoolDto questionPoolDto = questionsPoolRepository.createQuestionPool(questionPoolDtoSave);
        if (questionPoolDto.getQuestion() != null) {
            questionPoolDto.setQuestion(questionServiceJdbc.saveQuestionDtoList(questionPoolDtoSave.getQuestion(), questionPoolDto.getId()));
        }
        return questionPoolDto;
    }

    public void updateQuestionPoll(QuestionPoolDto questionPoolDto) {
        questionsPoolRepository.updateQuestionPool(questionPoolDto);
        if (questionPoolDto.getQuestion() != null) {
            questionServiceJdbc.updateQuestionDtoList(questionPoolDto.getQuestion());
        }
    }

    public void deleteQuestionPoolById(Long id) {
        QuestionPoolDto questionPoolDto = findQuestionPoolDtoById(id);
        if (questionPoolDto != null) {
            if (questionPoolDto.getQuestion() != null) {
                questionServiceJdbc.deleteQuestionByQuestionPoolId(questionPoolDto.getQuestion(), id);
            }
            questionsPoolRepository.deleteQuestionPoolById(id);
        }
    }

}
