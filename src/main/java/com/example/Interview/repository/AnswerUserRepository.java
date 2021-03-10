package com.example.Interview.repository;

import com.example.Interview.model.AnswerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerUserRepository extends JpaRepository<AnswerUser, Long> {
}
