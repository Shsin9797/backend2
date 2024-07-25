package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TodayRankingResponseDTO {
    private Integer todayMyThisStoryRanking;
    private Double todayTotalUserAvgPace;
    private Integer todayTotalUserRecordCount;
}
