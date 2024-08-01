package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecordResponseDTO {
    private Long recordId;
    private Long memberId;
    private Double runningDistance;
    private Long runningTime;
    private LocalDate recordDate;
    private Double recordPace;
    private Long runningStep;

    public RecordResponseDTO(Long memberId, Double runningDistance, Long runningTime, LocalDate recordDate, Double recordPace, Long runningStep) {
        this.memberId = memberId;
        this.runningDistance = runningDistance;
        this.runningTime = runningTime;
        this.recordDate = recordDate;
        this.recordPace = recordPace;
        this.runningStep = runningStep;
    }
}
