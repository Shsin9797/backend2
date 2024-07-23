package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class WeeklyRecordRequestDTO {
    private String weeklyRecordId;
    private String memberId;
    private Long weeklyTotalDistance;
    private Long weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;
}
