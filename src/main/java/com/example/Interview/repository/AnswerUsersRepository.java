package com.example.Interview.repository;

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
        jdbcTemplate.update("delete from answer_user where question_id=?", QuestionId);
    }
}
