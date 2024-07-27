package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class MonthlyRecordRequestDTO {
    private String monthlyRecordId;
    private String memberId;
    private Double monthlyTotalDistance;
    private LocalTime monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;
    private Long MonthlyRunningStep;
    private  String monthYear;


    public MonthlyRecordRequestDTO(String memberId, Double monthlyTotalDistance, LocalTime monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace, Long monthlyRunningStep, String monthYear) {
        this.memberId = memberId;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
        MonthlyRunningStep = monthlyRunningStep;
        this.monthYear = monthYear;
    }
}
