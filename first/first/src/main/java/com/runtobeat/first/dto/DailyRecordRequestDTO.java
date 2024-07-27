package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class DailyRecordRequestDTO {
    private String dailyRecordId;
    private String memberId;
    private Double dailyTotalDistance;
    private LocalTime dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;
    private Long dailyRunningStep;
    public DailyRecordRequestDTO( Double dailyTotalDistance, LocalTime dailyTotalTime, Long dailyRunningStep, LocalDate yearMonthDate, Double dailyRecordPace) {
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.dailyRunningStep = dailyRunningStep;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
    }
}
