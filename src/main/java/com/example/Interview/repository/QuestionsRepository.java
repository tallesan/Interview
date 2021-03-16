package com.example.Interview.repository;

import com.example.Interview.Dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<QuestionDto> findAllQuestionDtoByQuestionPoolId(Long id) {
        String questionSql = "select * from question where question_pool_id =?";
        return jdbcTemplate.query(questionSql, new Object[]{id}, new BeanPropertyRowMapper<>(QuestionDto.class));
    }

    public List<QuestionDto> saveQuestionList(List<QuestionDto> questionDtoList, Long questionPoolId) {
        String sqlQuestion = "insert into question(answer_four, answer_one, answer_three, answer_two, question_name, true_question, question_pool_id) values(?,?,?,?,?,?,?) returning id";
        questionDtoList.forEach(questionDto -> questionDto.setId(jdbcTemplate.queryForObject(sqlQuestion, new Object[]{
                questionDto.getAnswerFour(),
                questionDto.getAnswerOne(),
                questionDto.getAnswerThree(),
                questionDto.getAnswerTwo(),
                questionDto.getQuestionName(),
                questionDto.getTrueQuestion(),
                questionPoolId}, Long.class)));
        return questionDtoList;
    }

    public void saveQuestion(QuestionDto questionDto) {
        String sqlQuestion = "insert into question(answer_four, answer_one, answer_three, answer_two, question_name, true_question, question_pool_id) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sqlQuestion,
                questionDto.getAnswerFour(),
                questionDto.getAnswerOne(),
                questionDto.getAnswerThree(),
                questionDto.getAnswerTwo(),
                questionDto.getQuestionName(),
                questionDto.getTrueQuestion(),
                questionDto.getQuestionPoolId());
    }

    public void updateQuestionList(List<QuestionDto> questionDtoList) {
        String sqlQuestion = "update question set id=?, answer_one=?, answer_two=?, answer_three=?, answer_four=?, question_name=?, true_question=?, question_pool_id=? where id=?";
        questionDtoList.forEach(questionDto -> jdbcTemplate.update(sqlQuestion,
                questionDto.getId(),
                questionDto.getAnswerOne(),
                questionDto.getAnswerTwo(),
                questionDto.getAnswerThree(),
                questionDto.getAnswerFour(),
                questionDto.getQuestionName(),
                questionDto.getTrueQuestion(),
                questionDto.getQuestionPoolId(),
                questionDto.getId()));
    }

    public void deleteQuestionByQuestionPoolId(Long questionPoolId) {
        String sql = "delete from question where question_pool_id=?";
        jdbcTemplate.update(sql, questionPoolId);
    }

    public QuestionDto findQuestionById(Long id) {
        String sql = "select * from question where id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(QuestionDto.class)).stream().findAny().orElse(null);
    }

    public void updateQuestion(QuestionDto questionDto) {
        String sqlQuestion = "update question set id=?, answer_one=?, answer_two=?, answer_three=?, answer_four=?, question_name=?, true_question=?, question_pool_id=? where id=?";
        jdbcTemplate.update(sqlQuestion,
                questionDto.getId(),
                questionDto.getAnswerOne(),
                questionDto.getAnswerTwo(),
                questionDto.getAnswerThree(),
                questionDto.getAnswerFour(),
                questionDto.getQuestionName(),
                questionDto.getTrueQuestion(),
                questionDto.getQuestionPoolId(),
                questionDto.getId());
    }

    public void deleteQuestionById(Long id) {
        String sql = "delete from question where id=?";
        jdbcTemplate.update(sql, id);
    }
}
