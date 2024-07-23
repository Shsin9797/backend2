package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
public class RecordRequestDTO {
    private String recordId;
    private String memberId;
    private Long runningDistance;
    private LocalTime runningTime;
    private Long runningStep;
    private LocalDate recordDate;
    private Double recordPace;

}
