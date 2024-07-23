package com.runtobeat.first.dto;


import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class MonthlyRecordResponseDTO {
    private String monthlyRecordId;
    private String memberId;
    private Long monthlyTotalDistance;
    private Long monthlyTotalTime;
    private LocalDate yearMonth;
    private Double monthlyRecordPace;



}
