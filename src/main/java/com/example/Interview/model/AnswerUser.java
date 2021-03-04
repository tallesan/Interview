package com.example.Interview.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "answer_user")
public class AnswerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users user;
    private String answer;
    private Boolean trueAnswer;
    @ManyToOne
    private Question question;
}
