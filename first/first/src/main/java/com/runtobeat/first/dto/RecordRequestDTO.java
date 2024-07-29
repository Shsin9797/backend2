package com.runtobeat.first.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecordRequestDTO {
    private Long recordId;
    private Long memberId;
    private Double runningDistance;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime runningTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;
    private Double recordPace;
    private Long runningStep;

    public RecordRequestDTO(Long memberId, Double runningDistance, LocalTime runningTime, LocalDate recordDate, Double recordPace, Long runningStep) {
        this.memberId = memberId;
        this.runningDistance = runningDistance;
        this.runningTime = runningTime;
        this.recordDate = recordDate;
        this.recordPace = recordPace;
        this.runningStep = runningStep;
    }
}
