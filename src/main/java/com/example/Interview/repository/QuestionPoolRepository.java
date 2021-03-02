package com.example.Interview.repository;

import com.example.Interview.model.QuestionPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPoolRepository extends JpaRepository<QuestionPool, Long> {

}
