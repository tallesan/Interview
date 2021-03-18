package com.example.Interview.repository;

import com.example.Interview.Dto.statistic.*;
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
                " (select count(distinct (question_id)) from answer_user where user_id = ? and true_answer = true) as uniqueTrueAnswer," +
                " (select count(distinct (description)) from answer_user join question q on answer_user.question_id = q.id join question_pool qp on q.question_pool_id = qp.id where user_id = ?) as uniqueQuestionPoll," +
                " (select count(*) from question_pool) as allQuestionPoll from answer_user where user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id, id, id, id}, new BeanPropertyRowMapper<>(UserPollResultDto.class)).stream().findAny().orElse(null);
    }

    public List<UserAnswerStatistic> findUserAnswerPollByUserName(String userName) {
        String sql = "select distinct (description) from answer_user join users u on answer_user.user_id = u.id join question q on answer_user.question_id = q.id join question_pool qp on q.question_pool_id = qp.id where email = ?";

        return jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper<>(UserAnswerStatistic.class));
    }

    public List<UserAnswerStatisticDetails> findUserStatisticDetailsByUserNameAndDescription(String userName, String description) {
        String sql = "select question_name as answerName, true_question as trueAnswer, answer as userAnswer, true_answer as answerScore from answer_user join users u on answer_user.user_id = u.id join question q on answer_user.question_id = q.id join question_pool qp on q.question_pool_id = qp.id where email = ? and description = ?";
        return jdbcTemplate.query(sql, new Object[]{userName, description}, new BeanPropertyRowMapper<>(UserAnswerStatisticDetails.class));

    }

    public SystemStatistic countSystemStatistic() {
        String sql = "select count(*) as userSum, (select count(*) from question_pool) as questionPullSum, (select count(*) from question) as questionSum, (select count(*) from answer_user) as answerSum, (select count(*) from answer_user where true_answer = true) as trueQuestionSum from users";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SystemStatistic.class));
    }

    public List<StatisticUserDetailsPoll> getStatisticQuestionPoll() {
        String sql = "select description, count(question_name) as questionSum from question_pool join question q on question_pool.id = q.question_pool_id group by description";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StatisticUserDetailsPoll.class));
    }

    public List<SystemUserDetail> getStatisticUserDetails(String description) {
        String sql = "select distinct email, count(distinct question_id) as trueAnswer from answer_user join question q on answer_user.question_id = q.id join question_pool qp on q.question_pool_id = qp.id join users u on answer_user.user_id = u.id where description = ? and true_answer = true group by email";
        return jdbcTemplate.query(sql, new Object[]{description}, new BeanPropertyRowMapper<>(SystemUserDetail.class));
    }
}
