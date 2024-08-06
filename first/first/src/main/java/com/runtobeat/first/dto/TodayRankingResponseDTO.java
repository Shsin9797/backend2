package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TodayRankingResponseDTO {
    private Integer todayMyThisStoryRanking;
    private Double todayTotalUserAvgPace;
    private Integer todayTotalUserRecordCount;
}
