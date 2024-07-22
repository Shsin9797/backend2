package com.runtobeat.first.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class MonthlyRecordRequestDTO {
    private String monthlyRecordId;
    private String memberId;
    private Long monthlyTotalDistance;
    private Long monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;

    public MonthlyRecordRequestDTO(String monthlyRecordId, String memberId, Long monthlyTotalDistance, Long monthlyTotalTime, LocalDate yearMonth, Double monthlyRecordPace) {
        this.monthlyRecordId = monthlyRecordId;
        this.memberId = memberId;
        this.monthlyTotalDistance = monthlyTotalDistance;
        this.monthlyTotalTime = monthlyTotalTime;
        this.yearMonth = yearMonth;
        this.monthlyRecordPace = monthlyRecordPace;
    }


}
