package com.example.Interview.dao.statisticDao;

import com.example.Interview.model.AnswerUser;
import com.example.Interview.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticUserAnswer {
    private Question question;
    private AnswerUser answerUser;

}
