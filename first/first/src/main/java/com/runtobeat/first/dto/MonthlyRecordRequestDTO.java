package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MonthlyRecordRequestDTO {
    private Long monthlyRecordId;
    private Long memberId;
    private Double monthlyTotalDistance;
    private Long monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;
    private Long MonthlyRunningStep;
    private String monthYear;


    public MonthlyRecordRequestDTO(Long memberId, Double monthlyTotalDistance, Long monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace, Long monthlyRunningStep, String monthYear) {
        this.memberId = memberId;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
        MonthlyRunningStep = monthlyRunningStep;
        this.monthYear = monthYear;
    }
}
