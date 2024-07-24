package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class MonthlyRecordRequestDTO {
    private String monthlyRecordId;
    private String memberId;
    private Long monthlyTotalDistance;
    private Long monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;


}
