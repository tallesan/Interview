package com.example.Interview.repository;

import com.example.Interview.Dto.QuestionDto;
import com.example.Interview.Dto.QuestionPoolDto;
import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsPoolRepository {
    private final JdbcTemplate jdbcTemplate;

    public QuestionsPoolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<QuestionPoolDto> findAllQuestionPool() {
        String sql = "select * from question_pool qp join question q on qp.id = q.question_pool_id";
        String questionSql = "select * from question where question_pool_id =?";
        List<QuestionPoolDto> questionPoolDtoList = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(QuestionPoolDto.class));
        questionPoolDtoList.forEach(questionPoolDto -> questionPoolDto
                .setQuestion(jdbcTemplate.query(questionSql,
                        new Object[]{questionPoolDto.getId()},
                        new BeanPropertyRowMapper<>(QuestionDto.class))));
        return questionPoolDtoList;
    }

    public QuestionPoolDto findQuestionPoolById(Long id) {
        String sql = "select * from question_pool where id = ?";
        QuestionPoolDto questionPoolDto = jdbcTemplate.query(sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(QuestionPoolDto.class))
                .stream().findAny().orElse(null);
        if (questionPoolDto != null) {
            String questionSql = "select * from question where question_pool_id =?";
            questionPoolDto.setQuestion(jdbcTemplate.query(questionSql,
                    new Object[]{questionPoolDto.getId()},
                    new BeanPropertyRowMapper<>(QuestionDto.class)));
        }
        return questionPoolDto;
    }

    public QuestionPoolDto createQuestionPool(QuestionPoolDto questionPoolDto) {
        String sql = "insert into question_pool(description) values(?) returning id";
        questionPoolDto.setId(jdbcTemplate.queryForObject(sql,
                new Object[]{questionPoolDto.getDescription()}, Long.class));
        questionPoolDto.getQuestion().forEach(questionDto -> {
            String sqlQuestion = "insert into question(answer_four, answer_one, answer_three, answer_two, question_name, true_question, question_pool_id) values(?,?,?,?,?,?,?) returning id";
            questionDto.setId(jdbcTemplate.queryForObject(sqlQuestion,
                    new Object[]{questionDto.getAnswerFour(), questionDto.getAnswerOne(),
                            questionDto.getAnswerThree(),
                            questionDto.getAnswerTwo(),
                            questionDto.getQuestionName(),
                            questionDto.getTrueQuestion(),
                            questionPoolDto.getId()}, Long.class));
        });
        return questionPoolDto;
    }

    public void updateQuestionPool(QuestionPool questionPool) {

        String sql = "update question_pool set id=?, description=? where id=?";
        jdbcTemplate.update(sql, questionPool.getId(), questionPool.getDescription(), questionPool.getId());
        for (Question questionDto : questionPool.getQuestion()) {
            String sqlQuestion = "update question set id=?,answer_one=?, answer_two=?, answer_three=?, answer_four=?, question_name=?, true_question=?, question_pool_id=? where id=?";
            jdbcTemplate.update(sqlQuestion, questionDto.getId(), questionDto.getAnswerOne(), questionDto.getAnswerTwo(), questionDto.getAnswerThree(), questionDto.getAnswerFour(), questionDto.getQuestionName(), questionDto.getTrueQuestion(), questionDto.getQuestionPool().getId(), questionDto.getId());
        }
    }
}
