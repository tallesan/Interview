package com.example.Interview.dao;

import com.example.Interview.model.QuestionPool;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
public class QuestionDao {
    private Long id;
    private String questionName;
    private List<String> answers;
    private String trueQuestion;
    private QuestionPool questionPool;
}
