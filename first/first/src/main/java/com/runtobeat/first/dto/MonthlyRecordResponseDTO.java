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
public class MonthlyRecordResponseDTO {
    private Long monthlyRecordId;
    private Long memberId;
    private Double monthlyTotalDistance;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime monthlyTotalTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearMonth;
    private Double monthlyRecordPace;
    private Long monthlyRunningStep;
    private  String monthYear;

    public MonthlyRecordResponseDTO(Long memberId, Double monthlyTotalDistance, LocalTime monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace, Long monthlyRunningStep, String monthYear) {
        this.memberId = memberId;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
        this.monthlyRunningStep = monthlyRunningStep;
        this.monthYear = monthYear;
    }
}
