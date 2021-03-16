package com.example.Interview.repository;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsPoolRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionsPoolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<QuestionPoolDto> findAllQuestionPool() {
        String sql = "select * from question_pool";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QuestionPoolDto.class));
    }

    public QuestionPoolDto findQuestionPoolById(Long id) {
        String sql = "select * from question_pool where id = ?";
        QuestionPoolDto questionPoolDto = jdbcTemplate.query(sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(QuestionPoolDto.class))
                .stream().findAny().orElse(null);
        return questionPoolDto;
    }

    public QuestionPoolDto createQuestionPool(QuestionPoolDto questionPoolDto) {
        String sql = "insert into question_pool(description) values(?) returning id";
        questionPoolDto.setId(jdbcTemplate.queryForObject(sql,
                new Object[]{questionPoolDto.getDescription()}, Long.class));
        return questionPoolDto;
    }

    public void updateQuestionPool(QuestionPoolDto questionPool) {
        String sql = "update question_pool set id=?, description=? where id=?";
        jdbcTemplate.update(sql, questionPool.getId(), questionPool.getDescription(), questionPool.getId());
    }

    public void deleteQuestionPoolById(Long id) {
        String sql = "delete from question_pool where id=?";
        jdbcTemplate.update(sql, id);
    }
}
