package com.example.Interview.repository;

import com.example.Interview.Dto.statistic.UserAnswerDetail;
import com.example.Interview.Dto.statistic.UserPollResultDto;
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

    public UserPollResultDto statisticFromUserId(Long id) {
        String sql = "select count(*) as allAnswer,(select count(*) from answer_user where user_id = ? and true_answer = true) as trueAnswer," +
                     " (select count(true_answer) from answer_user where user_id = ? and true_answer=true) as uniqueTrueAnswer," +
                     " (select count(distinct (description)) from answer_user join question q on answer_user.question_id = q.id join question_pool qp on q.question_pool_id = qp.id where user_id = ?) as uniqueQuestionPoll," +
                     " (select count(*) from question_pool) as allQuestionPoll from answer_user where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id, id ,id, id}, new BeanPropertyRowMapper<>(UserPollResultDto.class)).stream().findAny().orElse(null);
    }

    public List<UserAnswerDetail> findUserDetailsByUserId(Long id) {
        String sql = "select description, question_name as answerName, true_question as trueAnswer, answer as userAnswer from answer_user join question q on answer_user.question_id = q.id join question_pool qp on q.question_pool_id = qp.id where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserAnswerDetail.class));
    }
}
