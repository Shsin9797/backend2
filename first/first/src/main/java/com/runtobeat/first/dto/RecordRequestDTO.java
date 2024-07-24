package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class RecordRequestDTO {
    private String recordId;
    private String memberId;
    private Long runningDistance;
    private LocalTime runningTime;
    private Long runningStep;
    private LocalDate recordDate;
    private Double recordPace;

}
