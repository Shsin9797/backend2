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

    public MonthlyRecordRequestDTO(String member1, double v, LocalTime of, String s, double v1, int i, String s1) {
    }
}
