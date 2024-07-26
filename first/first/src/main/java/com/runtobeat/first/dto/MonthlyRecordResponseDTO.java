package com.runtobeat.first.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class MonthlyRecordResponseDTO {
    private String monthlyRecordId;
    private String memberId;
    private Double monthlyTotalDistance;
    private LocalTime monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;
    private Long monthlyRunningStep;
}
