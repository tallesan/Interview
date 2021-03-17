package com.example.Interview.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionPoolDto {

    private Long id;
    private String description;
    private List<QuestionDto> question;
}
