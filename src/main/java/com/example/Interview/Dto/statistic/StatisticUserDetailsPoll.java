package com.example.Interview.Dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticUserDetailsPoll {
    private String description;
    private Long questionSum;
    private List<SystemUserDetail> userDetailList;
}
