package com.example.Interview.Dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SystemUserDetail {
    private String email;
    private Long trueAnswer;
}
