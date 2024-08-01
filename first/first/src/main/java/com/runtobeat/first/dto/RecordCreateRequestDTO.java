package com.runtobeat.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordCreateRequestDTO {
    private Long memberId;
    private Double runningDistance;
    private Long runningTime;
    private LocalDate recordDate;
    private Double recordPace;
    private Long runningStep;

}
