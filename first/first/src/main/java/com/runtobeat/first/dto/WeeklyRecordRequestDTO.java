package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class WeeklyRecordRequestDTO {
    private String weeklyRecordId;
    private String memberId;
    private Double weeklyTotalDistance;
    private LocalTime weeklyTotalTime;
    private LocalDate yearWeek;
    private Double weeklyRecordPace;
    private Long weeklyRunningStep;
}
