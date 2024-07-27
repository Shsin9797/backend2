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

    public DailyRecordRequestDTO(String memberId, Double dailyTotalDistance, LocalTime dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace, Long dailyRunningStep) {
        this.memberId = memberId;
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
        this.dailyRunningStep = dailyRunningStep;
    }
}
