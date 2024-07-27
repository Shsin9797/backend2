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
    private Double runningDistance;
    private LocalTime runningTime;
    private LocalDate recordDate;
    private Double recordPace;
    private Long runningStep;

    public RecordRequestDTO(String memberId, Double runningDistance, LocalTime runningTime, LocalDate recordDate, Double recordPace, Long runningStep) {
        this.memberId = memberId;
        this.runningDistance = runningDistance;
        this.runningTime = runningTime;
        this.recordDate = recordDate;
        this.recordPace = recordPace;
        this.runningStep = runningStep;
    }
}
