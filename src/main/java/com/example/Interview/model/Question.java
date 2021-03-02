package com.example.Interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionName;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String trueQuestion;
    @ManyToOne
    private QuestionPool questionPool;
}
