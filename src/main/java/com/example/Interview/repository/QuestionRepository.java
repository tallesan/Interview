package com.example.Interview.repository;

import com.example.Interview.model.Question;
import com.example.Interview.model.QuestionPool;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM Question where question_pool_id = :id", nativeQuery = true)
    List<Question> findByQuestionPoolId(Long id);
}
