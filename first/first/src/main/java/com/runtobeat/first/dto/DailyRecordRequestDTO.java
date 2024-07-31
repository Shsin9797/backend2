package com.runtobeat.first.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyRecordRequestDTO {
    private Long dailyRecordId;
    private Long memberId;
    private Double dailyTotalDistance;
    private Long dailyTotalTime;
    private LocalDate yearMonthDate;
    private Double dailyRecordPace;
    private Long dailyRunningStep;

    public DailyRecordRequestDTO(Long memberId, Double dailyTotalDistance, Long dailyTotalTime, LocalDate yearMonthDate, Double dailyRecordPace, Long dailyRunningStep) {
        this.memberId = memberId;
        this.dailyTotalDistance = dailyTotalDistance;
        this.dailyTotalTime = dailyTotalTime;
        this.yearMonthDate = yearMonthDate;
        this.dailyRecordPace = dailyRecordPace;
        this.dailyRunningStep = dailyRunningStep;
    }
}
