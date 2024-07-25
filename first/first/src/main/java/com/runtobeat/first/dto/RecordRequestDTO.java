package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class RecordRequestDTO {
    private String recordId;
    private String memberId;
    private Long runningDistance;
    private LocalTime runningTime;
    private Long runningStep;
    private LocalDate recordDate;
    private Double recordPace;

    public RecordRequestDTO(String memberId, Long runningDistance, LocalTime runningTime, Long runningStep, LocalDate recordDate, Double recordPace) {
        this.memberId = memberId;
        this.runningDistance = runningDistance;
        this.runningTime = runningTime;
        this.runningStep = runningStep;
        this.recordDate = recordDate;
        this.recordPace = recordPace;
    }
}
