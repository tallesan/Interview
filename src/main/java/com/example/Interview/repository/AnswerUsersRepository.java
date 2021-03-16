package com.example.Interview.repository;

import com.example.Interview.Dto.AnswerUserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerUsersRepository {
    private final JdbcTemplate jdbcTemplate;

    public AnswerUsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteAnswerUserByQuestionId(Long QuestionId) {
        String sql = "delete from answer_user where question_id=?";
        jdbcTemplate.update(sql, QuestionId);
    }

    public void saveAnswerUserDto(AnswerUserDto answerUser) {
        String sql = "insert into answer_user(answer,true_answer,question_id,user_id) values(?,?,?,?)";
        jdbcTemplate.update(sql, answerUser.getAnswer(), answerUser.getTrueAnswer(), answerUser.getQuestionId(), answerUser.getUserId());
    }
}
