package com.example.Interview.repository;

import com.example.Interview.dao.statisticDao.UserTestAnswer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticRepository {

    private final JdbcTemplate jdbcTemplate;

    public StatisticRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserTestAnswer> statisticUserAnswers(Long id) {

        return jdbcTemplate.query("select * from answer_user s " +
                "join question q on s.question_id = q.id where user_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(UserTestAnswer.class));
    }

}
